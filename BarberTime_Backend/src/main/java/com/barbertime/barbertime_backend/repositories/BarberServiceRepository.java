package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.BarberService;
import com.barbertime.barbertime_backend.enums.EService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberServiceRepository extends JpaRepository<BarberService, Long> {
    BarberService findByBarberShopIdBarberShopAndServiceName(Long barberShopId, EService serviceName);
    List<BarberService> findAllByServiceName(EService service);
}
