package task;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    protected ArrayList<Integer> subtaskIds;

    public Epic(int id, String name, String description, String status) {
        super(id, name, description, status);
        subtaskIds = new ArrayList<>();
    }

    public void addSubtaskId(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void deleteSubtaskIds() {
        subtaskIds.clear();
    }

    /**
     *
     * @param subtaskId принимает аргумент id subtask, которого удали, для того чтобы удалить этот id
     *  из "ArrayList<Integer> subtaskIds".
     *  Пришлось упоковать в класс-обертку, в противнои слечае вылетает ошибка, так как id воспринмается не как
     *  значение, а как индекс
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
