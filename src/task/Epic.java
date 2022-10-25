package task;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Epic.
 */
public class Epic extends Task {
    /**
     * The Subtask ids.
     */
    protected ArrayList<Integer> subtaskIds;

    /**
     * Instantiates a new Epic.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param status      the status
     */
    public Epic(int id, String name, String description, TaskStatus status) {
        super(id, name, description, status);
        subtaskIds = new ArrayList<>();
    }

    /**
     * Add subtask id.
     *
     * @param subtaskId the subtask id
     */
    public void addSubtaskId(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    /**
     * Gets subtask ids.
     *
     * @return the subtask ids
     */
    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    /**
     * Delete subtask ids.
     */
    public void deleteSubtaskIds() {
        subtaskIds.clear();
    }

    /**
     * Delete subtask id by id.
     *
     * @param subtaskId принимает аргумент id subtask, которого удали, для того чтобы удалить этот id  из "ArrayList<Integer> subtaskIds".  Пришлось упоковать в класс-обертку, в противнои слечае вылетает ошибка, так как id воспринмается не как  значение, а как индекс
     */
    public void deleteSubtaskIdById(Integer subtaskId) {
        subtaskIds.remove(subtaskId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskIds, epic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskIds);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskIds=" + subtaskIds +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
