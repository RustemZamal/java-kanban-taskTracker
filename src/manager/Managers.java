package manager;

/**
 * The type Managers.
 */
public class Managers {

    /**
     * Gets default.
     *
     * @return the default
     */
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    /**
     * Gets default history.
     *
     * @return the default history
     */
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
