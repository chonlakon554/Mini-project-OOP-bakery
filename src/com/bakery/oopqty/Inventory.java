package com.bakery.oopqty;

import java.util.*;

public class Inventory {
    private final List<Product> items = new ArrayList<>();
    private int nextId = 1;

    public Bread addBread(String name, double price, int quantity) {
        Bread b = new Bread(nextId++, name, price, quantity);
        items.add(b);
        return b;
    }

    public Drink addDrink(String name, double price, int quantity, Drink.Size size) {
        Drink d = new Drink(nextId++, name, price, quantity, size);
        items.add(d);
        return d;
    }

    public boolean removeById(int id) { return items.removeIf(p -> p.getId() == id); }

    public Optional<Product> findById(int id) {
        return items.stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean stockIn(int id, int amount) {
        var op = findById(id);
        if (op.isEmpty()) return false;
        op.get().stockIn(amount);
        return true;
    }

    public boolean stockOut(int id, int amount) {
        var op = findById(id);
        return op.isPresent() && op.get().stockOut(amount);
    }

    public List<Product> listAll() { return Collections.unmodifiableList(items); }
}
