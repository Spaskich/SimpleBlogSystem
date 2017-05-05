package com.blogsystem.controller;

import com.blogsystem.entity.User;
import com.blogsystem.model.article.EditArticleModel;
import com.blogsystem.model.article.PublishArticleModel;
import com.blogsystem.model.comment.CommentViewModel;
import com.blogsystem.model.comment.PostCommentModel;
import com.blogsystem.service.article.ArticleService;
import com.blogsystem.service.comment.CommentService;
import com.blogsystem.service.user.UserService;
import com.blogsystem.web.jsonview.Views;
import com.blogsystem.web.model.AjaxResponseBody;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.text.resources.FormatData;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Spaskich on 2.5.2017 г..
 */
@Controller
public class ArticleController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles/publish")
    public String getNewArticlePage(@ModelAttribute PublishArticleModel publishArticleModel, Model model) {

        model.addAttribute("view", "article/publish-article");
        model.addAttribute("title", "Publish Article");


        return "default-page";
    }

    @PostMapping("/articles/publish")
    public String publishArticle(@Valid @ModelAttribute PublishArticleModel publishArticleModel, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "article/publish-article");

            return "default-page";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        publishArticleModel.setAuthor(user.getUsername());
        this.articleService.publish(publishArticleModel);

        return "redirect:/";
    }

    @GetMapping("/articles")
    public String getUsersPage(Model model) {
        List<EditArticleModel> articleModels = this.articleService.loadAll();

        model.addAttribute("articles", articleModels);
        model.addAttribute("title", "Articles");
        model.addAttribute("view", "article/articles");

        return "default-page";
    }

    @GetMapping("/articles/edit/{id}")
    public String getEditArticlePage(@PathVariable Long id, Model model) {

        EditArticleModel articleModel = this.articleService.loadOneById(id);

        model.addAttribute("article", articleModel);
        model.addAttribute("title", "Edit Article");
        model.addAttribute("view", "article/edit-article");

        return "default-page";
    }

    @PostMapping("/articles/edit/{id}")
    public String editArticle(@Valid @ModelAttribute EditArticleModel articleModel, BindingResult bindingResult, Model model, @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "article/edit-article");

        }

        this.articleService.edit(articleModel);

        return "redirect:/articles";
    }

    @GetMapping("/article/view/{id}")
    public String getArticlePage(@PathVariable Long id, Model model) {

        EditArticleModel article = this.articleService.loadOneById(id);

        model.addAttribute("article", article);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("view", "article/view");



        return "default-page";
    }

    @GetMapping("/articles/delete/{id}")
    public String getDeleteArticlePage(@PathVariable Long id, Model model) {

        EditArticleModel articleModel = this.articleService.loadOneById(id);

        model.addAttribute("article", articleModel);
        model.addAttribute("title", "Delete Article");
        model.addAttribute("view", "article/delete-article");

        return "default-page";
    }

    @PostMapping("/articles/delete/{id}")
    public String deleteArticle(@Valid @ModelAttribute EditArticleModel articleModel, Model model, @PathVariable Long id) {

        this.articleService.delete(articleModel);

        return "redirect:/articles";
    }

    @GetMapping("/api/article/{id}/comments")
    public String getComments(@PathVariable Long id, Model model) {

        List<CommentViewModel> commentViewModels = this.commentService.loadCommentsByArticle(id);

        model.addAttribute("comments", commentViewModels);

        return "article/comments";
    }

    //@JsonView(Views.Public.class)
    //@PostMapping("/api/article/{id}/comments")
    @RequestMapping(value = "/api/article/{id}/comments", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postComment(@PathVariable Long id, PostCommentModel commentModel) {

        commentModel.setArticleId(id);
        this.commentService.postComment(commentModel);

        AjaxResponseBody result = new AjaxResponseBody();
        result.setCode("200");
        result.setMsg("Comment posted successfully");

        return "article/comments";
    }
}
