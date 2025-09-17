package com.example.fs_db_sboot.ratings;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService service;

    public record RatingDTO(Long id, Integer score, Long postId) {}

    private RatingDTO toDTO(Rating rating) {
        return new RatingDTO(
                rating.getId(),
                rating.getScore(),
                rating.getPost().getId()
        );
    }
    @GetMapping("/posts/{postId}")
    public List<RatingDTO> list(@PathVariable Long postId) {
        List<Rating> ratings = service.listByPost(postId);
        return ratings.stream().map(rating -> toDTO(rating)).toList();
    }

    @PostMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Rating create(@PathVariable Long postId, @Valid @RequestBody Rating body) {
        return service.createForPost(postId, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/posts/{postId}/rating/average")
    public Map<String, Object> average(@PathVariable Long postId) {
        Double avg = service.averageForPost(postId);
        return Map.of("postId", postId, "average", avg);
    }
}
