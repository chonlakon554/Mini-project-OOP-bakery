package com.bakery.oopqty;

public class OrderLine {
    private final Product product;
    private final int quantity;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double lineTotal() { return product.getPrice() * quantity; }

    @Override
    public String toString() {
        return String.format("%s x %d = %.2f", product.toString(), quantity, lineTotal());
    }
}
