package com.example.assignment1;

import com.example.assignment1.entity.DatasetRecord;
import com.example.assignment1.repository.DatasetRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

@SpringBootApplication
public class Assignment1Application {
    public static void main(String[] args) {
        SpringApplication.run(Assignment1Application.class, args);
    }

    @Bean
    @Profile("!test") // Only run this when not in test profile
    public CommandLineRunner demoData(DatasetRecordRepository repository, ObjectMapper objectMapper) {
        return args -> {
            System.out.println("Inserting sample data...");

            List<Map<String, Object>> sampleData = Arrays.asList(
                    Map.of("name", "John Doe", "age", 30, "department", "Engineering", "city", "New York"),
                    Map.of("name", "Jane Smith", "age", 25, "department", "Engineering", "city", "London"),
                    Map.of("name", "Alice Brown", "age", 28, "department", "Marketing", "city", "New York"),
                    Map.of("name", "Bob White", "age", 35, "department", "Sales", "city", "Chicago"),
                    Map.of("name", "Charlie Green", "age", 22, "department", "Engineering", "city", "London"),
                    Map.of("name", "Diana Prince", "age", 32, "department", "Marketing", "city", "Paris"),
                    Map.of("name", "Eve Black", "age", 29, "department", "Sales", "city", "Chicago"),
                    Map.of("name", "Frank Blue", "age", 40, "department", "HR", "city", "New York"),
                    Map.of("name", "Grace Kelly", "age", 27, "department", "HR", "city", "London"),
                    Map.of("name", "Harry Potter", "age", 23, "department", "Engineering", "city", "New York")
            );

            String datasetName = "employee_dataset";

            for (Map<String, Object> data : sampleData) {
                String json = objectMapper.writeValueAsString(data);
                DatasetRecord record = DatasetRecord.builder()
                        .datasetName(datasetName)
                        .recordData(json)
                        .build();
                repository.save(record);
            }
            System.out.println("Sample data inserted successfully.");
        };
    }
} 