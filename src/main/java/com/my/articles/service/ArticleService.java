package com.my.articles.service;

import com.my.articles.dao.ArticleDAO;
import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import com.my.articles.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO dao;

    @Autowired
    ArticleRepository articleRepository;

    public Page<ArticleDTO> findAllArticlesPage(Pageable pageable){
        return articleRepository.findAll(pageable).map(article -> ArticleDTO.fromEntity(article));
    }

    public List<ArticleDTO> findAllArticles() {
        List<Article> list = dao.findAllArticles();
        if(ObjectUtils.isEmpty(list)){
            return Collections.emptyList();
        }else {
        return list.stream().map(article -> ArticleDTO.fromEntity(article)).toList();
        }
    }

    public void createArticle(ArticleDTO articleDTO) {
        dao.createArticle(ArticleDTO.fromDTO(articleDTO));
    }

    public ArticleDTO findById(Long id) {
        return ArticleDTO.fromEntity(dao.findById(id));
    }

    public void updateArticle(ArticleDTO articleDTO) {
        dao.updateArticle(articleDTO);
    }

    public void deleteArticle(Long id) {
        dao.deleteArticle(id);
    }
}
