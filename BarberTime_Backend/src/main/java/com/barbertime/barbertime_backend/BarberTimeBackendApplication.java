package com.barbertime.barbertime_backend;

import com.barbertime.barbertime_backend.services.AdminService;
import com.barbertime.barbertime_backend.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class BarberTimeBackendApplication {
    private AdminService adminService;

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
