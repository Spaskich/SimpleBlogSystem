package com.blogsystem.repository.comment;

import com.blogsystem.entity.Article;
import com.blogsystem.entity.Comment;
import com.blogsystem.repository.article.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    private static Article article = new Article();

    @Before
    public void setUp() throws Exception {


        this.articleRepository.save(article);

        Comment comment1 = new Comment();
        comment1.setArticle(article);
        comment1.setText("comment1");
        comment1.setDatePublished(new Date());

        Comment comment2 = new Comment();
        comment2.setArticle(article);
        comment2.setText("comment2");
        comment2.setDatePublished(new Date());

        this.commentRepository.save(comment1);
        this.commentRepository.save(comment2);
    }

    @Test
    public void findAllByArticleOrderByDatePublishedDescIdDesc() throws Exception {

        //Act
        List<Comment> comments = this.commentRepository.findAllByArticleOrderByDatePublishedDescIdDesc(article);
        String lastCommentText = comments.get(0).getText();

        //Assert
        assertEquals(lastCommentText, "comment2");
    }

}