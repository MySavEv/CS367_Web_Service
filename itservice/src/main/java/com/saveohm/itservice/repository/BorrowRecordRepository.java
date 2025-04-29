package com.saveohm.itservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saveohm.itservice.model.BorrowRecord;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByEmployeeId(Long employeeId);
}