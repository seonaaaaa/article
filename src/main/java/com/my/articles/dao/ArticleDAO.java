package com.my.articles.dao;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class ArticleDAO {
    @Autowired
    EntityManager em;

    public List<Article> findAllArticles() {
        String sql = "SELECT a FROM Article a ORDER BY a.id DESC";
        return em.createQuery(sql).getResultList();
    }

    public void createArticle(Article article) {
        em.persist(article);
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    public void updateArticle(ArticleDTO articleDTO) {
        Article updateArticle = em.find(Article.class, articleDTO.getId());
        updateArticle.setTitle(articleDTO.getTitle());
        updateArticle.setContent(articleDTO.getContent());
    }

    public void deleteArticle(Long id) {
        Article deleteArticle = em.find(Article.class, id);
        em.remove(deleteArticle);
    }
}
