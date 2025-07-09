package com.example.assignment1.controller;

import com.example.assignment1.entity.DatasetRecord;
import com.example.assignment1.repository.DatasetRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.example.assignment1.model.InsertResponse;
import com.example.assignment1.model.GroupByResponse;
import com.example.assignment1.model.SortByResponse;
import com.example.assignment1.service.DatasetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dataset")
public class DatasetRecordController {
    private final DatasetRecordRepository repository;
    private final ObjectMapper objectMapper;
    private final DatasetRecordService service;

    @Autowired
    public DatasetRecordController(DatasetRecordRepository repository, ObjectMapper objectMapper, DatasetRecordService service) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping("/{datasetName}/record")
    public ResponseEntity<InsertResponse> createRecord(
            @PathVariable String datasetName,
            @RequestBody Map<String, Object> body
    ) {
        try {
            String json = objectMapper.writeValueAsString(body);
            DatasetRecord record = DatasetRecord.builder()
                    .datasetName(datasetName)
                    .recordData(json)
                    .build();
            DatasetRecord saved = repository.save(record);
            InsertResponse response = new InsertResponse(
                "Record added successfully",
                datasetName,
                saved.getId()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{datasetName}/query")
    public ResponseEntity<?> queryRecords(
            @PathVariable String datasetName,
            @RequestParam(required = false) String groupBy,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        List<DatasetRecord> records = repository.findByDatasetName(datasetName);
        List<Map<String, Object>> deserialized = new ArrayList<>();
        for (DatasetRecord record : records) {
            try {
                Map<String, Object> map = objectMapper.readValue(record.getRecordData(), Map.class);
                map.put("id", record.getId());
                deserialized.add(map);
            } catch (Exception ignored) {}
        }

        // Grouping
        if (groupBy != null && !groupBy.isEmpty()) {
            Map<String, List<Map<String, Object>>> grouped = service.groupByField(deserialized, groupBy);
            return ResponseEntity.ok(new GroupByResponse(grouped));
        }

        // Sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            List<Map<String, Object>> sorted = service.sortByField(deserialized, sortBy, order);
            return ResponseEntity.ok(new SortByResponse(sorted));
        }

        return ResponseEntity.ok(deserialized);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
} 