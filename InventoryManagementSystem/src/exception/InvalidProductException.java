package exception;

public class InvalidProductException extends InventoryException {
    public InvalidProductException(String message) {
        super("Invalid Product: " + message);
    }
}
