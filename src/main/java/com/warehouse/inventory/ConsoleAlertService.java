package com.warehouse.inventory;

public class ConsoleAlertService implements AlertService {
    @Override
    public void onStockChange(Product product) {
        if (product.getQuantity() <= product.getReorderThreshold()) {
            System.out.println("⚠ ALERT: Low stock for '" + product.getName() 
                               + "' — only " + product.getQuantity() + " left! Please reorder soon.");
        }
    }
}
