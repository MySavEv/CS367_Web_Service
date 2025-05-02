package com.saveohm.itservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saveohm.itservice.model.BorrowRecord;
import com.saveohm.itservice.model.ITEquipment;
import com.saveohm.itservice.repository.BorrowRecordRepository;
import com.saveohm.itservice.repository.ITEquipmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> borrow(
        @RequestParam(required = false) Long employeeId, 
        @RequestParam(required = false) Long equipmentId) {

        if (employeeId == null || equipmentId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Require employeeId & equipmentId");
        }

        Optional<ITEquipment> oequipment = equipmentRepo.findById(equipmentId);
        if (!oequipment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not found");
        }
        ITEquipment equipment = oequipment.get();

        if (!equipment.isAvailable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Equipment not available");
        }

        equipment.setAvailable(false);
        equipmentRepo.save(equipment);

        BorrowRecord record = BorrowRecord.builder()
                .employeeId(employeeId)
                .equipment(equipment)
                .borrowDate(LocalDate.now())
                .returned(false)
                .build();

        return ResponseEntity.ok(borrowRecordRepo.save(record));
    }

    // ✅ คืนอุปกรณ์
    @PostMapping("/return")
    public ResponseEntity<?> returnEquipment(@RequestParam Long borrowId) {
        Optional<BorrowRecord> oprecord = borrowRecordRepo.findById(borrowId);
        if (!oprecord.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrow record not found");
        }
        BorrowRecord record = oprecord.get();

        if (record.isReturned()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Already returned");
        }

        record.setReturnDate(LocalDate.now());
        record.setReturned(true);
        borrowRecordRepo.save(record);

        ITEquipment equipment = record.getEquipment();
        equipment.setAvailable(true);
        equipmentRepo.save(equipment);

        return ResponseEntity.ok(record);
    }

    // ✅ ดูรายการยืมของพนักงาน
    @GetMapping("/all")
    public List<BorrowRecord> getAllBorrows() {
        return borrowRecordRepo.findAll();
    }

    // ✅ ดูรายการยืมของพนักงาน
    @GetMapping("/employee/{employeeId}")
    public List<BorrowRecord> getEmployeeBorrows(@PathVariable Long employeeId) {
        return borrowRecordRepo.findByEmployeeId(employeeId);
    }
}
