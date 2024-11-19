package com.my.articles.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 500)
    private String title;
    @Column(nullable = false, length = 1000)
    private String content;
    @OneToMany(mappedBy = "article", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Comment> commentList;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", commentList=" + commentList +
                '}';
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
