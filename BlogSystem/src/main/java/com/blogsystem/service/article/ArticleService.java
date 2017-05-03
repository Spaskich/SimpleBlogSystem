package com.blogsystem.service.article;

import com.blogsystem.entity.User;
import com.blogsystem.model.article.EditArticleModel;
import com.blogsystem.model.article.PublishArticleModel;

import java.util.List;

/**
 * Created by Spaskich on 2.5.2017 г..
 */
public interface ArticleService {

    void publish(PublishArticleModel publishArticleModel);

    void edit(EditArticleModel editArticleModel);

    List<EditArticleModel> loadAll();

    List<EditArticleModel> loadAllByAuthor(User author);

    EditArticleModel loadOneById(Long id);
}
