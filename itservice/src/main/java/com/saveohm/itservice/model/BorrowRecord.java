package com.saveohm.itservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId; // รหัสพนักงาน (อ้างอิงจาก HR)

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private ITEquipment equipment;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
}