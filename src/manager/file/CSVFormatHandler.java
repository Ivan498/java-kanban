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
        task.getDescription() + DELIMITER +
        task.getEpic() + DELIMITER;
        return result;
    }


    static String historyToString(HistoryManager manager){
        List<String> result = new ArrayList<>();

        for (Task task : manager.getHistory()) {
            result.add(String.valueOf(task.getId()));
        }
        return String.join(DELIMITER , result);
    }
    public Task fromString(String value){
        String[] parts = value.split(",");
        //1,TASK,Task1,NEW,Description task1,
        int id = Integer.parseInt(parts[0]);
        Types types = Types.valueOf(parts[1]);
        String name = parts[2];
        Status status = Status.valueOf(parts[3]);
        String description = parts[4];
        String epicName = parts[5];

        if (types == Types.SUBTASK){
            return new Subtask(id, types, name, status, description,epicName);
        }
        else if (types == Types.EPIC){
            return new Epic(id, types, name, status, description,epicName);
        }

        return new Task(id, types, name, status, description,epicName);
    }
    static List<Integer> historyFromString(String value){
        List<Integer> historyNumber = new ArrayList<>();

        String[] values = value.split(",");

        for (String history : values) {
            historyNumber.add(Integer.valueOf(history));
        }
        return historyNumber;
    }
    public String getHeader(){
        return "id,type,name,status,description,epic";
    }
}
