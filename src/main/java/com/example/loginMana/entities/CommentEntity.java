package com.example.loginMana.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // The text content of the comment
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    // Optional: Timestamp for when the comment was created
    private LocalDateTime createdAt = LocalDateTime.now();
    // 1. User who WROTE the comment (the 'commenter')
    // Many comments can be written by One User (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commenter_id", nullable = false)
    private UserEntity commenter;
    // 2. User whose PROFILE the comment is ON (the 'target')
    // Many comments can target One User (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", nullable = false)
    private UserEntity targetUser;
    // Note: We use FetchType.LAZY to improve performance, so the UserEntities
    // are only loaded when you explicitly call a getter on them.
}
