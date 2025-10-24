package service;

import model.Product;
import java.util.List;

public class InventoryAlertThread extends Thread {
    private ProductService productService;
    private int checkInterval;
    private int lowStockThreshold;
    private volatile boolean running;
    
    public InventoryAlertThread(ProductService productService, int checkInterval, int lowStockThreshold) {
        this.productService = productService;
        this.checkInterval = checkInterval * 1000;
        this.lowStockThreshold = lowStockThreshold;
        this.running = true;
        this.setDaemon(true);
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                List<Product> lowStockProducts = productService.getLowStockProducts(lowStockThreshold);
                
                if (!lowStockProducts.isEmpty()) {
                    synchronized (System.out) {
                        System.out.println("\n?? LOW STOCK ALERT! The following products are running low:");
                        lowStockProducts.forEach(p -> 
                            System.out.printf("   %s (ID: %s) - Only %d left!%n", 
                                p.getName(), p.getId(), p.getQuantity()));
                        System.out.println("Please restock these items soon!\n");
                    }
                }
                
                Thread.sleep(checkInterval);
                
            } catch (InterruptedException e) {
                System.out.println("Inventory alert thread interrupted");
                break;
            } catch (Exception e) {
                System.out.println("Error in inventory alert: " + e.getMessage());
            }
        }
    }
    
    public void stopAlert() {
        running = false;
        this.interrupt();
    }
}
