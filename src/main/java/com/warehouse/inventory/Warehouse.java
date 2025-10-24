package com.warehouse.inventory;

import java.util.*;

public class Warehouse {
    private String id;
    private Map<String, Product> products = new HashMap<>();
    private List<AlertService> observers = new ArrayList<>();

    public Warehouse(String id) {
        this.id = id;
    }

    public void addObserver(AlertService observer) {
        observers.add(observer);
    }

    public void addProduct(Product product) {
        if(products.containsKey(product.getId())) {
            System.out.println("⚠ Product ID already exists. Skipping addition.");
            return;
        }
        products.put(product.getId(), product);
    }

    public Product getProduct(String productId) {
        if(!products.containsKey(productId)) {
            throw new IllegalArgumentException("Product ID '" + productId + "' not found!");
        }
        return products.get(productId);
    }

    public void receiveShipment(String productId, int qty) {
        Product p = getProduct(productId);
        p.increaseQuantity(qty);
        notifyObservers(p);
    }

    public void fulfillOrder(String productId, int qty) {
        Product p = getProduct(productId);
        p.decreaseQuantity(qty);
        notifyObservers(p);
    }

    private void notifyObservers(Product product) {
        for (AlertService observer : observers) {
            observer.onStockChange(product);
        }
    }

    public void showAllProducts() {
        System.out.println("\n--- All Products ---");
        for(Product p : products.values()) {
            System.out.println("ID: " + p.getId() + ", Name: " + p.getName() 
                               + ", Qty: " + p.getQuantity() + ", Threshold: " + p.getReorderThreshold());
        }
    }
}
