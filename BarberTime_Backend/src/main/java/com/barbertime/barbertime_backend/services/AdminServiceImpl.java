package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.BarberServiceDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.OwnerResDTO;
import com.barbertime.barbertime_backend.entities.*;
import com.barbertime.barbertime_backend.enums.ERole;
import com.barbertime.barbertime_backend.enums.EService;
import com.barbertime.barbertime_backend.mappers.Mappers;
import com.barbertime.barbertime_backend.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private BarberServiceRepository barberServiceRepository;
    private CustomerReposirtory customerReposirtory;
    private BarberShopRepository barberShopRepository;
    private OwnerRepository ownerRepository;
    private RoleRepository roleRepository;
    private Mappers mappers;

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

    @Override
    public Page<CustomerResDTO> getAllCustomers(int page, int size) {
        log.info("Getting all customers");
        Page<CustomerResDTO> map = customerReposirtory.findAll(PageRequest.of(page, size)).map(mappers::toCustomerResDTO);
        System.out.println(map.getContent());
        return map;
    }

    @Override
    public Page<OwnerResDTO> getAllOwners(int page, int size) {
        log.info("Getting all owners");
        return ownerRepository.findAll(PageRequest.of(page, size)).map(mappers::toOwnerResDTO);
    }

    @Override
    public Page<BarberShopResDTO> getAllBarberShops(int page, int size) {
        log.info("Getting all barber shops");
        return barberShopRepository.findAll(PageRequest.of(page, size)).map(mappers::toBarberShopResDTO);
    }

    @Override
    public Page<BarberServiceDTO> getAllBarberServices(int page, int size) {
        log.info("Getting all barber services");
        return barberServiceRepository.findAll(PageRequest.of(page, size)).map(mappers::toBarberServiceDTO);
    }
}
