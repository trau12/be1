package com.testbackend.backend.service;

import com.testbackend.backend.model.DeviceState;
import com.testbackend.backend.model.SensorData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final SimpMessagingTemplate messagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    private DeviceState currentState = new DeviceState("esp32", "OFF", null);

    public void updateDeviceState(DeviceState newState) {
        logger.info("Updating device state: {}", newState);
        this.currentState = newState;
        messagingTemplate.convertAndSend("/topic/device", newState);
    }

    public DeviceState getCurrentState() {
        return currentState;
    }

    public void updateSensorData(SensorData sensorData) {
        logger.info("Updating sensor data: {}", sensorData);
        if (currentState != null) {
            currentState.setSensorData(sensorData);
            messagingTemplate.convertAndSend("/topic/device", currentState);
        }
    }
}
