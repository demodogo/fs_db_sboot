package com.example.fs_db_sboot.comments;

import com.example.fs_db_sboot.posts.Post;
import com.example.fs_db_sboot.posts.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repo;
    private final PostRepository postRepo;

    public List<Comment> findAll() {
        return repo.findAll();

    }

    public Comment createForPost(Long postId, Comment body) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        body.setId(null);
        body.setPost(post);
        return repo.save(body);
    }


    public Comment findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
    }

    public Comment update(Long id, Comment body) {
        Comment comment = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));
        comment.setAuthor(body.getAuthor());
        comment.setContent(body.getContent());
        return repo.save(comment);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
