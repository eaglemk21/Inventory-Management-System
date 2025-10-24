package main;

import model.Product;
import model.Supplier;
import service.ProductService;
import service.SupplierService;
import service.InventoryAlertThread;
import exception.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagementSystem {
    private ProductService productService;
    private SupplierService supplierService;
    private InventoryAlertThread alertThread;
    private Scanner scanner;
    
    public InventoryManagementSystem() {
        this.productService = new ProductService();
        this.supplierService = new SupplierService();
        this.scanner = new Scanner(System.in);
        
        this.alertThread = new InventoryAlertThread(productService, 30, 10);
        this.alertThread.start();
    }
    
    public void displayMenu() {
        System.out.println("\n?? INVENTORY MANAGEMENT SYSTEM");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. View Low Stock Products");
        System.out.println("7. Generate Reports");
        System.out.println("8. Supplier Management");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }
    
    public void addProduct() {
        try {
            System.out.println("\n? ADD NEW PRODUCT");
            
            System.out.print("Enter Product ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Category: ");
            String category = scanner.nextLine();
            
            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter Price: ");
            double price = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter Supplier ID: ");
            String supplierId = scanner.nextLine();
            
            Product product = new Product(id, name, category, quantity, price, supplierId);
            productService.addProduct(product);
            
            System.out.println("? Product added successfully!");
            
        } catch (NumberFormatException e) {
            System.out.println("? Invalid number format. Please enter valid numbers for quantity and price.");
        } catch (InventoryException e) {
            System.out.println("? Error: " + e.getMessage());
        }
    }
    
    public void viewAllProducts() {
        System.out.println("\n?? ALL PRODUCTS");
        List<Product> products = productService.getAllProducts();
        
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            products.forEach(System.out::println);
        }
    }
    
    public void searchProduct() {
        System.out.println("\n?? SEARCH PRODUCT");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Category");
        System.out.print("Choose search type: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = scanner.nextLine();
                    Product product = productService.searchProductById(id);
                    System.out.println("? Product found: " + product);
                    break;
                    
                case 2:
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    List<Product> nameResults = productService.searchProductsByName(name);
                    displaySearchResults(nameResults);
                    break;
                    
                case 3:
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    List<Product> categoryResults = productService.searchProductsByCategory(category);
                    displaySearchResults(categoryResults);
                    break;
                    
                default:
                    System.out.println("? Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("? Please enter a valid number.");
        } catch (InventoryException e) {
            System.out.println("? " + e.getMessage());
        }
    }
    
    private void displaySearchResults(List<Product> results) {
        if (results.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Found " + results.size() + " product(s):");
            results.forEach(System.out::println);
        }
    }
    
    public void updateProduct() {
        try {
            System.out.println("\n?? UPDATE PRODUCT");
            System.out.print("Enter Product ID to update: ");
            String id = scanner.nextLine();
            
            Product existing = productService.searchProductById(id);
            System.out.println("Current product: " + existing);
            
            System.out.print("Enter new Name (current: " + existing.getName() + "): ");
            String name = scanner.nextLine();
            
            System.out.print("Enter new Category (current: " + existing.getCategory() + "): ");
            String category = scanner.nextLine();
            
            System.out.print("Enter new Quantity (current: " + existing.getQuantity() + "): ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter new Price (current: " + existing.getPrice() + "): ");
            double price = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter new Supplier ID (current: " + existing.getSupplierId() + "): ");
            String supplierId = scanner.nextLine();
            
            Product updated = new Product(id, name, category, quantity, price, supplierId);
            productService.updateProduct(id, updated);
            
            System.out.println("? Product updated successfully!");
            
        } catch (NumberFormatException e) {
            System.out.println("? Invalid number format.");
        } catch (InventoryException e) {
            System.out.println("? " + e.getMessage());
        }
    }
    
    public void deleteProduct() {
        try {
            System.out.println("\n??? DELETE PRODUCT");
            System.out.print("Enter Product ID to delete: ");
            String id = scanner.nextLine();
            
            Product product = productService.searchProductById(id);
            System.out.println("Product to delete: " + product);
            
            System.out.print("Are you sure? (yes/no): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("yes")) {
                productService.deleteProduct(id);
                System.out.println("? Product deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }
            
        } catch (InventoryException e) {
            System.out.println("? " + e.getMessage());
        }
    }
    
    public void viewLowStockProducts() {
        System.out.println("\n?? LOW STOCK PRODUCTS");
        List<Product> lowStock = productService.getLowStockProducts(10);
        
        if (lowStock.isEmpty()) {
            System.out.println("No low stock products. Good job!");
        } else {
            System.out.println("Found " + lowStock.size() + " product(s) with low stock:");
            lowStock.forEach(p -> System.out.printf("   %s (ID: %s) - Only %d left!%n", 
                p.getName(), p.getId(), p.getQuantity()));
        }
    }
    
    public void generateReports() {
        System.out.println("\n?? REPORTS & STATISTICS");
        
        double totalValue = productService.getTotalInventoryValue();
        System.out.printf("Total Inventory Value: $%.2f%n", totalValue);
        
        Map<String, Long> categoryCount = productService.getCategoryWiseCount();
        System.out.println("\nCategory-wise Product Count:");
        categoryCount.forEach((category, count) -> 
            System.out.printf("   %s: %d product(s)%n", category, count));
        
        List<Product> allProducts = productService.getAllProducts();
        System.out.println("\nTotal Products: " + allProducts.size());
    }
    
    public void supplierManagement() {
        System.out.println("\n?? SUPPLIER MANAGEMENT");
        System.out.println("1. View All Suppliers");
        System.out.println("2. Add New Supplier");
        System.out.print("Choose an option: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    List<Supplier> suppliers = supplierService.getAllSuppliers();
                    if (suppliers.isEmpty()) {
                        System.out.println("No suppliers found.");
                    } else {
                        System.out.println("All Suppliers:");
                        suppliers.forEach(System.out::println);
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter Supplier ID: ");
                    String id = scanner.nextLine();
                    
                    System.out.print("Enter Supplier Name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter Contact Person: ");
                    String contact = scanner.nextLine();
                    
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    
                    Supplier supplier = new Supplier(id, name, contact, email, phone);
                    supplierService.addSupplier(supplier);
                    System.out.println("? Supplier added successfully!");
                    break;
                    
                default:
                    System.out.println("? Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("? Please enter a valid number.");
        } catch (InventoryException e) {
            System.out.println("? " + e.getMessage());
        }
    }
    
    public void shutdown() {
        System.out.println("\n?? Shutting down Inventory Management System...");
        alertThread.stopAlert();
        scanner.close();
        System.out.println("Thank you for using our system! ??");
    }
    
    public static void main(String[] args) {
        InventoryManagementSystem system = new InventoryManagementSystem();
        
        try {
            while (true) {
                system.displayMenu();
                String input = system.scanner.nextLine();
                
                switch (input) {
                    case "1":
                        system.addProduct();
                        break;
                    case "2":
                        system.viewAllProducts();
                        break;
                    case "3":
                        system.searchProduct();
                        break;
                    case "4":
                        system.updateProduct();
                        break;
                    case "5":
                        system.deleteProduct();
                        break;
                    case "6":
                        system.viewLowStockProducts();
                        break;
                    case "7":
                        system.generateReports();
                        break;
                    case "8":
                        system.supplierManagement();
                        break;
                    case "9":
                        system.shutdown();
                        return;
                    default:
                        System.out.println("? Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("? Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
