package manager;

import Tasks.Task;

import java.util.List;

public interface HistoryManager {
    void remove(int id);
    List<Task> getHistory();

    void add(Task task);
}
