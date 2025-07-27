package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.dto.UserBasket;
import org.skypro.skyshop.model.product.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket basket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProductById_WhenProductNotExists_ThrowsException() {
        UUID productId = UUID.randomUUID();
        when(storageService.getProductsById(productId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () ->
                basketService.addProductById(productId));
    }

    @Test
    void addProductById_WhenSimpleProductExists_AddsToBasket() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct("Яблоко", 50);
        when(storageService.getProductsById(productId)).thenReturn(Optional.of(product));

        basketService.addProductById(productId);

        verify(basket).addProduct(productId);
    }

    @Test
    void addProductById_WhenDiscountedProductExists_AddsToBasket() {
        UUID productId = UUID.randomUUID();
        Product product = new DiscountedProduct("Банан", 70, 10);
        when(storageService.getProductsById(productId)).thenReturn(Optional.of(product));

        basketService.addProductById(productId);

        verify(basket).addProduct(productId);
    }

    @Test
    void getUserBasket_WhenEmpty_ReturnsEmptyBasket() {
        when(basket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getItems().isEmpty());
        assertEquals(0.0, result.getTotal());
    }

    @Test
    void getUserBasket_WithSimpleProduct_ReturnsCorrectBasket() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct("Яблоко", 50);
        when(basket.getProducts()).thenReturn(Map.of(productId, 2));
        when(storageService.getProductsById(productId)).thenReturn(Optional.of(product));

        UserBasket result = basketService.getUserBasket();

        assertEquals(1, result.getItems().size());
        assertEquals(100.0, result.getTotal());
    }

    @Test
    void getUserBasket_WithDiscountedProduct_ReturnsCorrectBasket() {
        UUID productId = UUID.randomUUID();
        Product product = new DiscountedProduct("Банан", 70, 10);
        when(basket.getProducts()).thenReturn(Map.of(productId, 3));
        when(storageService.getProductsById(productId)).thenReturn(Optional.of(product));

        UserBasket result = basketService.getUserBasket();

        assertEquals(1, result.getItems().size());
        assertEquals(189.0, result.getTotal());
    }
}