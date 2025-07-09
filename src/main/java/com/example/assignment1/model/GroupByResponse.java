package com.example.assignment1.model;

import java.util.List;
import java.util.Map;

public class GroupByResponse {
    private Map<String, List<Map<String, Object>>> groupedRecords;

    public GroupByResponse() {}
    public GroupByResponse(Map<String, List<Map<String, Object>>> groupedRecords) {
        this.groupedRecords = groupedRecords;
    }
    public Map<String, List<Map<String, Object>>> getGroupedRecords() {
        return groupedRecords;
    }
    public void setGroupedRecords(Map<String, List<Map<String, Object>>> groupedRecords) {
        this.groupedRecords = groupedRecords;
    }
} 