package com.example.fs_db_sboot.ratings;

import com.example.fs_db_sboot.posts.Post;
import com.example.fs_db_sboot.posts.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratings;
    private final PostRepository posts;

    public List<Rating> listByPost(Long postId) {
        Post post = posts.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        return ratings.findByPost(post);
    }

    public Rating createForPost(Long postId, Rating body) {
        Post post = posts.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        body.setId(null);
        body.setPost(post);
        return ratings.save(body);
    }

    public void delete(Long id) {
        ratings.deleteById(id);
    }

    public Double averageForPost(Long postId) {
        Double avg = ratings.findAverageByPost(postId);
        if (avg == null) return 0.0;

        return BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
