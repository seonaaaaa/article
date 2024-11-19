package com.my.articles.dto;

import com.my.articles.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private List<CommentDTO> commentList;

    public ArticleDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static ArticleDTO fromEntity(Article article) {
        List<CommentDTO> commentDTOList = null;
        if(!ObjectUtils.isEmpty(article.getCommentList())){
            commentDTOList = article.getCommentList().stream().map(comment -> CommentDTO.fromEntity(comment)).toList();
        }
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                commentDTOList
        );
    }

    public static Article fromDTO(ArticleDTO dto) {
        return new Article(
                dto.getTitle(),
                dto.getContent()
        );
    }
}
