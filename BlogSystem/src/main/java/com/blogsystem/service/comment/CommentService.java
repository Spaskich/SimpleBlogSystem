package com.blogsystem.service.comment;

import com.blogsystem.model.comment.CommentViewModel;
import com.blogsystem.model.comment.PostCommentModel;

import java.util.List;

/**
 * Created by Spaskich on 4.5.2017 г..
 */
public interface CommentService {

    List<CommentViewModel> loadCommentsByArticle(long articleId);

    void postComment(PostCommentModel postCommentModel);
}
