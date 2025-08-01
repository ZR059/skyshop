package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.dto.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService)  {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @PostMapping("/basket/{id}")
    public String addProduct(@PathVariable UUID id){
        basketService.addProductById(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket(){
        return basketService.getUserBasket();
    }
}

