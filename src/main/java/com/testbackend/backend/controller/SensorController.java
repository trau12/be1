package com.testbackend.backend.controller;

import com.testbackend.backend.model.SensorData;
import com.testbackend.backend.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/data")
    public ResponseEntity<Void> saveSensorData(@RequestBody SensorData sensorData) {
        if (sensorData == null) {
            return ResponseEntity.badRequest().build();
        }
        sensorService.processSensorData(sensorData);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/latest/{deviceId}")
    public ResponseEntity<SensorData> getLatestData(@PathVariable String deviceId) {
        SensorData data = sensorService.getLatestSensorData(deviceId);
        return data != null ? ResponseEntity.ok(data) : ResponseEntity.notFound().build();
    }
}