package com.saveohm.itservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saveohm.itservice.model.ITEquipment;

public interface ITEquipmentRepository extends JpaRepository<ITEquipment, Long> {
    List<ITEquipment> findByAvailableTrue();
    List<ITEquipment> findByTypeIgnoreCase(String type);
}
