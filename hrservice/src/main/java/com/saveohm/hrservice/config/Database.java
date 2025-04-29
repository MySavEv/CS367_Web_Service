package com.saveohm.hrservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saveohm.hrservice.model.Employee;
import com.saveohm.hrservice.repository.EmployeeRepository;

@Configuration
public class Database {
    public static final Logger log = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository){
        return args -> {
            // Seeding data
            repository.save(new Employee(
                    "Mr.", "John", "Doe", "Software Engineer", 3500.00, "123-456-7890"));
            repository.save(new Employee(
                    "Ms.", "Jane", "Smith", "Product Manager", 4500, "234-567-8901"));
            repository.save(new Employee(
                    "Mr.", "Robert", "Brown", "QA Engineer", 3200, "345-678-9012"));
            repository.save(new Employee(
                    "Dr.", "Alice", "Johnson", "Data Scientist", 6000, "456-789-0123"));
            repository.save(new Employee(
                    "Mrs.", "Mary", "Williams", "HR Manager", 4000, "567-890-1234"));


            log.info("Seed Database Success");

    };

}

}