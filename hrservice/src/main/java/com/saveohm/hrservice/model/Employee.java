package com.saveohm.hrservice.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String firstname;
    private String lastname;
    private String position;
    private double salary;
    private String phone;

    public Employee(String title, String firstname, String lastname, String position, double salary, String phone) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.salary = salary;
        this.phone = phone;
    }
}
