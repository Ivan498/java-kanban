package service;

import model.Status;
import model.Task;
import model.Subtask;
import model.Epic;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

public class TaskTracker {
    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subTasks = new HashMap<>();
    public int nextId = 1;
    List<Task> allEpics = new ArrayList<>();
    List<Task> allTasks = new ArrayList<>();
    List<Task> allSubtasks = new ArrayList<>();

    public void addTask(Task task) {
        task.setId(++nextId);
        epics.put(task.getId(), (Epic) task);
    }

    public void addEpic(Epic task) {
        task.setId(++nextId);
        tasks.put(task.getId(), task);
    }

    public void addSubtask(Subtask task) {
        task.setId(++nextId);
        subTasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        epics.put(task.getId(), (Epic) task);
    }

    public void updateEpic(Epic task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubtask(Subtask task) {
        subTasks.put(task.getId(), task);
    }

    public List<Task> getAllEpic() {
        allEpics.addAll(epics.values());
        return allEpics;
    }
    public List<Task> getAllTask() {
        allTasks.addAll(tasks.values());
        return allTasks;
    }
    public List<Task> getAllSubtask() {
        allSubtasks.addAll(subTasks.values());
        return allSubtasks;
    }

    public void removeAllTasks(List<Task> allTasks) {
        allTasks.removeAll(epics.values());
        allTasks.removeAll(tasks.values());
        allTasks.removeAll(subTasks.values());
    }

    public Task getTaskById(int taskId) {
      return epics.get(taskId);
    }

    public void getTaskRemoveById(int taskId) {
        epics.remove(taskId);
    }

    public List<Subtask> getEpicTasks(int epicId) {
        ArrayList<Subtask> result = new ArrayList<>();
        subTasks.values().forEach(subtask -> {
            if(epicId == subtask.getEpicId()){
            result.add(subtask);
        }
        });
        return result;
    }
}
