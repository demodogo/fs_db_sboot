package com.example.fs_db_sboot.ratings;

import com.example.fs_db_sboot.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByPost(Post post);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.post.id = :postId")
    Double findAverageByPost(Long postId);
}
