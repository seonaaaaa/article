package com.my.articles.service;

import com.my.articles.dto.ArticleDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    @DisplayName("모든 글 가져오기")
    public void findAllArticles() {
        articleService.findAllArticles().forEach(System.out::println);
    }

    @Test
    @DisplayName("새로운 글 작성하기")
    public void createArticle() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle("제목");
        articleDTO.setContent("내용입니다.");
        articleService.createArticle(articleDTO);
        findAllArticles();
    }

    @Test
    @DisplayName("ID로 찾아오기")
    public void findById() {
        Long id = 4L;
        System.out.println(articleService.findById(id));
    }

    @Test
    @DisplayName("글 수정하기")
    public void updateArticle() {
        ArticleDTO articleDTO = articleService.findById(7L);
        articleDTO.setTitle("제목(수정)");
        articleDTO.setContent("수정된 내용입니다.");
        articleService.updateArticle(articleDTO);
        System.out.println(articleService.findById(7L));
    }

    @Test
    @DisplayName("글 삭제하기")
    public void deleteArticle() {
        articleService.deleteArticle(7L);
        findAllArticles();
    }
}