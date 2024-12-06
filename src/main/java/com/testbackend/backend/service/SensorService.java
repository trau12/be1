package com.testbackend.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbackend.backend.model.SensorData;
import com.testbackend.backend.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorService {
//    private static final Logger logger = LoggerFactory.getLogger(SensorService.class);
//    private final SensorDataRepository sensorDataRepository;
//    private final RedisTemplate<String, String> redisTemplate;
//    private static final String LATEST_SENSOR_KEY = "latest_sensor:";
//
//    public void processSensorData(SensorData sensorData) {
//        if (sensorData == null) {
//            logger.error("SensorData is null, cannot process");
//            return;
//        }
//        sensorDataRepository.save(sensorData);
//        String key = LATEST_SENSOR_KEY + sensorData.getDeviceId();
//        redisTemplate.opsForValue().set(key, convertToJson(sensorData));
//        logger.info("Processed and cached sensor data for device {}", sensorData.getDeviceId());
//    }
//
//    public SensorData getLatestSensorData(String deviceId) {
//        String key = LATEST_SENSOR_KEY + deviceId;
//        String cachedData = redisTemplate.opsForValue().get(key);
//        if (cachedData != null) {
//            return convertFromJson(cachedData);
//        }
//        return null;
//    }
//
//    // Helper methods
//    private String convertToJson(SensorData sensorData) {
//        // Implement JSON conversion
//        return ""; // Placeholder
//    }
//
//    private SensorData convertFromJson(String json) {
//        // Implement JSON parsing
//        return null; // Placeholder
//    }

    private static final Logger logger = LoggerFactory.getLogger(SensorService.class);
    private final SensorDataRepository sensorDataRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private static final String LATEST_SENSOR_KEY = "latest_sensor:esp32";

    public void processSensorData(SensorData sensorData) {
        if (sensorData == null) {
            logger.error("SensorData is null, cannot process");
            return;
        }
        sensorDataRepository.save(sensorData);
        redisTemplate.opsForValue().set(LATEST_SENSOR_KEY, convertToJson(sensorData));
        logger.info("Processed and cached sensor data for device esp32");
    }

    public SensorData getLatestSensorData(String deviceId) {
        String key = LATEST_SENSOR_KEY + deviceId;
        String cachedData = redisTemplate.opsForValue().get(key);
        if (cachedData != null) {
            return convertFromJson(cachedData);
        }
        return null;
    }

    // Helper methods
    private String convertToJson(SensorData sensorData) {
        try {
            return new ObjectMapper().writeValueAsString(sensorData);
        } catch (JsonProcessingException e) {
            logger.error("Error converting SensorData to JSON", e);
            return null;
        }
    }

    private SensorData convertFromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, SensorData.class);
        } catch (JsonProcessingException e) {
            logger.error("Error converting JSON to SensorData", e);
            return null;
        }
    }
}