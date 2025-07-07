package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;
    private final Map<UUID, Product> availableProducts;

    public StorageService(){
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        createTestData();
        this.availableProducts = new HashMap<>();
    }

    public Collection<Product> getProducts(){
        return Collections.unmodifiableCollection(products.values());
    }

    public Collection<Article> getArticles(){
        return Collections.unmodifiableCollection(articles.values());
    }

    private void createTestData(){
        SimpleProduct apple = new SimpleProduct("Яблоко", 50);
        products.put(apple.getID(),apple);
        DiscountedProduct banana = new DiscountedProduct("Банан", 70, 10);
        products.put(banana.getID(),banana);
        FixPriceProduct orange = new FixPriceProduct("Апельсин");
        products.put(orange.getID(),orange);
        Article article = new Article("Выбор ноутбука", "На что стоит обращать внимание при выборе ноутбука?");
        articles.put(article.getID(),article);
    }

    public Collection<Searchable> getAllSearchables(){
        List<Searchable> result = new ArrayList<>();
        result.addAll(getProducts());
        result.addAll(getArticles());
        return result;
    }

    public Optional<Product> getProductsById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }
}
