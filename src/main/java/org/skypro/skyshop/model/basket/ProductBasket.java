package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products;

    public ProductBasket(){
        this.products = new HashMap<>();
    }

    public void addProduct(UUID id){
        if(id == null) {
            throw new IllegalArgumentException("id продукта не может быть пустым");
        }
        products.merge(id, 1, Integer :: sum);//увеличение количества на 1
    }

    public Map<UUID, Integer> getProducts(){
        return Collections.unmodifiableMap(products);
    }
}
