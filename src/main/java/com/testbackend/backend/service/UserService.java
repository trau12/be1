package com.testbackend.backend.service;

import com.testbackend.backend.model.User;
import com.testbackend.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;
    private static final String USER_KEY_PREFIX = "user:";

    public User save(User user) {
        User savedUser = userRepository.save(user);
        redisTemplate.opsForValue().set(USER_KEY_PREFIX + user.getUsername(), savedUser);
        logger.info("User {} saved", user.getUsername());
        return savedUser;
    }

    public Optional<User> findByUsername(String username) {
        User cachedUser = redisTemplate.opsForValue().get(USER_KEY_PREFIX + username);
        if (cachedUser != null) {
            return Optional.of(cachedUser);
        }
        return userRepository.findByUsername(username)
                .map(user -> {
                    redisTemplate.opsForValue().set(USER_KEY_PREFIX + username, user);
                    return user;
                });
    }

    public void updateUserStatus(String username, boolean online) {
        findByUsername(username).ifPresent(user -> {
            user.setOnline(online);
            user.setLastActive(LocalDateTime.now());
            save(user);
            logger.info("User {} status updated to {}", username, online);
        });
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}