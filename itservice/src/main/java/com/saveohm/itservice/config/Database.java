package com.saveohm.itservice.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saveohm.itservice.model.ITEquipment;
import com.saveohm.itservice.repository.ITEquipmentRepository;

@Configuration
public class Database {
        public static final Logger log = LoggerFactory.getLogger(Database.class);

        @Bean
        CommandLineRunner initDatabase(ITEquipmentRepository repository){
                return args -> {
                // Seeding data
                repository.saveAll(List.of(
                        new ITEquipment(null, "Laptop", "Computer", "Dell", "XPS 13", "SN12345", true),
                        new ITEquipment(null, "Monitor", "Display", "LG", "UltraWide", "SN67890", true),
                        new ITEquipment(null, "Mouse", "Accessory", "Logitech", "MX Master 3", "SN54321", false)
                        ));

                log.info("Seed Database Success");

        };

}

}