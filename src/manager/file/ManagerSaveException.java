package manager.file;

public class ManagerSaveException extends Exception{
    public ManagerSaveException(String message) {
        super(message);
    }
    public ManagerSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
