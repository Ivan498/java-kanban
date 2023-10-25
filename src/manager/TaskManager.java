package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.io.IOException;
import java.util.List;

public interface TaskManager {
    void addTask(Task task) throws IOException;
    void addEpic(Epic epic) throws IOException;
    void addSubtask(Subtask subtask) throws IOException;
    void updateTask(Task task) throws IOException;
    void updateEpic(Epic epic) throws IOException;
    void updateSubtask(Subtask subtask) throws IOException;
    List<Task> getAllEpic() throws IOException;
    List<Task> getAllTask() throws IOException;
    List<Task> getAllSubtask() throws IOException;
    void removeAllEpic() throws IOException;
    void removeAllTask() throws IOException;
    void removeAllSubtask() throws IOException;
    Task getTaskById(int taskId) throws IOException;
    void deletionSubtaskById(int id) throws IOException;
    List<Subtask> getEpicTasks(int epicId) throws IOException;
    void updateEpicStatus(int epicId) throws IOException;
    List<Task> getHistory() throws IOException;
}
