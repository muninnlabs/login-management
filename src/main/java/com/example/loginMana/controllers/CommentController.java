package com.example.loginMana.controllers;

import com.example.loginMana.dto.CommentDto;
import com.example.loginMana.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping({"", "/"})
    public List<CommentDto> findAllComments() {
        return commentService.findAllAsDto();
    }

    @GetMapping("/target-user/{targetUserId}")
    public List<CommentDto> findCommentsByTargetUser(@PathVariable Long targetUserId) {
        return commentService.findByTargetUserId(targetUserId);
    }
}