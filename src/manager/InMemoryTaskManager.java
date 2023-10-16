package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    protected HashMap<Integer, Task> tasks = new HashMap<>();
    protected HashMap<Integer, Epic> epics = new HashMap<>();
    protected HashMap<Integer, Subtask> subTasks = new HashMap<>();

    protected HistoryManager historyManager = Managers.getHistoryDefault();
    private int nextId = 0;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }
    @Override
    public void addTask(Task task) throws IOException {
        task.setId(++nextId);
        tasks.put(task.getId(), task);
    }

    @Override
    public void addEpic(Epic epic) throws IOException {
        epic.setId(++nextId);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void addSubtask(Subtask subtask) throws IOException {
        if(epics.containsKey((subtask.getEpicId())))
        subtask.setId(++nextId);
        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtasks(nextId);
        subTasks.put(nextId, subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    @Override
    public void updateTask(Task task) throws IOException {
        tasks.put(task.getId(),task);
    }

    @Override
    public void updateEpic(Epic epic) throws IOException {
        epics.put(epic.getId(), epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) throws IOException {
        subTasks.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    @Override
    public List<Task> getAllEpic() throws IOException {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Task> getAllTask() throws IOException {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Task> getAllSubtask() throws IOException {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void removeAllEpic() throws IOException {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void removeAllTask() throws IOException {
        tasks.clear();
    }

    @Override
    public void removeAllSubtask() throws IOException {
       epics.clear();
       subTasks.clear();
    }

    @Override
    public Task getTaskById(int taskId) throws IOException {
      Task task = tasks.get(taskId);
      historyManager.add(task);
      return task;
    }


    @Override
    public void deletionSubtaskById(int id) throws IOException {
        for (Epic epic : epics.values()) {
            if (epics.containsKey(id)) {
                epic.clearSubtask();
                return;
            }
        }
    }

    @Override
    public List<Subtask> getEpicTasks(int epicId) throws IOException {
        ArrayList<Subtask> result = new ArrayList<>();
        subTasks.values().forEach(subtask -> {
            if(epicId == subtask.getEpicId()){
            result.add(subtask);
        }
        });
        return result;
    }

    @Override
    public void updateEpicStatus(int epicId) throws IOException {
        Epic epic = epics.get(epicId);
        // Проверяем, есть ли подзадачи у эпика
        if (epic.getSubTasksIds().isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        // Проверяем статусы всех подзадач эпика
        boolean allSubtasksDone = true;
        for (Task subtask : subTasks.values()) {
            if (!subtask.getStatus().equals(Status.DONE)) {
                allSubtasksDone = false;
                break;
            }
        }

        // Рассчитываем статус эпика
        if (allSubtasksDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}
