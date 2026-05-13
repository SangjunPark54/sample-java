package com.example.board.service;

import com.example.board.model.Post;
import com.example.board.repository.PostRepository;
import com.example.board.exception.PostNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 게시글 서비스.
 * INTENTIONAL: class_name_pascal_case (클래스명이 snake_case — lint_java 규칙)
 */
@Service
public class post_service {

    private final PostRepository repository;

    public post_service(PostRepository repository) {
        this.repository = repository;
    }

    public Post findById(Long id) {
        if (!repository.existsById(id)) {
            return null;
        }
        return new Post(id, "Sample title", "Sample body", "tester");
    }

    public int removeByAuthor(String author) {
        return repository.deleteByAuthor(author);
    }

    public Post getPost(Long id) {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }
}