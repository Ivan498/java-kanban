package manager.file;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.Types;
import manager.HistoryManager;
import manager.Status;

import java.util.ArrayList;
import java.util.List;

public class CSVFormatHandler {
    private static final String DELIMITER = ",";

    String toString(Task task){
        //1,TASK,Task1,NEW,Description task1,
        String result = task.getId() + DELIMITER +
        task.getTypes() + DELIMITER +
        task.getName() + DELIMITER +
        task.getStatus() + DELIMITER +
        task.getDescription() + DELIMITER;

        if (task.getTypes() == Types.SUBTASK){
            result = result + ((Subtask) task).getEpicId();
        }
        return result;
    }

    public Task fromString(String value){
        String[] parts = value.split(DELIMITER);

        String name = parts[0];
        String description = parts[1];
        Status status = Status.valueOf(parts[2]);
        int epicId = Integer.parseInt(parts[3]);
        Types types = Types.valueOf(parts[4]);

        if (types == Types.SUBTASK && parts.length > 5){
            return new Subtask(name, description, status, epicId, types);
        }
        else if (types == Types.EPIC){
            return new Epic(name, description, types);
        }

        return new Task(name, description, status);
    }

    static String historyToString(HistoryManager manager){
        List<String> result = new ArrayList<>();

        for (Task task : manager.getHistory()) {
            result.add(String.valueOf(task.getId()));
        }
        return String.join(DELIMITER , result);
    }

    static List<Integer> historyFromString(String value){
        List<Integer> historyNumber = new ArrayList<>();

        String[] values = value.split(DELIMITER);

        for (String history : values) {
            historyNumber.add(Integer.valueOf(history));
        }
        return historyNumber;
    }

    public String getHeader(){
        return "id,type,name,status,description,epic";
    }
}
