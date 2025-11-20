package com.example.loginMana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private String commenterName;
    private String commenterEmail;
    private String targetUserName;
    private String targetUserEmail;
    private LocalDateTime createdAt;

}