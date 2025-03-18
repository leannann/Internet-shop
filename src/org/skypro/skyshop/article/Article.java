package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable {

    String title;
    String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String getSearchTerm() {
        return title + " " + content;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String toString() {
        return title + "\n" + content;
    }
}
