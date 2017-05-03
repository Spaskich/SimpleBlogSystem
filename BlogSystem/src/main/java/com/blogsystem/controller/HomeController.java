package com.blogsystem.controller;

import com.blogsystem.model.article.EditArticleModel;
import com.blogsystem.model.article.PublishArticleModel;
import com.blogsystem.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String getHomePage(Model model){

        List<EditArticleModel> articles = this.articleService.loadAll();
        model.addAttribute("view","home");
        model.addAttribute("articles", articles);

        return "default-page";
    }
}
