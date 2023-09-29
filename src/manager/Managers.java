package manager;

public final class Managers {
    public static TaskManager getDefault() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        return new InMemoryTaskManager(historyManager);
    }
    public static HistoryManager getHistoryDefault(){
        return new InMemoryHistoryManager();
    }
}
