package com.example.fs_db_sboot.comments;

import com.example.fs_db_sboot.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
