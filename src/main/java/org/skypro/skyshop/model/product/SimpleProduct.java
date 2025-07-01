package org.skypro.skyshop.model.product;

public class SimpleProduct extends Product {
    private final int cost;


    public SimpleProduct(String name, int cost) {
        super(name);
        if (cost <= 0) {
            throw new IllegalArgumentException("Цена не может быть меньше или равна 0");
        }
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return getName() + ": " + getCost();
    }
}
