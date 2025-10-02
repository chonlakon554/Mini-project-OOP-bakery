package com.bakery.oopqty;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderLine> lines = new ArrayList<>();

    public void add(Product p, int qty) { lines.add(new OrderLine(p, qty)); }

    public double getTotal() {
        double sum = 0;
        for (OrderLine l : lines) sum += l.lineTotal();
        return sum;
    }

    public boolean isEmpty() { return lines.isEmpty(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OrderLine l : lines) sb.append(l).append("\n");
        sb.append(String.format("TOTAL: %.2f", getTotal()));
        return sb.toString();
    }
}
