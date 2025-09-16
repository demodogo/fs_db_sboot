package com.example.fs_db_sboot.posts;

import com.example.fs_db_sboot.comments.Comment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;


    public record PostDTO(Long id, String title, String content, List<Comment> comments) {}

    private PostDTO toDTO(Post post, boolean withComments) {
        return new PostDTO(post.getId(),
                post.getTitle(),
                post.getContent(),
                withComments ? post.getComments() : Collections.emptyList()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> list(@RequestParam(required = false) boolean withComments) {
        List<Post> posts = service.findAll();
        return posts.stream().map(post -> toDTO(post, withComments)).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO get(@PathVariable Long id, @RequestParam(required = false) boolean withComments) {
        Post post = service.findById(id);
        return toDTO(post, withComments);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@Valid @RequestBody Post body) {
        return service.create(body);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable Long id, @Valid @RequestBody Post body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
