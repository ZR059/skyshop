package org.skypro.skyshop.model.dto;

import org.skypro.skyshop.model.product.Product;

public final class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        if (quantity <= 0){
            throw new IllegalArgumentException("Количество продуктов должно быть больше 0");
        }
    }

    public Product getProduct() {
    return product;
    }

    public int getQuantity(){
        return quantity;
    }
}
