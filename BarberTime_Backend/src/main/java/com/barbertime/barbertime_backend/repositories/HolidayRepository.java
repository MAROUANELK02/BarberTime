package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Page<Holiday> findAllByBarberShopIdBarberShopAndHolidayDateAfter(Long idBarberShop, LocalDate date, Pageable pageable);
    Holiday findByBarberShopIdBarberShopAndHolidayDate(Long idBarberShop, LocalDate date);
}
