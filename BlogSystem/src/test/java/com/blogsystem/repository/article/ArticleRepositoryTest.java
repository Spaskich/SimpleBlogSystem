package com.blogsystem.repository.article;

import com.blogsystem.entity.Article;
import com.blogsystem.entity.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Spaskich on 5.5.2017 г..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Before
    public void setUp() throws Exception {

        Article article1 = new Article();
        article1.setDatePublished(new Date());
        article1.setTitle("article1");

        Article article2 = new Article();
        article2.setDatePublished(new Date());
        article1.setTitle("article2");

        this.articleRepository.save(article1);
        this.articleRepository.save(article2);
    }

    @Test
    public void findAllByOrderByDatePublishedDesc() throws Exception {

        //Act
        List<Article> articles = this.articleRepository.findAllByOrderByDatePublishedDesc();
        String lastArticleTitle = articles.get(0).getTitle();

        //Assert
        assertEquals(lastArticleTitle, "article2");
    }

}