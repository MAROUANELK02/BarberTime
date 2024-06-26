package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Appointment;
import com.barbertime.barbertime_backend.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findAllByBarberShopIdBarberShop(Long barberShopId, Pageable pageable);
    Page<Appointment> findAllByBarberShopIdBarberShopAndDate(Long barberShopId, LocalDate date, Pageable pageable);
    List<Appointment> findAllByBarberShopIdBarberShopAndDateAndTime(Long barberShopId, LocalDate date, LocalTime time);
    Page<Appointment> findAllByCustomerIdUserAndStatus(Long customerId, EStatus status, Pageable pageable);
    List<Appointment> findByDate(LocalDate date);
}
