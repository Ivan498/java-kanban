package Tasks;

import manager.Status;

public class Subtask extends Task {
    public int epicId;


    public Subtask(String name, String description, Status status, int epicId, Types types) {
        super(name, description,status);
        this.epicId = epicId;
        this.types = Types.SUBTASK;
    }

    public Subtask(int id, Types types, String name, Status status, String description, String epic) {
        super(id, types, name,status, description, epic);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }



    @Override
    public String toString() {
        return "Сабтаски{" +
                "id='" + id + '\'' +
                "Имя='" + name + '\'' +
                "Описание='" + description + '\'' +
                "Статус='" + status + '\'' +'}';
    }
}
