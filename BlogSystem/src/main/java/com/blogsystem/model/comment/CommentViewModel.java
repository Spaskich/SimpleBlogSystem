package com.blogsystem.model.comment;

import com.blogsystem.entity.Article;
import com.blogsystem.entity.User;

import java.util.Date;

/**
 * Created by Spaskich on 4.5.2017 г..
 */
public class CommentViewModel {

    private String text;

    private User author;

    private Article article;

    private Date datePublished;

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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
}
