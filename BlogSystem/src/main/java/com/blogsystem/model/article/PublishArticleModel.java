package com.blogsystem.model.article;

import javax.validation.constraints.Size;

/**
 * Created by Spaskich on 2.5.2017 г..
 */
public class PublishArticleModel {

    @Size(min = 3, message = "The title must be at least 3 symbols long.")
    private String title;

    @Size(min = 150, message = "The article must be at least 150 symbols long.")
    private String text;

    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
