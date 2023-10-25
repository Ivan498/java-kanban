package manager.file;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.Types;
import manager.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Types;
import manager.Managers;
import Tasks.Task;
import manager.HistoryManager;
import manager.Status;
import manager.TaskManager;
import manager.file.FileBackedTasksManager;

import java.io.FileWriter;
import java.io.IOException;
public class FileBackedTasksManager extends InMemoryTaskManager {
    private CSVFormatHandler handler = new CSVFormatHandler();
    File file;
    public static void main(String[] args) throws IOException {

        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(new File("example.csv"));

        Task task1 = new Task(1,Types.TASK,"Задача №1",Status.NEW, "...", "task1");
        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.addTask(new Task(2,Types.TASK,"Задача №2",Status.NEW, "...", "task2"));

        Epic epic1 = new Epic(3,Types.EPIC,"Epic №1",Status.NEW, "...", "epic1");
        fileBackedTasksManager.addEpic(epic1);

        Subtask subtask1 = new Subtask(4,Types.SUBTASK, "Подзадача №1",Status.NEW,"Зайти на сайт","subtask1");
        fileBackedTasksManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(5,Types.SUBTASK,"Подзадача №2",Status.NEW,"нажать на кнопку","subtask2");
        fileBackedTasksManager.addSubtask(subtask2);
        Subtask subtask3 = new Subtask(6,Types.SUBTASK,"Подзадача №3",Status.NEW,"найти работу","subtask3");
        fileBackedTasksManager.addSubtask(subtask3);

        fileBackedTasksManager.updateSubtask(subtask3);

        Epic epic2 = new Epic(7,Types.EPIC,"Epic №2",Status.NEW, "...", "epic2");
        fileBackedTasksManager.addEpic(epic2);

        System.out.println("Все задачи: " + fileBackedTasksManager.getAllTask());
        System.out.println("Все эпики: " + fileBackedTasksManager.getAllEpic());
        System.out.println("Все сабтаски: " + fileBackedTasksManager.getAllSubtask());

        System.out.println("Первая задача: " + fileBackedTasksManager.getTaskById(1));
        System.out.println("Вторая задача: " + fileBackedTasksManager.getTaskById(2));

        System.out.println("История: " + fileBackedTasksManager.getHistory());

        fileBackedTasksManager.removeAllTask();
        fileBackedTasksManager.removeAllEpic();
        fileBackedTasksManager.removeAllSubtask();

        System.out.println("Все задачи: " + fileBackedTasksManager.getAllTask());
        System.out.println("Все эпики: " + fileBackedTasksManager.getAllEpic());
        System.out.println("Все сабтаски: " + fileBackedTasksManager.getAllSubtask());

        fileBackedTasksManager.removeAllTask();
        System.out.println("История: " + fileBackedTasksManager.getHistory());

        fileBackedTasksManager.save();
    }

    public void save() throws IOException {
        try (FileWriter writer = new FileWriter("C:\\Users\\vanka\\dev\\java-kanban\\src\\manager\\example.csv",false)) {

            writer.write(handler.getHeader() + "\n") ;

            for (Task task : tasks.values()) {
                writer.write(handler.toString(task) + "\n");
            }
            for (Epic epic : epics.values()) {
                writer.write(handler.toString(epic) + "\n");
            }
            for (Subtask subtask : subTasks.values()) {
                writer.write(handler.toString(subtask) + "\n");
            }

            writer.write(handler.historyToString(historyManager) + "\n");

            writer.close();

        } catch (IOException e) {
            throw new IllegalArgumentException("Невозможно прочитать файл");
        }
    }


    public FileBackedTasksManager(File file) throws IOException {
        super("example.csv");
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

    @Override
    public List<Task> getHistory() throws IOException {
        List<Task> tasks =  super.getHistory();
        save();
        return tasks;
    }
}
