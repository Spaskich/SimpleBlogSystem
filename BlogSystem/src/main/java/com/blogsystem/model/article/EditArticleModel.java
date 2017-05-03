package com.blogsystem.model.article;

import com.blogsystem.entity.User;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Spaskich on 2.5.2017 г..
 */
public class EditArticleModel {

    private long id;

    @Size(min = 3, message = "The title must be at least 3 symbols long.")
    private String title;

    @Size(min = 150, message = "The article must be at least 150 symbols long.")
    private String text;

    private User author;

    private Date datePublished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
}
