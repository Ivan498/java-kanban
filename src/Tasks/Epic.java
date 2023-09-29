package Tasks;

import manager.Status;

import java.util.ArrayList;

public class Epic extends Task {
    protected ArrayList<Integer> subTasksIds;

    public Epic(String name, String description) {
        super(name,description, Status.NEW);
        subTasksIds = new ArrayList<>();
    }

    public ArrayList<Integer> getSubTasksIds() {
        return subTasksIds;
    }

    public void setSubTasksIds(ArrayList<Integer> subTasksIds) {
        this.subTasksIds = subTasksIds;
    }
    public void addSubtasks(int subtaskId){
        this.subTasksIds.add(subtaskId);
    }
    public void clearSubtask(){
        this.subTasksIds.clear();
    }

    @Override
    public String toString() {
        return "Эпик{" +
                "id='" + id + '\'' +
                "Имя='" + name + '\'' +
                "Описание='" + description + '\'' +
                "Статус='" + status + '\'' +'}';
    }

}