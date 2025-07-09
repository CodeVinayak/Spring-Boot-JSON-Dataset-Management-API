package com.example.assignment1.entity;

import jakarta.persistence.*;

@Entity
public class DatasetRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String datasetName;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String recordData; // Store JSON as String

    public DatasetRecord() {}

    public DatasetRecord(Long id, String datasetName, String recordData) {
        this.id = id;
        this.datasetName = datasetName;
        this.recordData = recordData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String datasetName;
        private String recordData;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder datasetName(String datasetName) {
            this.datasetName = datasetName;
            return this;
        }

        public Builder recordData(String recordData) {
            this.recordData = recordData;
            return this;
        }

        public DatasetRecord build() {
            return new DatasetRecord(id, datasetName, recordData);
        }
    }
} 