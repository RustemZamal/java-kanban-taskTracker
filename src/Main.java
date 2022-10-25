import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import task.Epic;
import task.TaskStatus;
import task.Subtask;
import manager.InMemoryTaskManager;
import task.Task;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Тесты

        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Task task1 = new Task(- 1,"My task 1", "Description 1",TaskStatus.NEW);
        Task task2 = new Task(- 1,"My task 2", "Description 2",TaskStatus.IN_PROGRESS);
        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);
        System.out.println("После добавления всех tasks: " + inMemoryTaskManager.getAllTasks());
        //inMemoryTaskManager.deleteTaskById(task2.getId());
        System.out.println(inMemoryTaskManager);
        System.out.println();

        Epic epic1 = new Epic(- 1, "EPIC 1", "Epic Description 1", TaskStatus.NEW);
        Epic epic2 = new Epic(- 1, "EPIC 2", "Epic Description 2", TaskStatus.NEW);
        inMemoryTaskManager.addEpic(epic1);
        inMemoryTaskManager.addEpic(epic2);
        System.out.println("После добления всех epics:" + inMemoryTaskManager.getALLEpics());
        System.out.println();

        inMemoryTaskManager.getTaskById(task1.getId());
        inMemoryTaskManager.getTaskById(task1.getId());
        System.out.println("History!!: " + inMemoryTaskManager.getHistory());

        Subtask subtask1 = new Subtask(-1, "SUBTASK 1", "Subtask Description 1", TaskStatus.NEW, epic1.getId());
        Subtask subtask2 = new Subtask(-1, "SUBTASK 2", "Subtask Description 2", TaskStatus.NEW, epic1.getId());
        Subtask subtask3 = new Subtask(-1, "SUBTASK 3", "Subtask Description 3", TaskStatus.DONE, epic1.getId());
        inMemoryTaskManager.addSubtask(subtask1);
        inMemoryTaskManager.addSubtask(subtask2);
        inMemoryTaskManager.addSubtask(subtask3);
        System.out.println(inMemoryTaskManager.getALLSubtasks());
        System.out.println("Провряю статус epic после добавления всех subtasks: " + inMemoryTaskManager.getALLEpics());
        System.out.println(inMemoryTaskManager);
        System.out.println();

       // inMemoryTaskManager.deleteSubtaskById(subtask2.getId());
        System.out.println("Проверяю статус epic после удаления subtask по id: "  + inMemoryTaskManager.getALLEpics());
        System.out.println();

        //inMemoryTaskManager.deleteAllEpics();
        System.out.println("После удаление всех epics: "  + inMemoryTaskManager.getALLEpics());
        System.out.println("После удаления всех epics проверяю наличие subtasks" + inMemoryTaskManager.getALLSubtasks());
        System.out.println();

        System.out.println(inMemoryTaskManager);
        System.out.println(inMemoryTaskManager.getSubtaskById(subtask1.getId()));
        System.out.println(inMemoryTaskManager.getSubtaskById(subtask3.getId()));
        System.out.println("History 1: " + inMemoryTaskManager.getHistory() + " size: " + inMemoryTaskManager.getHistory().size());
        System.out.println();
       // System.out.println(inMemoryTaskManager.getTaskById(task2.getId()));
        System.out.println("History 2: " + inMemoryTaskManager.getHistory() + " size: " + inMemoryTaskManager.getHistory().size());
        System.out.println();
       // System.out.println(inMemoryTaskManager.getEpicById(epic1.getId()));
        System.out.println("History 3: " + inMemoryTaskManager.getHistory() + " size: " + inMemoryTaskManager.getHistory().size());
        System.out.println();
        //System.out.println(inMemoryTaskManager.getEpicById(epic2.getId()));
        System.out.println("History 4: " + inMemoryTaskManager.getHistory() + " size: " + inMemoryTaskManager.getHistory().size());

        System.out.println();
        TaskManager manager = Managers.getDefault();
        Epic epic4 = new Epic(- 1, "EPIC_MANAGER 4", "Epic Description 4", TaskStatus.NEW);
        Epic epic5 = new Epic(- 1, "EPIC_MANAGER 5", "Epic Description 5", TaskStatus.NEW);
        manager.addEpic(epic4);
        manager.addEpic(epic5);
        Subtask subtask4 = new Subtask(-1, "SUBTASK 4", "Subtask Description 4", TaskStatus.DONE, epic4.getId());
        Subtask subtask5 = new Subtask(-1, "SUBTASK 5", "Subtask Description 5", TaskStatus.NEW, epic4.getId());
        manager.addSubtask(subtask4);
        manager.addSubtask(subtask4);
        manager.addSubtask(subtask5);
        manager.getSubtaskById(subtask4.getId());
        System.out.println("History manager : " + manager.getHistory());
        System.out.println();
        System.out.println(manager.getALLEpics());
        manager.deleteAllSubtasks();
        System.out.println("History manager after deleting all Susbtasks: " + manager.getHistory());
        manager.deleteAllEpics();


        System.out.println("Manager.getALLSubtasks: " + manager.getALLSubtasks());

        Task task3 = new Task(- 1,"My task 3", "Description 3",TaskStatus.NEW);
        Task task4 = new Task(- 1,"My task 4", "Description 4",TaskStatus.IN_PROGRESS);
        Task task5 = new Task(- 1,"My task 5", "Description 5",TaskStatus.DONE);
        manager.addTask(task3);
        manager.addTask(task4);
        manager.addTask(task3);
        manager.addTask(task5);

        System.out.println("GetAllTasks: " + manager.getAllTasks());

        System.out.println("Manager.geTaskBy id:" + manager.getTaskById(task3.getId()));
        System.out.println("Manager.geTaskBy id:" + manager.getTaskById(task4.getId()));
        manager.deleteTaskById(task3.getId());

        System.out.println("History manager : " + manager.getHistory());
        manager.deleteAllTasks();
        System.out.println("History manager afte deleting All Tasks: " + manager.getHistory());

        System.out.println();
        System.out.println(manager.getEpicById(epic4.getId()));
        System.out.println(manager.getEpicById(epic5.getId()));
        System.out.println(manager.getSubtaskById(subtask4.getId()));
        System.out.println("History manager 1: " + manager.getHistory());
        System.out.println();

        manager.deleteAllEpics();
        System.out.println("History manager after deleting all Epics: " + manager.getHistory());
        System.out.println("manager.getSubtaskById: " + manager.getSubtaskById(subtask4.getId()));
        System.out.println("History manager 2: " + manager.getHistory());
        System.out.println();

        manager.getTaskById(task3.getId());
        manager.getEpicById(epic4.getId());
        System.out.println("History manager 3: " + manager.getHistory());
        System.out.println();

        manager.getTaskById(task4.getId());
        manager.getTaskById(task3.getId());
        System.out.println("History manager 4: " + manager.getHistory() +" size: " + manager.getHistory().size());


        System.out.println(manager.getALLEpics());
        System.out.println("Managers.getDefaultHistory: " + manager.getHistory());
        HistoryManager managerHistory = Managers.getDefaultHistory();
        System.out.println(managerHistory.getHistoryManager());
        System.out.println(manager.getHistory() + " size: " + manager.getHistory().size());
        System.out.println(manager);
    }
}
