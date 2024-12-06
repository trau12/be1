package com.testbackend.backend.controller;

import com.testbackend.backend.model.Message;
import com.testbackend.backend.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {
    private final WebSocketService webSocketService;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload Message message) {
        log.info("Received message: {}", message);
        if (message == null || message.getMessage() == null || message.getUsername() == null) {
            log.error("Invalid message received: {}", message);
            return;
        }
        // Ghi log tin nhắn hợp lệ
        log.info("Message received: [Username: {}, Room: {}, Content: {}]",
                message.getUsername(), message.getRoomId(), message.getMessage());

        // Gửi tin nhắn đến phòng
        webSocketService.sendToRoom(message.getRoomId(), message);
    }
}
