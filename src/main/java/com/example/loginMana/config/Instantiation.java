package com.example.loginMana.config;

import com.example.loginMana.entities.CommentEntity;
import com.example.loginMana.entities.UserEntity;
import com.example.loginMana.repositories.CommentRepository;
import com.example.loginMana.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(String... args0) throws Exception {
        // --- 1. Clean Repositories ---
        commentRepository.deleteAll();
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

        userRepository.saveAll(Arrays.asList(user1, user2));

        // Assuming your full constructor signature is:
        // (id, content, createdAt, commenter, targetUser, parentComment, replies)

        // --- 3. Create TOP-LEVEL COMMENTS (Parents) ---

        // PARENT 1 (Alice comments on Bob's profile)
        CommentEntity parent1 = new CommentEntity(
                null,
                "Bob, I noticed your portfolio is online now! Great work on the design.",
                LocalDateTime.now().minusHours(2),
                user1, // Commenter: Alice
                user2, // Target: Bob
                null,  // No Parent
                null   // Replies List
        );

        // PARENT 2 (Bob comments on his own profile)
        CommentEntity parent2 = new CommentEntity(
                null,
                "Note to self: Must update my project list before the weekend.",
                LocalDateTime.now().minusDays(1),
                user2, // Commenter: Bob
                user2, // Target: Bob
                null,
                null
        );

        // PARENT 3 (Alice comments on her own profile)
        CommentEntity parent3 = new CommentEntity(
                null,
                "This is a general announcement for all followers.",
                LocalDateTime.now().minusHours(4),
                user1, // Commenter: Alice
                user1, // Target: Alice
                null,
                null
        );

        // Save Parents FIRST to generate IDs
        commentRepository.saveAll(Arrays.asList(parent1, parent2, parent3));

        // --- 4. Create REPLY Comments (Children) ---

        // REPLY 1 (Bob replies to Parent 1, which is a comment on his profile)
        CommentEntity reply1 = new CommentEntity(
                null,
                "Thanks so much, Alice! That means a lot. I appreciate the feedback.",
                LocalDateTime.now().minusHours(1).minusMinutes(30),
                user2, // Commenter: Bob
                user2, // Target: Bob (Target remains the user whose profile the thread is on)
                parent1, // ⭐️ Parent is parent1
                null
        );

        // REPLY 2 (Alice replies to Parent 3, which is a comment on her own profile)
        CommentEntity reply2 = new CommentEntity(
                null,
                "To clarify my announcement: it only applies to users in the ADMIN role.",
                LocalDateTime.now().minusHours(3),
                user1, // Commenter: Alice
                user1, // Target: Alice
                parent3, // ⭐️ Parent is parent3
                null
        );

        // REPLY 3 (A second-level reply: Bob replies to Reply 2)
        CommentEntity reply3 = new CommentEntity(
                null,
                "Got it. Thanks for the clarification!",
                LocalDateTime.now().minusHours(2).minusMinutes(50),
                user2, // Commenter: Bob
                user1, // Target: Alice (The target user doesn't change)
                reply2, // ⭐️ Parent is reply2
                null
        );

        // --- 5. Save all Replies ---
        commentRepository.saveAll(Arrays.asList(reply1, reply2, reply3));

        System.out.println("Mock data (users, 3 parents, and 3 replies) populated successfully!");
    }
}