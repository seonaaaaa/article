package com.my.articles.controller;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.service.ArticleService;
import com.my.articles.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    PaginationService paginationService;


    @GetMapping("")
    public String showAll(Model model, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<ArticleDTO> articlesPage = articleService.findAllArticlesPage(pageable);
        int currentPage = articlesPage.getNumber();
        int totalPages = articlesPage.getTotalPages();
        model.addAttribute("pageBars", paginationService.getPaginationBarNumber(currentPage, totalPages));
        model.addAttribute("list", articlesPage);
        return "/articles/show_all";
    }

    @GetMapping("new")
    public String newArticle() {
        return "/articles/new";
    }

    @PostMapping("create")
    public String createArticle(ArticleDTO articleDTO, RedirectAttributes redirectAttributes) {
        articleService.createArticle(articleDTO);
        redirectAttributes.addFlashAttribute("msg", "등록되었습니다!");
        return "redirect:/articles";
    }

    @GetMapping("{id}")
    public String showOneArticle(@PathVariable("id")Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "/articles/show";
    }

    @GetMapping("{id}/update")
    public String updateArticleView(@PathVariable("id")Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "/articles/update";
    }

    @PostMapping("update")
    public String updateArticle(ArticleDTO articleDTO,RedirectAttributes redirectAttributes) {
        String url = "redirect:/articles/" + articleDTO.getId();
        articleService.updateArticle(articleDTO);
        redirectAttributes.addFlashAttribute("msg", "수정되었습니다!");
        return url;
    }

    @GetMapping("{id}/delete")
    public String deleteArticle(@PathVariable("id")Long id, RedirectAttributes redirectAttributes) {
        articleService.deleteArticle(id);
        redirectAttributes.addFlashAttribute("msg", "삭제되었습니다!");
        return "redirect:/articles";
    }
}