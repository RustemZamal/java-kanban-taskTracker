package manager;

import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * The type In memory task manager.
 */
public class InMemoryTaskManager implements TaskManager {
    /**
     * The Generator id.
     */
    protected int generatorId = 1;
    /**
     * The Tasks.
     */
    protected HashMap<Integer, Task> tasks = new HashMap<>();
    /**
     * The Epics.
     */
    protected HashMap<Integer, Epic> epics = new HashMap<>();
    /**
     * The Subtasks.
     */
    protected HashMap<Integer, Subtask> subtasks = new HashMap<>();

    /**
     * The History list.
     */
// при (просметре задачи) получения задачи по id, эту задачу надо добавить historyList
    // возвращать он должен последнии 10 просмотов. Необходимо проверять не размером ли спиок 10
    //можно перед добовлением или после добаления проверить ели список больше чем из 10 элементов удалять 1ый элемент
    protected List<Task> historyList = new ArrayList<>();
    /**
     * The Manager history.
     */
    protected HistoryManager managerHistory = Managers.getDefaultHistory();

    @Override
    public void addTask(Task task) {
        if (tasks.containsValue(task)) {
            return; /// !!! если так не делать, и добавлять один и тот же task он будет добавлятся
            // в мапу, но с одним и тем же id, при этом id будет на единицу больше
        }
        task.setId(generatorId);
        tasks.put(task.getId(), task);
        generatorId++;
    }

    @Override
    public void addEpic (Epic epic) {
        epic.setId(generatorId);
        epics.put(epic.getId(), epic);
        generatorId++;
    }

    /**
     *
     * @param subtask должен быть привязон к какому-то epic. Если epic с таким id не существует,
     * который указан в subtask, то такой subtask добавлятся не должен
     *
     */
    @Override
    public void addSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }

        if (subtasks.containsValue(subtask)) {
            return;
        }

        subtask.setId(generatorId);
        subtasks.put(subtask.getId(),subtask);
        epic.addSubtaskId(subtask.getId());
        updateEpicStatus(epic);
        generatorId++;
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        int epicId = subtask.getEpicId();
        if (!epics.containsKey(epicId)) {
            return;
        }
        Epic epic = epics.get(epicId);
        updateEpicStatus(epic);
    }


    private void updateEpicStatus(Epic epic) {
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
        if (subtaskIds.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        TaskStatus status = null;
        for (int subtaskId : subtaskIds) {
            Subtask subtask = subtasks.get(subtaskId);
            if (status == null) {
                status = subtask.getStatus();
                continue;
            }

            // status.equals(subtask.getStatus()) может выдовать NullPointerException.
            // Посматри урок Работа с перечислениями
            if (status == subtask.getStatus()) {
                continue;
            }

            epic.setStatus(TaskStatus.IN_PROGRESS);
            return;
        }

        epic.setStatus(status);
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTaskById (int taskId) {
        Task task = tasks.get(taskId);
        //historyList.add(task); // ?? check
        addElementToHistoryList(task);
        managerHistory.addHistory(task, taskId);
        return task;
    }

    @Override
    public ArrayList<Epic> getALLEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public Epic getEpicById(int epicId) {
       // historyList.add(epics.get(epicId)); //?? check
        addElementToHistoryList(epics.get(epicId));
        managerHistory.addHistory(epics.get(epicId), epicId);
        return (epics.get(epicId));
    }

    @Override
    public ArrayList<Subtask> getALLSubtasks() {
        ArrayList<Subtask> arrSubtasks = new ArrayList<>();
        for (Integer key : subtasks.keySet()) {
            arrSubtasks.add(subtasks.get(key));
        }
        return arrSubtasks;
    }

    @Override
    public Subtask getSubtaskById(int subtaskId) {
        addElementToHistoryList(subtasks.get(subtaskId));
        managerHistory.addHistory(subtasks.get(subtaskId), subtaskId);
        return (subtasks.get(subtaskId));
    }


    @Override
    public void deleteAllTasks() {
        List<Task> allTask = managerHistory.getHistoryManager();
        for (Task task : tasks.values()) {
            managerHistory.deleteHistoryList(task);
        }

        tasks.clear();
    }

    @Override
    public void deleteTaskById(int taskId) {
        managerHistory.deleteHistoryList(tasks.get(taskId));
        tasks.remove(taskId);

    }

    @Override
    public void deleteAllEpics() {
        for (Integer keyId : epics.keySet()) {
            Epic epic = epics.get(keyId);
            epic.deleteSubtaskIds();
            managerHistory.deleteHistoryList(epic);
        }

        for (Subtask subtask : subtasks.values()) {
            managerHistory.deleteHistoryList(subtask);
        }

        subtasks.clear();
        epics.clear();
    }

    @Override
    public void deleteEpicById(int epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
        for (Integer subId : subtaskIds) {
            subtasks.remove(subId);

        }
        epic.deleteSubtaskIds();
        epics.remove(epicId);
    }

    @Override
    public void deleteAllSubtasks() {
        for (Subtask subtask : subtasks.values()) {
            managerHistory.deleteHistoryList(subtask);
        }

        for (Integer keyId : epics.keySet()) {
            Epic epic = epics.get(keyId);
            ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
            for(Integer subId : subtaskIds) {
                subtasks.remove(subId);

            }
            epic.deleteSubtaskIds();
            updateEpicStatus(epic);
        }
    }

    /**
     *
     * @param subtaskId в качестве аргумена принимает id объекта класса Subtask для того чтобы уладить его.
     * По этому id(он же является ключет в мапе к объкту, где хранятся все объекты класса Subtask) вынемает из мапы
     * сам объект, у этого объекта в поле "int epicId" хранится id и он же клчюч к объкту класса Epic.
     * По этому ключу достаем из мапы сам epic, в поле которого "ArrayList<Integer> subtaskIds" хрянятся ids
     * объкта Subtask. Достаем id, для того чтобы при помощи метода deleteSubtaskIdById удалить id объекта
     * которого удалили. И обновлям статус epic при помощи "updateEpicStatus()" к которому привязан удаленный subtask
     *
     */
    @Override
    public void deleteSubtaskById(int subtaskId) {
        Subtask subtask = subtasks.get(subtaskId);
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        subtasks.remove(subtaskId);
        epic.deleteSubtaskIdById(subtaskId);
        updateEpicStatus(epic);
    }

    private void addElementToHistoryList(Task task) {
        historyList.add(task);
        if (historyList.size() > 2) {
            historyList.remove(0);
        }
    }

   /* @Override
    public List<Task> getHistory() {
        return historyList;
    }
*/
    public List<Task> getHistory() {
        return managerHistory.getHistoryManager();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryTaskManager that = (InMemoryTaskManager) o;
        return generatorId == that.generatorId && Objects.equals(tasks, that.tasks) && Objects.equals(epics, that.epics) && Objects.equals(subtasks, that.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generatorId, tasks, epics, subtasks);
    }

    @Override
    public String toString() {
        return "manager.TaskManager{" +
                "genaratorId=" + generatorId +
                ", tasks=" + tasks +
                ", epics=" + epics +
                ", subtasks=" + subtasks +
                '}';
    }
}
