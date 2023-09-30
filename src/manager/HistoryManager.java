package manager;

import Tasks.Task;

import java.util.List;

public interface HistoryManager {

    void removeNode(InMemoryHistoryManager.Node nodeToRemove);

    void remove(int id);
    List<Task> getHistory();

    void linkLast(Task task);
    void add (Task task);
}
