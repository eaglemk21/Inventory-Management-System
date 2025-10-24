package model;

public class Product {
    private String id;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private String supplierId;
    
    public Product() {}
    
    public Product(String id, String name, String category, int quantity, double price, String supplierId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.supplierId = supplierId;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Category: %s, Quantity: %d, Price: $%.2f, Supplier: %s",
                id, name, category, quantity, price, supplierId);
    }
    
    public String toCSV() {
        return String.join(",",
                id, name, category, 
                String.valueOf(quantity), 
                String.valueOf(price), 
                supplierId);
    }
}
