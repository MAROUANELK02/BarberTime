package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.BarberService;
import com.barbertime.barbertime_backend.enums.EService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberServiceRepository extends JpaRepository<BarberService, Long> {
    BarberService findByBarberShopIdBarberShopAndServiceName(Long barberShopId, EService serviceName);
}
