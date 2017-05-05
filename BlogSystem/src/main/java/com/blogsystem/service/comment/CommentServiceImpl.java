package com.blogsystem.service.comment;

import com.blogsystem.entity.Article;
import com.blogsystem.entity.Comment;
import com.blogsystem.entity.User;
import com.blogsystem.model.comment.CommentViewModel;
import com.blogsystem.model.comment.PostCommentModel;
import com.blogsystem.repository.article.ArticleRepository;
import com.blogsystem.repository.comment.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Spaskich on 5.5.2017 г..
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CommentViewModel> loadCommentsByArticle(long articleId) {

        List<CommentViewModel> commentViewModels = new ArrayList<>();

        Article article = this.articleRepository.findOneById(articleId);

        List<Comment> comments = this.commentRepository.findAllByArticleOrderByDatePublishedDescIdDesc(article);
        for (Comment comment : comments) {

            commentViewModels.add(this.modelMapper.map(comment, CommentViewModel.class));
        }

        return commentViewModels;
    }

    @Override
    public void postComment(PostCommentModel postCommentModel) {
        Comment comment = new Comment();

        Article article = this.articleRepository.findOneById(postCommentModel.getArticleId());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        comment.setText(postCommentModel.getText());
        comment.setArticle(article);
        comment.setAuthor(user);
        comment.setDatePublished(new Date());

        this.commentRepository.save(comment);
    }
}
