package com.saveohm.itservice.controller;


import org.springframework.web.bind.annotation.*;

import com.saveohm.itservice.model.ITEquipment;
import com.saveohm.itservice.repository.ITEquipmentRepository;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class ITEquipmentController {

    private final ITEquipmentRepository equipmentRepository;

    public ITEquipmentController(ITEquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    // ✅ ดูรายการอุปกรณ์ทั้งหมด
    @GetMapping
    public List<ITEquipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // ✅ เพิ่มอุปกรณ์ใหม่
    @PostMapping
    public ITEquipment addEquipment(@RequestBody ITEquipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // ✅ ค้นหาเฉพาะอุปกรณ์ที่ว่างให้ยืมได้
    @GetMapping("/available")
    public List<ITEquipment> getAvailableEquipment() {
        return equipmentRepository.findByAvailableTrue();
    }

    // ✅ ค้นหาตามประเภท (optional)
    @GetMapping("/type")
    public List<ITEquipment> getByType(@RequestParam String type) {
        return equipmentRepository.findByTypeIgnoreCase(type);
    }
}
