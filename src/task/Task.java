package task;

import java.util.Objects;

/**
 * The type Task.
 */
public class Task {

    /**
     * The Id.
     */
    protected int id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Status.
     */
    protected TaskStatus status;

    /**
     * Instantiates a new Task.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param status      the status
     */
    public Task(int id, String name, String description, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    /**
     * Instantiates a new Task.
     *
     * @param name        the name
     * @param description the description
     * @param status      the status
     */
    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    /**
     * Instantiates a new Task.
     *
     * @param name        the name
     * @param description the description
     */
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW; // по умолчению будет саздаватся со статусом NEW
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
