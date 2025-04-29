package com.saveohm.itservice.controller;


import org.springframework.web.bind.annotation.*;

import com.saveohm.itservice.model.BorrowRecord;
import com.saveohm.itservice.model.ITEquipment;
import com.saveohm.itservice.repository.BorrowRecordRepository;
import com.saveohm.itservice.repository.ITEquipmentRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/borrow") 
public class BorrowController {

    private final BorrowRecordRepository borrowRecordRepo;
    private final ITEquipmentRepository equipmentRepo;

    public BorrowController(BorrowRecordRepository borrowRecordRepo, ITEquipmentRepository equipmentRepo) {
        this.borrowRecordRepo = borrowRecordRepo;
        this.equipmentRepo = equipmentRepo;
    }

    // ✅ ยืมอุปกรณ์
    @PostMapping
    public BorrowRecord borrow(@RequestParam Long employeeId, @RequestParam Long equipmentId) {
        ITEquipment equipment = equipmentRepo.findById(equipmentId).orElseThrow();
        if (!equipment.isAvailable()) throw new RuntimeException("Equipment not available");

        equipment.setAvailable(false);
        equipmentRepo.save(equipment);

        BorrowRecord record = BorrowRecord.builder()
                .employeeId(employeeId)
                .equipment(equipment)
                .borrowDate(LocalDate.now())
                .returned(false)
                .build();

        return borrowRecordRepo.save(record);
    }

    // ✅ คืนอุปกรณ์
    @PostMapping("/return")
    public BorrowRecord returnEquipment(@RequestParam Long borrowId) {
        BorrowRecord record = borrowRecordRepo.findById(borrowId).orElseThrow();
        if (record.isReturned()) throw new RuntimeException("Already returned");

        record.setReturnDate(LocalDate.now());
        record.setReturned(true);
        borrowRecordRepo.save(record);

        ITEquipment equipment = record.getEquipment();
        equipment.setAvailable(true);
        equipmentRepo.save(equipment);

        return record;
    }

    // ✅ ดูรายการยืมของพนักงาน
    @GetMapping("/employee/{employeeId}")
    public List<BorrowRecord> getEmployeeBorrows(@PathVariable Long employeeId) {
        return borrowRecordRepo.findByEmployeeId(employeeId);
    }
}
