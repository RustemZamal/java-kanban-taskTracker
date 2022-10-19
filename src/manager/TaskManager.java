package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class TaskManager {
    private int generatorId = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public void addTask(Task task) {
        task.setId(generatorId);
        tasks.put(task.getId(), task);
        generatorId++;
    }

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
    public void addSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        subtask.setId(generatorId);
        subtasks.put(subtask.getId(),subtask);
        epic.addSubtaskId(subtask.getId());
        updateEpicStatus(epic);
        generatorId++;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
    }

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
            epic.setStatus("NEW");
            return;
        }

        String status = null;
        for (int subtaskId : subtaskIds) {
            Subtask subtask = subtasks.get(subtaskId);
            if (status == null) {
                status = subtask.getStatus();
                continue;
            }

            if (status.equals(subtask.getStatus())) {
                continue;
            }

            epic.setStatus("IN_PROGRESS");
            return;
        }

        epic.setStatus(status);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTaskById (int taskId) {
        Task task = tasks.get(taskId);
        return task;
    }

    public ArrayList<Epic> getALLEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic getEpicById(int epicId) {
        return (epics.get(epicId));
    }

    public ArrayList<Subtask> getALLSubtasks() {
        ArrayList<Subtask> arrSubtasks = new ArrayList<>();
        for (Integer key : subtasks.keySet()) {
            arrSubtasks.add(subtasks.get(key));
        }
        return arrSubtasks;
    }

    public Subtask getSubtaskById(int subtaskId) {
        return (subtasks.get(subtaskId));
    }

    public void deleteAllTasks() {
        decreaseId(tasks.size());
        tasks.clear();

    }

    public void deleteTaskById(int taskId) {
        tasks.remove(taskId);
        generatorId--;
    }

    public void deleteAllEpics() {
        for (Integer keyId : epics.keySet()) {
            Epic epic = epics.get(keyId);
            epic.deleteSubtaskIds();
        }
        decreaseId(subtasks.size());
        subtasks.clear();
        decreaseId(epics.size());
        epics.clear();
    }

    public void deleteEpicById(int epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
        for (Integer subId : subtaskIds) {
            subtasks.remove(subId);
            generatorId--;
        }
        epic.deleteSubtaskIds();
        epics.remove(epicId);
        generatorId--;
    }

    /**
     *
     */
    public void deleteAllSubtasks() {
        for (Integer keyId : epics.keySet()) {
            Epic epic = epics.get(keyId);
            ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
            for(Integer subId : subtaskIds) {
                subtasks.remove(subId);
                generatorId--;
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
    public void deleteSubtaskById(int subtaskId) {
        Subtask subtask = subtasks.get(subtaskId);
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        subtasks.remove(subtaskId);
        epic.deleteSubtaskIdById(subtaskId);
        updateEpicStatus(epic);
        generatorId--;
    }

    public void decreaseId(int size) {
        for (int i = 0; i < size; i++) {
            generatorId--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManager that = (TaskManager) o;
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
