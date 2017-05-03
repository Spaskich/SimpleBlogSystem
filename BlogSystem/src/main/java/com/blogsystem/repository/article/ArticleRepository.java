package com.blogsystem.repository.article;

import com.blogsystem.entity.Article;
import com.blogsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spaskich on 2.5.2017 г..
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Article findOneById(Long id);

    Article findOneByTitle(String title);

    List<Article> findAllByOrderByDatePublishedDesc();

    List<Article> getAllByAuthor(User author);
}
