package task;

import java.util.Objects;

/**
 * The type Subtask.
 */
public class Subtask extends Task {

    /**
     * The Epic id.
     */
    protected int epicId;

    /**
     * Instantiates a new Subtask.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param status      the status
     * @param epicId      the epic id
     */
    public Subtask(int id, String name, String description, TaskStatus status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    /**
     * Gets epic id.
     *
     * @return the epic id
     */
    public int getEpicId() {
        return epicId;
    }

    /**
     * Sets epic id.
     *
     * @param epicId the epic id
     */
    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicId == subtask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

