package com.example.fs_db_sboot.posts;

import com.example.fs_db_sboot.comments.Comment;
import com.example.fs_db_sboot.ratings.Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "posts")
@SequenceGenerator(name = "post_seq", sequenceName = "SEQ_POST", allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 120)
    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @NotBlank
    @Size(min = 10, max = 4000)
    @Column(name = "content", nullable = false, length = 4000)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime  updatedAt;
}