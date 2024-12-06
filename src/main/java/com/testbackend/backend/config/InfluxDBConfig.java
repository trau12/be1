package com.testbackend.backend.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class InfluxDBConfig {
    private static final Logger logger = LoggerFactory.getLogger(InfluxDBConfig.class);

    @Value("${influxdb.url}")
    private String url;

    @Value("${influxdb.token}")
    private String token;

    @Value("${influxdb.org}")
    private String org;

    @Value("${influxdb.bucket}")
    private String bucket;

    @Bean
    public InfluxDBClient influxDBClient() {
        try {
            return InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        } catch (Exception e) {
            logger.error("Could not connect to InfluxDB: {}", e.getMessage());
            throw e; // Ném lại ngoại lệ để dừng ứng dụng
        }
    }

    @Bean
    public String influxDBBucket() {
        return bucket;
    }

    @Bean
    public String influxDBOrg() {
        return org;
    }
}