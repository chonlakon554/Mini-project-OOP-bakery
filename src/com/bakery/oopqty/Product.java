package com.bakery.oopqty;

public abstract class Product implements Pricable {
    private final int id;
    private String name;
    private double basePrice;
    private int quantity;

    protected Product(int id, String name, double basePrice, int quantity) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void stockIn(int amount) { if (amount > 0) this.quantity += amount; }
    public boolean stockOut(int amount) {
        if (amount <= 0) return false;
        if (quantity < amount) return false;
        this.quantity -= amount;
        return true;
    }

    public abstract String getCategory();

    @Override
    public double getPrice() { return basePrice; }

    @Override
    public String toString() {
        return String.format("%-3d | %-9s | %-14s | %7.2f | %5d",
                id, getCategory(), name, getPrice(), quantity);
    }
}
