package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void search_WhenQueryIsEmpty_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> searchService.search(null));
        assertThrows(IllegalArgumentException.class, () -> searchService.search("   "));
    }

    @Test
    void search_WhenNoMatches_ReturnsEmptyCollection() {
        when(storageService.getAllSearchables()).thenReturn(List.of(
                new SimpleProduct("Яблоко", 50),
                new Article("Выбор ноутбука", "Советы")
        ));

        Collection<SearchResult> results = searchService.search("апельсин");

        assertTrue(results.isEmpty());
    }

    @Test
    void search_WhenProductMatches_ReturnsProductResult() {
        Product product = new SimpleProduct("Яблоко", 50);
        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        Collection<SearchResult> results = searchService.search("ябл");

        assertEquals(1, results.size());
        assertEquals("Яблоко", results.iterator().next().getProductName());
    }

    @Test
    void search_WhenArticleMatches_ReturnsArticleResult() {
        Article article = new Article("Выбор ноутбука", "Советы");
        when(storageService.getAllSearchables()).thenReturn(List.of(article));

        Collection<SearchResult> results = searchService.search("ноут");

        assertEquals(1, results.size());
        assertEquals("Выбор ноутбука", results.iterator().next().getProductName());
    }

    @Test
    void search_IsCaseInsensitive() {
        Product product = new SimpleProduct("Яблоко", 50);
        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        Collection<SearchResult> results = searchService.search("ЯБЛ");

        assertEquals(1, results.size());
    }
}