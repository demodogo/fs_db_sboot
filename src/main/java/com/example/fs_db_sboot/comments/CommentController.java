package com.example.fs_db_sboot.comments;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@PathVariable Long postId, @Valid @RequestBody Comment body) {
        return service.createForPost(postId, body);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable Long id, @Valid @RequestBody Comment body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
