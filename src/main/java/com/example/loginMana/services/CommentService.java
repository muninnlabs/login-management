package com.example.loginMana.services;

import com.example.loginMana.entities.dto.CommentDto;
import com.example.loginMana.entities.CommentEntity;
import com.example.loginMana.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentDto> findAllAsDto() {
        List<CommentEntity> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // In CommentService.java
    public List<CommentDto> findAll() {
        // Retrieve only top-level comments. JPA's fetch strategy will load the replies.
        List<CommentEntity> topLevelComments = commentRepository.findByParentCommentIsNull();
        // Map the top-level entities, which triggers the recursive mapping for replies
        return topLevelComments.stream()
                .map(this::convertToDto)
                .toList();
    }

    public CommentDto findById(Long id) {
        CommentEntity comment = commentRepository.findById(id).orElse(null);
        return comment != null ? convertToDto(comment) : null;
    }

    public List<CommentDto> findByTargetUserId(Long targetUserId) {
        List<CommentEntity> comments = commentRepository.findByTargetUserId(targetUserId);
        return comments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> addNewComment(CommentEntity comment) {
        commentRepository.save(comment);
        return findByTargetUserId(comment.getTargetUser().getId());
    }

    private CommentDto convertToDto(CommentEntity comment) {
        CommentDto dto = new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getCommenter().getName(),
                comment.getCommenter().getEmail(),
                comment.getTargetUser().getName(),
                comment.getTargetUser().getEmail(),
                comment.getCreatedAt(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                null // Replies can be populated if needed
        );

        if(comment.getReplies() != null && !comment.getReplies().isEmpty()) {
            List<CommentDto> replyDtos = comment.getReplies().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            dto.setReplies(replyDtos);
        }

        return dto;

    }


}