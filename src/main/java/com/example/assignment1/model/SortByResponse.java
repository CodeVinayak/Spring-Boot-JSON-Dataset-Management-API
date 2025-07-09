package com.example.assignment1.model;

import java.util.List;
import java.util.Map;

public class SortByResponse {
    private List<Map<String, Object>> sortedRecords;

    public SortByResponse() {}
    public SortByResponse(List<Map<String, Object>> sortedRecords) {
        this.sortedRecords = sortedRecords;
    }
    public List<Map<String, Object>> getSortedRecords() {
        return sortedRecords;
    }
    public void setSortedRecords(List<Map<String, Object>> sortedRecords) {
        this.sortedRecords = sortedRecords;
    }
} 