package com.blogsystem.repository.comment;

import com.blogsystem.entity.Article;
import com.blogsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spaskich on 4.5.2017 г..
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArticleOrderByDatePublishedDescIdDesc(Article article);
}
