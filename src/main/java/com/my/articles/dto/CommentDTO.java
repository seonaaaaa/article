package com.my.articles.dto;

import com.my.articles.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String nickname;
    private String body;

    public static CommentDTO fromEntity(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }

    public static Comment fromDTO(CommentDTO commentDTO) {
        return new Comment(
                commentDTO.getNickname(),
                commentDTO.getBody()
        );
    }
}
