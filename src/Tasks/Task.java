package Tasks;

import manager.Status;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected Status status;
    protected Types types;
    protected String  epic;

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.types = Types.TASK;
    }
//id,type,name,status,description,epic
    public Task(int id,Types types,String name, Status status, String description,String epic){
        this.id = id;
        this.types = types;
        this.name = name;
        this.status = status;
        this.description = description;
        this.epic = epic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public String getEpic() {
        return epic;
    }

    public void setEpic(String epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return"Таски{" +
                "id='" + id + '\'' +
                "Имя='" + name + '\'' +
                "Описание='" + description + '\'' +
                "Статус='" + status + '\'' +'}';
    }

}
