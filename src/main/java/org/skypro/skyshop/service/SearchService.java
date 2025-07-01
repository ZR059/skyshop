package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService){
        this.storageService = storageService;
    }

    public Collection<SearchResult> search (String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }

        String lowerQuery = query.toLowerCase();

        return storageService.getAllSearchables().stream()
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(lowerQuery))
                .map(SearchResult :: fromSearchable)
                .collect(Collectors.toList());
    }

}
