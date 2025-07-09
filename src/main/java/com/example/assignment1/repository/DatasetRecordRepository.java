package com.example.assignment1.repository;

import com.example.assignment1.entity.DatasetRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
 
public interface DatasetRecordRepository extends JpaRepository<DatasetRecord, Long> {
    List<DatasetRecord> findByDatasetName(String datasetName);
} 