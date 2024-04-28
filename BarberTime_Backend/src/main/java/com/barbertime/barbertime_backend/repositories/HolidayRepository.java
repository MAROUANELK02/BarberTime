package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Page<Holiday> findAllByBarberShopIdBarberShop(Long idBarberShop, Pageable pageable);
    List<Holiday> findAllByBarberShopIdBarberShop(Long idBarberShop);
}
