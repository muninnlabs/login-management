package com.example.loginMana.services;

import com.example.loginMana.entities.UserEntity;
import com.example.loginMana.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUserByName(String username) {
        return userRepository.findByUserName(username);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

}
