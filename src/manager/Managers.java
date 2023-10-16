package manager;

public final class Managers {
    public static TaskManager getDefault(HistoryManager historyManager) {
        return new InMemoryTaskManager(historyManager);
    }
    public static HistoryManager getHistoryDefault(){
        return new InMemoryHistoryManager();
    }
}
