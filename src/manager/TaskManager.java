package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface Task manager.
 */
public interface TaskManager {

    /**
     * Add task.
     *
     * @param task the task
     */
    void addTask(Task task);

    /**
     * Add epic.
     *
     * @param epic the epic
     */
    void addEpic (Epic epic);

    /**
     * Add subtask.
     *
     * @param subtask the subtask
     */
    void addSubtask(Subtask subtask);

    /**
     * Update task.
     *
     * @param task the task
     */
    void updateTask(Task task);

    /**
     * Update epic.
     *
     * @param epic the epic
     */
    void updateEpic(Epic epic);

    /**
     * Update subtask.
     *
     * @param subtask the subtask
     */
    void updateSubtask(Subtask subtask);

    /**
     * Gets all tasks.
     *
     * @return the all tasks
     */
    ArrayList<Task> getAllTasks();

    /**
     * Gets task by id.
     *
     * @param taskId the task id
     * @return the task by id
     */
    Task getTaskById (int taskId);

    /**
     * Gets all epics.
     *
     * @return the all epics
     */
    ArrayList<Epic> getALLEpics();

    /**
     * Gets epic by id.
     *
     * @param epicId the epic id
     * @return the epic by id
     */
    Epic getEpicById(int epicId);

    /**
     * Gets all subtasks.
     *
     * @return the all subtasks
     */
    ArrayList<Subtask> getALLSubtasks();

    /**
     * Gets subtask by id.
     *
     * @param subtaskId the subtask id
     * @return the subtask by id
     */
    Subtask getSubtaskById(int subtaskId);

    /**
     * Delete all tasks.
     */
    void deleteAllTasks();

    /**
     * Delete task by id.
     *
     * @param taskId the task id
     */
    void deleteTaskById(int taskId);

    /**
     * Delete all epics.
     */
    void deleteAllEpics();

    /**
     * Delete epic by id.
     *
     * @param epicId the epic id
     */
    void deleteEpicById(int epicId);

    /**
     * Delete all subtasks.
     */
    void deleteAllSubtasks();

    /**
     * Delete subtask by id.
     *
     * @param subtaskId the subtask id
     */
    void deleteSubtaskById(int subtaskId);

    /**
     * Gets history.
     *
     * @return the history
     */
    List<Task> getHistory();
}
