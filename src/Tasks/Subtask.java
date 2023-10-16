package Tasks;

import manager.Status;

public class Subtask extends Task {
    public int epicId;


    public Subtask(String name, String description, Status status, int epicId, Types types) {
        super(name, description,status);
        this.epicId = epicId;
        this.types = Types.SUBTASK;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }



    @Override
    public String toString() {
        return "Subtask{" +
                "id='" + id + '\'' +
                "Имя='" + name + '\'' +
                "Описание='" + description + '\'' +
                "Статус='" + status + '\'' +'}';
    }
}
