package model;
import java.util.ArrayList;
import java.util.List;


public class Status {
        public String  calculateStatus(Epic epic, List<Task> subtasks) {
            // Проверяем, есть ли подзадачи у эпика
            if (subtasks.isEmpty()) {
                return "NEW";
            }

            // Проверяем статусы всех подзадач эпика
            boolean allSubtasksDone = true;
            for (Task subtask : subtasks) {
                if (!subtask.getStatus().equals("DONE")) {
                    allSubtasksDone = false;
                    break;
                }
            }

            // Рассчитываем статус эпика
            if (allSubtasksDone) {
                return "DONE";
            } else {
                return "IN_PROGRESS";
            }
        }

}
