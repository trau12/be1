package com.testbackend.backend.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.testbackend.backend.model.SensorData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class SensorDataRepository {
    private static final Logger logger = LoggerFactory.getLogger(SensorDataRepository.class);

    private final InfluxDBClient influxDBClient;
    private final String bucket;
    private final String org;

    @Autowired
    public SensorDataRepository(InfluxDBClient influxDBClient,
                                String influxDBBucket,
                                String influxDBOrg) {
        this.influxDBClient = influxDBClient;
        this.bucket = influxDBBucket;
        this.org = influxDBOrg;
    }

    public void save(SensorData sensorData) {
        // Kiểm tra tính hợp lệ của sensorData nếu cần
        if (sensorData == null) {
            logger.error("SensorData is null, cannot save to InfluxDB");
            return;
        }

        try (WriteApi writeApi = influxDBClient.makeWriteApi()) {
            writeApi.writeMeasurement(bucket, org, WritePrecision.NS, sensorData);
        } catch (Exception e) {
            logger.error("Error writing SensorData to InfluxDB: {}", e.getMessage());
            // Có thể ném ngoại lệ hoặc xử lý theo cách khác
        }
    }
}