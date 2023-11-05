package manager;

import manager.file.FileBackedTasksManager;
import manager.file.ManagerSaveException;

import java.io.File;
import java.io.IOException;

public final class Managers {
    private Managers() {

    }
    public static TaskManager getDefault() throws IOException, ManagerSaveException {
        return new FileBackedTasksManager(new File("example.csv"));
    }
    public static HistoryManager getHistoryDefault(){
        return new InMemoryHistoryManager();
    }
}
