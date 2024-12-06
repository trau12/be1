package com.testbackend.backend.service;

import com.testbackend.backend.model.Message;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;

    public void handleConnect(String username) {
        logger.info("User {} connected", username);
        userService.updateUserStatus(username, true);
    }

    public void handleDisconnect(String username) {
        logger.info("User {} disconnected", username);
        userService.updateUserStatus(username, false);
    }

    public void sendMessage(String destination, Message message) {
        logger.info("Sending message to {}: {}", destination, message.getMessage());
        messagingTemplate.convertAndSend(destination, message);
    }

    public void sendToRoom(String roomId, Message message) {
        sendMessage("/topic/messages/" + roomId, message);
    }
}