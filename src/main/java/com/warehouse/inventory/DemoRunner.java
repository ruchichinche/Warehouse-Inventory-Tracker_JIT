package com.warehouse.inventory;

import java.util.Scanner;

public class DemoRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create warehouse
        System.out.print("Enter Warehouse ID: ");
        String warehouseId = sc.nextLine();
        Warehouse wh = new Warehouse(warehouseId);
        wh.addObserver(new ConsoleAlertService());
        System.out.println("\n✅ Warehouse '" + warehouseId + "' created successfully!");

        while (true) {
            System.out.println("\n========= WAREHOUSE MENU =========");
            System.out.println("1. Add Product");
            System.out.println("2. Receive Shipment");
            System.out.println("3. Fulfill Order");
            System.out.println("4. Show Product Details");
            System.out.println("5. Show All Products");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Enter a number 1–6.");
                continue;
            }

            switch (choice) {
                case 1 :{
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Quantity: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Reorder Threshold: ");
                    int threshold = Integer.parseInt(sc.nextLine());
                    Product p = new Product(id, name, qty, threshold);
                    wh.addProduct(p);
                    System.out.println("✅ Product added successfully!");
                }
                case 2 : {
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Shipment Quantity: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    try {
                        wh.receiveShipment(id, qty);
                        System.out.println("📦 Shipment received successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                }
                case 3 :{
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Order Quantity: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    try {
                        wh.fulfillOrder(id, qty);
                        System.out.println("🛒 Order fulfilled successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                }
                case 4 :{
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    try {
                        Product p = wh.getProduct(id);
                        System.out.println("\n--- Product Details ---");
                        System.out.println("ID: " + p.getId());
                        System.out.println("Name: " + p.getName());
                        System.out.println("Quantity: " + p.getQuantity());
                        System.out.println("Reorder Threshold: " + p.getReorderThreshold());
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                }
                case 5 : wh.showAllProducts();
                case 6 : {
                    System.out.println("👋 Exiting program. Goodbye!");
                    sc.close();
                    return;
                }
                default : System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
