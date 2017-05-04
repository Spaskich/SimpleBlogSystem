package com.blogsystem.service.article;

import com.blogsystem.entity.Article;
import com.blogsystem.entity.User;
import com.blogsystem.model.article.EditArticleModel;
import com.blogsystem.model.article.PublishArticleModel;
import com.blogsystem.repository.article.ArticleRepository;
import com.blogsystem.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Spaskich on 2.5.2017 г..
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void publish(PublishArticleModel publishArticleModel) {
        Article article = this.modelMapper.map(publishArticleModel, Article.class);
        article.setDatePublished(new Date());
        article.setAuthor(this.userRepository.findOneByUsername(publishArticleModel.getAuthor()));

        this.articleRepository.save(article);
    }

    @Override
    public void edit(EditArticleModel editArticleModel) {
        Article article = this.articleRepository.findOneById(editArticleModel.getId());

        article.setTitle(editArticleModel.getTitle());
        article.setText(editArticleModel.getText());

        this.articleRepository.saveAndFlush(article);
    }

    @Override
    public void delete(EditArticleModel editArticleModel) {

        Article article = this.articleRepository.findOneById(editArticleModel.getId());

        this.articleRepository.delete(editArticleModel.getId());
    }

    @Override
    public List<EditArticleModel> loadAll() {

        List<EditArticleModel> articleModels = new ArrayList<>();

        List<Article> articles = this.articleRepository.findAllByOrderByDatePublishedDesc();
        for (Article article : articles) {
            articleModels.add(this.modelMapper.
                    map(article, EditArticleModel.class)
            );
        }

        return articleModels;
    }

    @Override
    public List<EditArticleModel> loadAllByAuthor(User author) {

        List<EditArticleModel> articleModels = new ArrayList<>();

        List<Article> articles = this.articleRepository.getAllByAuthor(author);
        for (Article article : articles) {
            articleModels.add(this.modelMapper.map(article, EditArticleModel.class));
        }

        return articleModels;
    }

    @Override
    public EditArticleModel loadOneById(Long id) {
        Article article = this.articleRepository.findOneById(id);

        EditArticleModel editArticleModel = this.modelMapper.map(article, EditArticleModel.class);

        return editArticleModel;
    }
}
