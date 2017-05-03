package com.blogsystem.controller;

import com.blogsystem.entity.User;
import com.blogsystem.model.article.EditArticleModel;
import com.blogsystem.model.article.PublishArticleModel;
import com.blogsystem.service.article.ArticleService;
import com.blogsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private ArticleService articleService;

    @GetMapping("/articles/publish")
    public String getNewArticlePage(@ModelAttribute PublishArticleModel publishArticleModel, Model model) {

        model.addAttribute("view", "article/publish-article");


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

    @GetMapping("/articles/all")
    public String getUsersPage(Model model) {
        List<EditArticleModel> articleModels = this.articleService.loadAll();

        model.addAttribute("articles", articleModels);
        model.addAttribute("view", "article/articles");

        return "default-page";
    }

    @GetMapping("/articles/edit/{id}")
    public String getEditArticlePage(@PathVariable Long id, Model model) {

        EditArticleModel articleModel = this.articleService.loadOneById(id);

        model.addAttribute("article", articleModel);
        model.addAttribute("view", "article/edit-article");

        return "default-page";
    }

    @PostMapping("/articles/edit/{id}")
    public String editArticle(@Valid @ModelAttribute EditArticleModel articleModel, BindingResult bindingResult, Model model, @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "article/edit-article");

        }

        this.articleService.edit(articleModel);

        return "redirect:/articles/all";
    }

    @GetMapping("/article/view/{id}")
    public String getArticlePage(@PathVariable Long id, Model model) {

        EditArticleModel article = this.articleService.loadOneById(id);

        model.addAttribute("article", article);
        model.addAttribute("view", "article/view");

        return "default-page";
    }
}
