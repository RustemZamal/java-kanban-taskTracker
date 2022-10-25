package manager;

import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type In memory history manager.
 */
public class InMemoryHistoryManager implements HistoryManager {

    /**
     * The History list.
     */
    protected List<Task> historyList = new ArrayList<>();

    /**
     * The Ids.
     */
    protected List<Integer> ids = new ArrayList<>(); // ???

    @Override
    public void addHistory(Task task, int id) {
        if (task == null) {
            return;
        }

        if (!ids.contains(id)) {
            ids.add(id);
            historyList.add(task);
            if (historyList.size() > 2) {
                Task taskId = historyList.get(0);
                Integer delId = taskId.getId();
                ids.remove(delId);
                historyList.remove(0);
            }
        }
    }

    @Override
    public void deleteHistoryList(Task task) {
        if (!historyList.contains(task)) {
            return;
        }
            Integer taskId = task.getId();
            ids.remove(taskId);
            historyList.remove(task);
    }

    @Override
    public List<Task> getHistoryManager() {
        return historyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryHistoryManager that = (InMemoryHistoryManager) o;
        return Objects.equals(historyList, that.historyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyList);
    }

    @Override
    public String toString() {
        return "InMemoryHistoryManager{" +
                "historyList=" + historyList +
                '}';
    }
}
