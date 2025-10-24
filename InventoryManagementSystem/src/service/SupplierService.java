package service;

import model.Supplier;
import util.FileUtil;
import exception.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SupplierService {
    private static final String SUPPLIERS_FILE = "data/suppliers.csv";
    private List<Supplier> suppliers;
    
    public SupplierService() {
        this.suppliers = new ArrayList<>();
        loadSuppliers();
    }
    
    private void loadSuppliers() {
        try {
            List<String[]> records = FileUtil.readCSV(SUPPLIERS_FILE);
            for (String[] record : records) {
                if (record.length >= 5) {
                    Supplier supplier = new Supplier(
                        record[0], record[1], record[2], record[3], record[4]
                    );
                    suppliers.add(supplier);
                }
            }
        } catch (IOException | FileFormatException e) {
            System.out.println("Warning: Could not load suppliers: " + e.getMessage());
        }
    }
    
    private void saveSuppliers() throws IOException {
        List<String[]> data = suppliers.stream()
            .map(Supplier::toCSV)
            .map(csv -> csv.split(","))
            .collect(Collectors.toList());
        FileUtil.writeCSV(SUPPLIERS_FILE, data);
    }
    
    public void addSupplier(Supplier supplier) throws InventoryException {
        if (supplier.getId() == null || supplier.getId().trim().isEmpty()) {
            throw new InventoryException("Supplier ID cannot be empty");
        }
        
        if (suppliers.stream().anyMatch(s -> s.getId().equals(supplier.getId()))) {
            throw new InventoryException("Supplier with ID " + supplier.getId() + " already exists");
        }
        
        suppliers.add(supplier);
        try {
            saveSuppliers();
        } catch (IOException e) {
            throw new InventoryException("Failed to save supplier: " + e.getMessage());
        }
    }
    
    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers);
    }
    
    public Supplier getSupplierById(String id) throws InventoryException {
        return suppliers.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new InventoryException("Supplier not found with ID: " + id));
    }
}
