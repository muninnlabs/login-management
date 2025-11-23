package com.example.loginMana.controllers;

import com.example.loginMana.entities.dto.CommentDto;
import com.example.loginMana.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    public CommentService commentService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Object findAllComments() {
        return commentService.findAll();
    }

    @GetMapping("/target-user/{targetUserId}")
    public List<CommentDto> findCommentsByTargetUser(@PathVariable Long targetUserId) {
        return commentService.findByTargetUserId(targetUserId);
    }

    @GetMapping("/{id}")
    public CommentDto findById(@PathVariable Long id) {
        return commentService.findById(id);
    }
}
