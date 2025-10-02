package com.bakery.oopqty;

public class Bread extends Product {
    public Bread(int id, String name, double basePrice, int quantity) {
        super(id, name, basePrice, quantity);
    }
    @Override
    public String getCategory() { return "Bread"; }
}
