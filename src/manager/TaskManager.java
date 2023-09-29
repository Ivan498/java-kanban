package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.List;

public interface TaskManager {
    void addTask(Task task);
    void addEpic(Epic epic);
    void addSubtask(Subtask subtask);
    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);
    List<Task> getAllEpic();
    List<Task> getAllTask();
    List<Task> getAllSubtask();
    void removeAllEpic();
    void removeAllTask();
    void removeAllSubtask();
    Task getTaskById(int taskId);
    void deletionSubtaskById(int id);
    List<Subtask> getEpicTasks(int epicId);
    void updateEpicStatus(int epicId);
}
