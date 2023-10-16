package manager.file;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import manager.HistoryManager;
import manager.InMemoryTaskManager;

import java.io.*;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private CSVFormatHandler handler = new CSVFormatHandler();
    File file;
    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write(handler.getHeader());
            writer.newLine();

            for (Task task : tasks.values()) {
                writer.write(handler.toString(task));
                writer.newLine();
            }
            for (Epic epic : epics.values()) {
                writer.write(handler.toString(epic));
                writer.newLine();
            }
            for (Subtask subtask : subTasks.values()) {
                writer.write(handler.toString(subtask));
                writer.newLine();
            }

            writer.write(handler.historyToString(historyManager));
            writer.newLine();

        } catch (IOException e) {
            throw new IllegalArgumentException("Невозможно прочитать файл");
        }
    }

    static FileBackedTasksManager  loadFromFile(File file){
        
    }

    public FileBackedTasksManager(HistoryManager historyManager) throws IOException {
        super(historyManager);
        save();
    }

    @Override
    public void addTask(Task task) throws IOException {
        super.addTask(task);
        save();
    }

    @Override
    public void addEpic(Epic epic) throws IOException {
        super.addEpic(epic);
        save();
    }

    @Override
    public void addSubtask(Subtask subtask) throws IOException {
        super.addSubtask(subtask);
        save();
    }

    @Override
    public void updateTask(Task task) throws IOException {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) throws IOException {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) throws IOException {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public List<Task> getAllEpic() throws IOException {
        save();
        return super.getAllEpic();
    }

    @Override
    public List<Task> getAllTask() throws IOException {
        save();
        return super.getAllTask();
    }

    @Override
    public List<Task> getAllSubtask() throws IOException {
        save();
        return super.getAllSubtask();
    }

    @Override
    public void removeAllEpic() throws IOException {
        super.removeAllEpic();
        save();
    }

    @Override
    public void removeAllTask() throws IOException {
        super.removeAllTask();
        save();
    }

    @Override
    public void removeAllSubtask() throws IOException {
        super.removeAllSubtask();
        save();
    }

    @Override
    public Task getTaskById(int taskId) throws IOException {
        save();
        return super.getTaskById(taskId);
    }

    @Override
    public void deletionSubtaskById(int id) throws IOException {
        super.deletionSubtaskById(id);
        save();
    }

    @Override
    public List<Subtask> getEpicTasks(int epicId) throws IOException {
        save();
        return super.getEpicTasks(epicId);
    }

    @Override
    public void updateEpicStatus(int epicId) throws IOException {
        super.updateEpicStatus(epicId);
        save();
    }
}
