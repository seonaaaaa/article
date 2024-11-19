package com.my.articles.service;

import com.my.articles.dao.CommentDAO;
import com.my.articles.dto.CommentDTO;
import com.my.articles.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CommentService {
    @Autowired
    CommentDAO dao;

    public void createComment(Long article_id, CommentDTO commentDTO) {
        dao.insertComment(article_id, CommentDTO.fromDTO(commentDTO));
    }

    public CommentDTO findById(Long id) {
        Comment findComment = dao.findById(id);
        if(ObjectUtils.isEmpty(findComment)){
            return null;
        }else {
            return CommentDTO.fromEntity(findComment);
        }
    }

    public void updateComment(CommentDTO commentDTO) {
        dao.updateComment(commentDTO);
    }

    public void deleteComment(Long id) {
        dao.deleteComment(id);
    }
}
