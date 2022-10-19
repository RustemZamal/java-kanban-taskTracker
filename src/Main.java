import task.Epic;
import task.Subtask;
import manager.TaskManager;
import task.Task;

public class Main {
    public static void main(String[] args) {
        // Тесты

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task(- 1,"My task 1", "Description 1","NEW");
        Task task2 = new Task(- 1,"My task 2", "Description 2","IN PROGRESS");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        System.out.println("После добавления всех tasks: " + taskManager.getAllTasks());
        taskManager.deleteTaskById(task2.getId());
        System.out.println(taskManager);
        System.out.println();

        Epic epic1 = new Epic(- 1, "EPIC 1", "Epic Description 1", "NEW");
        Epic epic2 = new Epic(- 1, "EPIC 2", "Epic Description 2", "NEW");
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        System.out.println("После добления всех epics:" + taskManager.getALLEpics());
        System.out.println();

        Subtask subtask1 = new Subtask(-1, "SUBTASK 1", "Subtask Description 1", "NEW", epic1.getId());
        Subtask subtask2 = new Subtask(-1, "SUBTASK 2", "Subtask Description 2", "DONE", epic1.getId());
        Subtask subtask3 = new Subtask(-1, "SUBTASK 3", "Subtask Description 3", "NEW", epic2.getId());
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        System.out.println(taskManager.getALLSubtasks());
        System.out.println("Провряю статус epic после добавления всех subtasks: " + taskManager.getALLEpics());
        System.out.println(taskManager);
        System.out.println();

        taskManager.deleteSubtaskById(subtask2.getId());
        System.out.println("Проверяю статус epic после удаления subtask по id: "  + taskManager.getALLEpics());
        System.out.println();

        taskManager.deleteAllEpics();
        System.out.println("После удаление всех epics: "  + taskManager.getALLEpics());
        System.out.println("После удаления всех epics проверяю наличие subtasks" + taskManager.getALLSubtasks());
        System.out.println();

        System.out.println(taskManager);

    }
}
