package com.example.loginMana.config;


import com.example.loginMana.entities.CommentEntity;
import com.example.loginMana.entities.UserEntity;
import com.example.loginMana.repositories.CommentRepository;
import com.example.loginMana.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(String... args0) throws Exception {
        // --- 1. Clean Repositories ---
        commentRepository.deleteAll(); // Clean comments first, as they depend on users
        userRepository.deleteAll();

        // --- 2. Create and Save Users ---

        UserEntity user1 = new UserEntity(
                null, "Alice", "Smith", "alice_admin", "adminPass123",
                "ADMIN", "alice.smith@example.com", "LOCAL", null, null
        );

        UserEntity user2 = new UserEntity(
                null, "Bob", "Johnson", "bob_user", "userPass456",
                "USER", "bob.johnson@example.com", "LOCAL", null, null
        );

        // Save users to ensure they get their auto-generated IDs
        userRepository.saveAll(Arrays.asList(user1, user2));

        // --- 3. Create Comments ---

        // Assuming your Comment constructor is:
        // (Long id, String content, LocalDateTime createdAt, UserEntity commenter, UserEntity targetUser)

        // Comment 1: Alice comments on Bob's profile
        CommentEntity comment1 = new CommentEntity(
                null,
                "Great profile, Bob! Welcome to the site.",
                LocalDateTime.now(),
                user1, // Commenter: Alice
                user2  // Target: Bob
        );

        // Comment 2: Bob comments on Alice's profile
        CommentEntity comment2 = new CommentEntity(
                null,
                "Thanks for the help, Alice!",
                LocalDateTime.now().minusMinutes(5), // Make it slightly older
                user2, // Commenter: Bob
                user1  // Target: Alice
        );

        // Comment 3: Alice comments on her own profile (for testing purposes)
        CommentEntity comment3 = new CommentEntity(
                null,
                "Leaving a quick note for myself.",
                LocalDateTime.now().minusHours(1),
                user1, // Commenter: Alice
                user1  // Target: Alice
        );

        // Comment 4: Bob replies to a comment on Alice's profile (or simply comments later)
        CommentEntity comment4 = new CommentEntity(
                null,
                "I agree, Alice's work is impressive!",
                LocalDateTime.now().minusDays(1), // One day old
                user2, // Commenter: Bob
                user1  // Target: Alice
        );

// Comment 5: Alice leaves a quick, recent thought on Bob's profile
        CommentEntity comment5 = new CommentEntity(
                null,
                "Check out my latest post, Bob!",
                LocalDateTime.now().minusSeconds(30), // Very recent
                user1, // Commenter: Alice
                user2  // Target: Bob
        );

// Comment 6: Bob comments on his own profile
        CommentEntity comment6 = new CommentEntity(
                null,
                "Need to update my profile picture soon.",
                LocalDateTime.now().minusHours(2),
                user2, // Commenter: Bob
                user2  // Target: Bob
        );

        // --- 4. Save Comments ---
        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3, comment4, comment5, comment6));

        System.out.println("Mock data (users and comments) populated successfully!");
    }
}