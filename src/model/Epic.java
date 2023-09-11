package model;

import java.util.ArrayList;

public class Epic extends Task {
    public Epic(String name, String description, int id, int idSubtask, Status status) {
        super(name, description, id, status);
    }
    protected ArrayList<Subtask> subTasksIds = new ArrayList<>();

    public ArrayList<Subtask> getSubTasksIds() {
        return subTasksIds;
    }

    public void setSubTasksIds(ArrayList<Subtask> subTasksIds) {
        this.subTasksIds = subTasksIds;
    }

}