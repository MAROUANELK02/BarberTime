package com.barbertime.barbertime_backend;

import com.barbertime.barbertime_backend.services.AdminService;
import com.barbertime.barbertime_backend.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BarberTimeBackendApplication {
    @Autowired
    private AdminService adminService;
    @Autowired
    private OwnerService ownerService;

    public static void main(String[] args) {
        SpringApplication.run(BarberTimeBackendApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            adminService.saveAllServices();
            adminService.saveAllRoles();
        };
    }

}
