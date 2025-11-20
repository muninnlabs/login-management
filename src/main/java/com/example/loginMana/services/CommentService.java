package com.example.loginMana.services;

import com.example.loginMana.dto.CommentDto;
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

    public List<CommentDto> findByTargetUserId(Long targetUserId) {
        List<CommentEntity> comments = commentRepository.findByTargetUserId(targetUserId);
        return comments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CommentDto convertToDto(CommentEntity comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getCommenter().getName(),
                comment.getCommenter().getEmail(),
                comment.getTargetUser().getName(),
                comment.getTargetUser().getEmail(),
                comment.getCreatedAt()
        );
    }
}