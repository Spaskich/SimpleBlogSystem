package com.blogsystem.model.comment;

/**
 * Created by Spaskich on 5.5.2017 г..
 */
public class PostCommentModel {

    private String text;

    private long articleId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
