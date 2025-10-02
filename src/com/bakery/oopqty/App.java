package com.bakery.oopqty;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        seed(inv);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Inventory (Quantity, No Premium) ===");
            System.out.println("1) List all products");
            System.out.println("2) Add product");
            System.out.println("3) Remove product");
            System.out.println("4) Stock IN");
            System.out.println("5) Stock OUT");
            System.out.println("6) Sample Order");
            System.out.println("0) Exit");
            System.out.print("Select: ");
            String s = sc.nextLine().trim();

            switch (s) {
                case "1" -> list(inv);
                case "2" -> add(inv, sc);
                case "3" -> remove(inv, sc);
                case "4" -> stockIn(inv, sc);
                case "5" -> stockOut(inv, sc);
                case "6" -> sampleOrder(inv);
                case "0" -> { System.out.println("Bye."); return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private static void seed(Inventory inv) {
        inv.addBread("WholeWheat", 40, 8);
        inv.addBread("ButterRoll", 25, 12);
        inv.addDrink("Latte", 55, 10, Drink.Size.M);
    }

    private static void list(Inventory inv) {
        List<Product> all = inv.listAll();
        if (all.isEmpty()) { System.out.println("No items."); return; }
        System.out.println("ID  | Category  | Name           |  Price |  Qty ");
        System.out.println("------------------------------------------------");
        for (Product p : all) System.out.println(p);
    }

    private static void add(Inventory inv, Scanner sc) {
        System.out.print("Type (1=Bread, 2=Drink): ");
        String t = sc.nextLine().trim();
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        double price = readDouble(sc, "Base price: ");
        int qty = readInt(sc, "Quantity: ");

        if ("1".equals(t)) {
            System.out.println("Added -> " + inv.addBread(name, price, qty));
        } else if ("2".equals(t)) {
            System.out.print("Size (S/M/L): ");
            Drink.Size size = switch (sc.nextLine().trim().toUpperCase()) {
                case "M" -> Drink.Size.M;
                case "L" -> Drink.Size.L;
                default -> Drink.Size.S;
            };
            System.out.println("Added -> " + inv.addDrink(name, price, qty, size));
        } else {
            System.out.println("Unknown type.");
        }
    }

    private static void remove(Inventory inv, Scanner sc) {
        int id = readInt(sc, "ID to remove: ");
        System.out.println(inv.removeById(id) ? "Removed." : "Not found.");
    }

    private static void stockIn(Inventory inv, Scanner sc) {
        int id = readInt(sc, "ID: ");
        int amt = readInt(sc, "Amount IN: ");
        System.out.println(inv.stockIn(id, amt) ? "Stocked IN." : "Not found.");
    }

    private static void stockOut(Inventory inv, Scanner sc) {
        int id = readInt(sc, "ID: ");
        int amt = readInt(sc, "Amount OUT: ");
        boolean ok = inv.stockOut(id, amt);
        if (!ok) System.out.println("Not found or insufficient quantity.");
        else System.out.println("Stocked OUT.");
    }

    private static void sampleOrder(Inventory inv) {
        Order order = new Order();
        for (Product p : inv.listAll()) order.add(p, 1);
        if (order.isEmpty()) System.out.println("Order empty.");
        else System.out.println(order);
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("กรุณากรอกจำนวนเต็ม"); }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("กรุณากรอกเลขทศนิยมได้"); }
        }
    }
}
