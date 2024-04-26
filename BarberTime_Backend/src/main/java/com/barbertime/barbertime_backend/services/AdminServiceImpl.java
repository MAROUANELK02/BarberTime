package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.entities.BarberService;
import com.barbertime.barbertime_backend.entities.Role;
import com.barbertime.barbertime_backend.enums.ERole;
import com.barbertime.barbertime_backend.enums.EService;
import com.barbertime.barbertime_backend.repositories.BarberServiceRepository;
import com.barbertime.barbertime_backend.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private BarberServiceRepository barberServiceRepository;
    private RoleRepository roleRepository;

    @Override
    public void saveAllServices() {
        log.info("Saving all services");
        Random random = new Random();
        for (EService service : EService.values()) {
            int price = random.nextInt((150 - 50) + 1) + 50;
            barberServiceRepository.save(BarberService.builder()
                            .serviceName(service)
                            .price(price)
                            .build());
        }
        log.info("All services saved");
    }

    @Override
    public void saveAllRoles() {
        log.info("Saving all roles");
        for (ERole role : ERole.values()) {
            roleRepository.save(Role.builder()
                            .roleName(role)
                            .build());
        }
        log.info("All roles saved");
    }
}
