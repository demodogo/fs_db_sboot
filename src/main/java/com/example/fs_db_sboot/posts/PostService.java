package com.example.fs_db_sboot.posts;

import com.example.fs_db_sboot.comments.Comment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repo;

    public List<Post> findAll() {
        return repo.findAll();
    }

    public Post findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Post create(Post p) {
        p.setId(null);
        return repo.save(p);
    }

    public Post update(Long id, Post p) {
        Post dbPost = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        dbPost.setTitle(p.getTitle());
        dbPost.setContent(p.getContent());
        return repo.save(dbPost);
    }

    public List<Comment> getComments(Long id) {
        Post dbPost = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        return dbPost.getComments();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
