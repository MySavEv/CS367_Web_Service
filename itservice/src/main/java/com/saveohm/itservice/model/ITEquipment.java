package com.saveohm.itservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ITEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;         // ชื่ออุปกรณ์ เช่น Laptop, Mouse
    private String type;         // ประเภท เช่น Laptop, Monitor, Accessory
    private String brand;        // ยี่ห้อ เช่น Dell, HP
    private String model;        // รุ่น
    private String serialNumber; // หมายเลขประจำเครื่อง
    private boolean available;   // ใช้
}