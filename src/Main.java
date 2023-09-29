import Tasks.Epic;
import Tasks.Subtask;
import manager.Managers;
import Tasks.Task;
import manager.HistoryManager;
import manager.Status;
import manager.TaskManager;

public class Main {
    public static void main(String[] args) {
        HistoryManager historyManager = Managers.getHistoryDefault();
        TaskManager taskManager = Managers.getDefault();
        Task task1 = new Task("Задача №1", "...", Status.DONE);
        taskManager.addTask(task1);
        taskManager.addTask(new Task("Задача №2", "...", Status.NEW));

        Epic epic1 = new Epic("Эпик №1","Найти работу");
        taskManager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача №1","Зайти на сайт", Status.NEW,epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Подзадача №2","Написать работадателям", Status.DONE,epic1.getId());
        taskManager.addSubtask(subtask2);
        Subtask subtask3 = new Subtask("Подзадача №3","Прийте на работу", Status.DONE,epic1.getId());
        taskManager.addSubtask(subtask2);

        taskManager.updateSubtask(subtask3);

        Epic epic2 = new Epic("Эпик №2","Заказать пиццу");
        taskManager.addEpic(epic2);

        System.out.println("Все задачи: " + taskManager.getAllTask());
        System.out.println("Все эпики: " + taskManager.getAllEpic());
        System.out.println("Все сабтаски: " + taskManager.getAllSubtask());

        System.out.println("Первая задача: " + taskManager.getTaskById(1));
        System.out.println("Вторая задача: " + taskManager.getTaskById(2));

        System.out.println("История: " + historyManager.getHistory());

        taskManager.removeAllTask();
        taskManager.removeAllEpic();
        taskManager.removeAllSubtask();

        System.out.println("Все задачи" + taskManager.getAllTask());
        System.out.println("Все эпики: " + taskManager.getAllEpic());
        System.out.println("Все сабтаски: " + taskManager.getAllSubtask());

        historyManager.remove(1);
        System.out.println("История: " + historyManager.getHistory());

    }
}
