package manager;

public final class Managers {
    private Managers() {

    }
    public static TaskManager getDefault() {
        return new InMemoryTaskManager("example.csv");
    }
    public static HistoryManager getHistoryDefault(){
        return new InMemoryHistoryManager();
    }
}
