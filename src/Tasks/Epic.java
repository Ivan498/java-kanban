package Tasks;

import manager.Status;

import java.util.ArrayList;

public class Epic extends Task {

    protected ArrayList<Integer> subTasksIds;

    public Epic(String name, String description , Types types) {
        super(name,description, Status.NEW);
        subTasksIds = new ArrayList<>();
        this.types = Types.TASK;
    }

    public Epic(int id, Types types, String name,Status status, String description, String epic) {
        super(id, types, name, status, description, epic);
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