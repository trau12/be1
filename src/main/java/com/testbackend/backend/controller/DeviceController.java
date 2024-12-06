package com.testbackend.backend.controller;

import com.testbackend.backend.model.DeviceState;
import com.testbackend.backend.model.SensorData;
import com.testbackend.backend.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @MessageMapping("/device/state")
    public void updateState(@Payload DeviceState state) {
        logger.info("Received state update from ESP32: {}", state);
        deviceService.updateDeviceState(state);
    }

    @MessageMapping("/device/sensor")
    public void updateSensor(@Payload SensorData sensorData) {
        logger.info("Received sensor data from ESP32: {}", sensorData);
        deviceService.updateSensorData(sensorData);
    }
}

