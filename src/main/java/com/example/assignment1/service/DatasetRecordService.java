package com.example.assignment1.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatasetRecordService {
    public Map<String, List<Map<String, Object>>> groupByField(List<Map<String, Object>> records, String field) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        m -> Optional.ofNullable(m.get(field)).map(Object::toString).orElse("null")
                ));
    }

    public List<Map<String, Object>> sortByField(List<Map<String, Object>> records, String field, String order) {
        Comparator<Map<String, Object>> comparator = Comparator.comparing(
                m -> Optional.ofNullable(m.get(field)).map(Object::toString).orElse("")
        );
        if (order != null && order.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }
        return records.stream().sorted(comparator).toList();
    }
} 