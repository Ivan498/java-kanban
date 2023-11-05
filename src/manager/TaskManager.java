package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import manager.file.ManagerSaveException;

import java.io.IOException;
import java.util.List;

public interface TaskManager {
    void addTask(Task task) throws IOException, ManagerSaveException;
    void addEpic(Epic epic) throws IOException, ManagerSaveException;
    void addSubtask(Subtask subtask) throws IOException, ManagerSaveException;
    void updateTask(Task task) throws IOException, ManagerSaveException;
    void updateEpic(Epic epic) throws IOException, ManagerSaveException;
    void updateSubtask(Subtask subtask) throws IOException, ManagerSaveException;
    List<Task> getAllEpic() throws IOException, ManagerSaveException;
    List<Task> getAllTask() throws IOException, ManagerSaveException;
    List<Task> getAllSubtask() throws IOException, ManagerSaveException;
    void removeAllEpic() throws IOException, ManagerSaveException;
    void removeAllTask() throws IOException, ManagerSaveException;
    void removeAllSubtask() throws IOException, ManagerSaveException;
    Task getTaskById(int taskId) throws IOException, ManagerSaveException;
    void deletionSubtaskById(int id) throws IOException, ManagerSaveException;
    List<Subtask> getEpicTasks(int epicId) throws IOException, ManagerSaveException;
    void updateEpicStatus(int epicId) throws IOException, ManagerSaveException;
    List<Task> getHistory() throws IOException, ManagerSaveException;
}
