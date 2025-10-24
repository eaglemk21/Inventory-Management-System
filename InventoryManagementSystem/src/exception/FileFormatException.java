package exception;

public class FileFormatException extends InventoryException {
    public FileFormatException(String message) {
        super("File Format Error: " + message);
    }
}
