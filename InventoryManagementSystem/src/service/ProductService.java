package service;

import model.Product;
import util.FileUtil;
import exception.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private static final String PRODUCTS_FILE = "data/products.csv";
    private List<Product> products;
    
    public ProductService() {
        this.products = new ArrayList<>();
        loadProducts();
    }
    
    private void loadProducts() {
        try {
            List<String[]> records = FileUtil.readCSV(PRODUCTS_FILE);
            for (String[] record : records) {
                if (record.length >= 6) {
                    Product product = new Product(
                        record[0], record[1], record[2],
                        Integer.parseInt(record[3]),
                        Double.parseDouble(record[4]),
                        record[5]
                    );
                    products.add(product);
                }
            }
        } catch (IOException | FileFormatException e) {
            System.out.println("Warning: Could not load products: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid number format in products file");
        }
    }
    
    private void saveProducts() throws IOException {
        List<String[]> data = products.stream()
            .map(Product::toCSV)
            .map(csv -> csv.split(","))
            .collect(Collectors.toList());
        FileUtil.writeCSV(PRODUCTS_FILE, data);
    }
    
    public void addProduct(Product product) throws InventoryException {
        if (product.getId() == null || product.getId().trim().isEmpty()) {
            throw new InvalidProductException("Product ID cannot be empty");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new InvalidProductException("Product name cannot be empty");
        }
        if (product.getQuantity() < 0) {
            throw new InvalidProductException("Quantity cannot be negative");
        }
        if (product.getPrice() < 0) {
            throw new InvalidProductException("Price cannot be negative");
        }
        
        if (products.stream().anyMatch(p -> p.getId().equals(product.getId()))) {
            throw new InvalidProductException("Product with ID " + product.getId() + " already exists");
        }
        
        products.add(product);
        try {
            saveProducts();
        } catch (IOException e) {
            throw new InventoryException("Failed to save product: " + e.getMessage());
        }
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    public Product searchProductById(String id) throws ProductNotFoundException {
        return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ProductNotFoundException("ID: " + id));
    }
    
    public List<Product> searchProductsByName(String name) {
        return products.stream()
            .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    public List<Product> searchProductsByCategory(String category) {
        return products.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }
    
    public void updateProduct(String id, Product updatedProduct) throws InventoryException {
        Product existingProduct = searchProductById(id);
        
        if (updatedProduct.getQuantity() < 0) {
            throw new InvalidProductException("Quantity cannot be negative");
        }
        if (updatedProduct.getPrice() < 0) {
            throw new InvalidProductException("Price cannot be negative");
        }
        
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setSupplierId(updatedProduct.getSupplierId());
        
        try {
            saveProducts();
        } catch (IOException e) {
            throw new InventoryException("Failed to update product: " + e.getMessage());
        }
    }
    
    public void deleteProduct(String id) throws InventoryException {
        Product product = searchProductById(id);
        products.remove(product);
        
        try {
            saveProducts();
        } catch (IOException e) {
            throw new InventoryException("Failed to delete product: " + e.getMessage());
        }
    }
    
    public List<Product> getLowStockProducts(int threshold) {
        return products.stream()
            .filter(p -> p.getQuantity() < threshold)
            .collect(Collectors.toList());
    }
    
    public double getTotalInventoryValue() {
        return products.stream()
            .mapToDouble(p -> p.getQuantity() * p.getPrice())
            .sum();
    }
    
    public Map<String, Long> getCategoryWiseCount() {
        return products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.counting()
            ));
    }
}
