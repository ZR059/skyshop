package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public final class Article implements Searchable {

    public final String title;
    private final String text;
    private final UUID id;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
        this.id = UUID.randomUUID();
    }

    String getTitle() {
        return title;
    }

    String getText() {
        return text;
    }

    @Override
    public UUID getID(){
        return id;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return title.equals(article.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
