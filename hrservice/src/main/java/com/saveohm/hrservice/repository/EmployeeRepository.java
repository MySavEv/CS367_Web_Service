package com.saveohm.hrservice.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saveohm.hrservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
     // ค้นหาพนักงานที่เงินเดือนมากกว่า
    List<Employee> findBySalaryGreaterThan(BigDecimal salary);
    
    // ค้นหาพนักงานที่เงินเดือนน้อยกว่า
    List<Employee> findBySalaryLessThan(BigDecimal salary);

    // ค้นหาพนักงานที่เงินเดือนเท่ากับ
    List<Employee> findBySalaryEquals(BigDecimal salary);

    // ค้นหาพนักงานที่มีตำแหน่งตามเงื่อนไข
    List<Employee> findByPositionContainingIgnoreCase(String position);

    // ค้นหาพนักงานตามทุกเงื่อนไข
    @Query("SELECT e FROM Employee e WHERE " +
        "(:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
        "(:position IS NULL OR LOWER(e.position) LIKE LOWER(CONCAT('%', :position, '%'))) AND " +
        "(:minSalary IS NULL OR e.salary >= :minSalary) AND " +
        "(:maxSalary IS NULL OR e.salary <= :maxSalary)")
    List<Employee> search(String title, String position, float minSalary,float maxSalary);
}
