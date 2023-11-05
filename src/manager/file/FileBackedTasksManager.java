package manager.file;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.Types;
import manager.*;

import java.io.*;
import java.util.*;

import manager.Status;

import java.io.FileWriter;
import java.io.IOException;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private CSVFormatHandler handler = new CSVFormatHandler();
    private String file = "src/manager/example.csv";

    public static void main(String[] args) throws IOException, IllegalAccessException, ManagerSaveException {

        FileBackedTasksManager fileBackedTasksManager = FileBackedTasksManager.loadFromFile(new File("src/manager/example.csv"));
        FileBackedTasksManager newManager = new FileBackedTasksManager(new File("src/manager/example.csv"));

        Task task1 = new Task(1,Types.TASK,"Задача №1",Status.NEW, "...", "task1");
        fileBackedTasksManager.addTask(task1);

        Epic epic1 = new Epic(2,Types.EPIC,"Epic №1",Status.NEW, "...", "epic1");
        fileBackedTasksManager.addEpic(epic1);

        Subtask subtask1 = new Subtask(3,Types.SUBTASK,"Подзадача №3",Status.NEW,"найти работу","subtask3");
        fileBackedTasksManager.addSubtask(subtask1);
        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.getTaskById(1);
        System.out.println(fileBackedTasksManager.getHistory());

        if (fileBackedTasksManager.getTasks().equals(newManager.getTasks())) {
            System.out.println("Задачи те же самые");
        } else {
            System.out.println("Задачи не те же самые");
        }
        if (fileBackedTasksManager.getEpics().equals(newManager.getEpics())) {
            System.out.println("Эпики те же самые");
        } else {
            System.out.println("Эпики не те же самые");
        }
        if (fileBackedTasksManager.getSubtasks().equals(newManager.getSubtasks())) {
            System.out.println("Сабтаски те же самые");
        } else {
            System.out.println("Сабтаски не те же самые");
        }
        if(fileBackedTasksManager.getHistory().equals(newManager.getHistory())){
            System.out.println("История правильно записана");
        } else {
            System.out.println("История не правильно");
        }
    }

    public void save() throws IOException, ManagerSaveException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(handler.getHeader() + "\n") ;

            for (Task task : tasks.values()) {
                bufferedWriter.write(handler.toString(task) + "\n");
            }
            for (Epic epic : epics.values()) {
                bufferedWriter.write(handler.toString(epic) + "\n");
            }
            for (Subtask subtask : subTasks.values()) {
                bufferedWriter.write(handler.toString(subtask) + "\n");
            }

            bufferedWriter.write(handler.historyToString(historyManager) + "\n");

        } catch (IOException e) {
            throw new ManagerSaveException("Невозможно записать файл");
        }
    }

    static FileBackedTasksManager loadFromFile(File file) throws IllegalAccessException, IOException, ManagerSaveException {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        CSVFormatHandler csvFormatHandler = new CSVFormatHandler();
        Map<Integer, Task> recoveredTask = new LinkedHashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileBackedTasksManager.file))) {
            if(bufferedReader.readLine() == null){
                return new FileBackedTasksManager(file);
            }
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();

                if (line.isEmpty()) {
                    break;
                }
                try {
                    Task task = csvFormatHandler.fromString(line);
                    recoveredTask.put(task.getId(), task);
                }
                catch (Exception e){
                    continue;
                }
            }

            for (Task task : recoveredTask.values()){
                if(task.getId() > fileBackedTasksManager.idInc){
                    fileBackedTasksManager.idInc = task.getId();
                }

                if(task.getTypes() == Types.TASK){
                    fileBackedTasksManager.tasks.put(task.getId(), task);
                }
                if(task.getTypes() == Types.EPIC){
                    fileBackedTasksManager.epics.put(task.getId(), (Epic) task);
                }
                if(task.getTypes() == Types.SUBTASK){
                    fileBackedTasksManager.subTasks.put(task.getId(), (Subtask) task);
                }
                fileBackedTasksManager.idInc = recoveredTask.size();
                String historyLine = bufferedReader.readLine();
                List<Integer> history = csvFormatHandler.historyFromString(historyLine);

                for (Integer idHistory : history) {
                    fileBackedTasksManager.historyManager.add(recoveredTask.get(idHistory));
                }
            }
        }
        catch (IOException exp){
            throw new IllegalAccessException("Невозможно прочитать файл");
        }
        catch (Exception exp) {
            throw new ManagerSaveException("Ошибка при загрузке данных из файла", exp);
        }
        return fileBackedTasksManager;
    }

    public FileBackedTasksManager(File file) throws IOException, ManagerSaveException {
        super("example.csv");
        save();
    }

    @Override
    public void addTask(Task task) throws IOException, ManagerSaveException {
        super.addTask(task);
        save();
    }

    @Override
    public void addEpic(Epic epic) throws IOException, ManagerSaveException {
        super.addEpic(epic);
        save();
    }

    @Override
    public void addSubtask(Subtask subtask) throws IOException, ManagerSaveException {
        super.addSubtask(subtask);
        save();
    }

    @Override
    public void updateTask(Task task) throws IOException, ManagerSaveException {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) throws IOException, ManagerSaveException {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) throws IOException, ManagerSaveException {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public List<Task> getAllEpic() throws IOException, ManagerSaveException {
        save();
        return super.getAllEpic();
    }

    @Override
    public List<Task> getAllTask() throws IOException, ManagerSaveException {
        save();
        return super.getAllTask();
    }

    @Override
    public List<Task> getAllSubtask() throws IOException, ManagerSaveException {
        save();
        return super.getAllSubtask();
    }

    @Override
    public void removeAllEpic() throws IOException, ManagerSaveException {
        super.removeAllEpic();
        save();
    }

    @Override
    public void removeAllTask() throws IOException, ManagerSaveException {
        super.removeAllTask();
        save();
    }

    @Override
    public void removeAllSubtask() throws IOException, ManagerSaveException {
        super.removeAllSubtask();
        save();
    }

    @Override
    public Task getTaskById(int taskId) throws IOException, ManagerSaveException {
        save();
        return super.getTaskById(taskId);
    }

    @Override
    public void deletionSubtaskById(int id) throws IOException, ManagerSaveException {
        super.deletionSubtaskById(id);
        save();
    }

    @Override
    public List<Subtask> getEpicTasks(int epicId) throws IOException, ManagerSaveException {
        save();
        return super.getEpicTasks(epicId);
    }

    @Override
    public void updateEpicStatus(int epicId) throws IOException, ManagerSaveException {
        super.updateEpicStatus(epicId);
        save();
    }

    @Override
    public List<Task> getHistory() throws IOException, ManagerSaveException {
        List<Task> tasks =  super.getHistory();
        save();
        return tasks;
    }

    public Object getTasks() {
        return tasks;
    }

    public Object getEpics() {
        return epics;
    }

    public Object getSubtasks() {
        return subTasks;
    }
}
