package com.example.fs_db_sboot.comments;

import com.example.fs_db_sboot.posts.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
@SequenceGenerator(name = "comment_seq", sequenceName = "SEQ_COMMENT", allocationSize = 1)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Post post;

    @NotBlank
    @Size(min = 2, max = 60)
    @Column(name = "author", nullable = false, length = 60)
    private String author;

    @NotBlank
    @Size(min = 2, max = 1000)
    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
