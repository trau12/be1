package com.testbackend.backend.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "messages")
public class Message {
    @Id
    private String id;  // Thêm ID cho Message

    @NotBlank(message = "Message content is required")
    private String message;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Room ID is required")
    private String roomId;

    @CreationTimestamp
    private Instant created;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    // Thêm field cho sensor data
    private SensorData sensorData;
}