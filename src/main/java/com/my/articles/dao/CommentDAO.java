package com.my.articles.dao;

import com.my.articles.dto.CommentDTO;
import com.my.articles.entity.Article;
import com.my.articles.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CommentDAO {
    @Autowired
    EntityManager em;

    public void insertComment(Long article_id, Comment comment) {
        Article updateArticle = em.find(Article.class, article_id);
        comment.setArticle(updateArticle);
        updateArticle.getCommentList().add(comment);
        em.persist(updateArticle);
    }

    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    public void updateComment(CommentDTO commentDTO) {
        Comment updateComment = em.find(Comment.class, commentDTO.getId());
        updateComment.setBody(commentDTO.getBody());
    }

    public void deleteComment(Long id) {
        Comment deleteComment = findById(id);
        em.remove(deleteComment);
    }
}
