package exception;

public class ProductNotFoundException extends InventoryException {
    public ProductNotFoundException(String message) {
        super("Product Not Found: " + message);
    }
}
