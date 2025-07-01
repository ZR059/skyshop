package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 10;

    public FixPriceProduct(String name) {
        super(name);
    }

    public int getCost() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + " c фиксированной ценой: Фиксированная цена " + getCost();
    }
}
