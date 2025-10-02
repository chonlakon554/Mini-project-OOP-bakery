package com.bakery.oopqty;

public class Drink extends Product {
    public enum Size { S, M, L }
    private Size size;

    public Drink(int id, String name, double basePrice, int quantity, Size size) {
        super(id, name, basePrice, quantity);
        this.size = size;
    }

    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }

    @Override
    public String getCategory() { return "Drink"; }

    @Override
    public double getPrice() {
        double base = getBasePrice();
        return switch (size) {
            case M -> base + 5;
            case L -> base + 10;
            default -> base;
        };
    }
}
