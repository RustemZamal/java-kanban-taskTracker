package manager;

import task.Task;

import java.util.List;

/**
 * The interface History manager.
 * @see InMemoryHistoryManager
 */
public interface HistoryManager {

    /**
     * Add history.
     *
     * @param task   the task
     * @param taskId the task id
     */
    void addHistory(Task task, int taskId);

    /**
     * Delete history list.
     *
     * @param task the task
     */
    void deleteHistoryList(Task task);

    /**
     * Gets history manager.
     *
     * @return the history manager
     */
    List<Task> getHistoryManager();


}
