package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.dto.BasketItem;
import org.skypro.skyshop.model.dto.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProductById(UUID id) {
        storageService.getProductsById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт с id: " + id + " не найден"));
        basket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketItems = basket.getProducts();

        List<BasketItem> items = basketItems.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductsById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Продукт, не обнаруженный на складе: " + id));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());
        return new UserBasket(items);
    }
}
