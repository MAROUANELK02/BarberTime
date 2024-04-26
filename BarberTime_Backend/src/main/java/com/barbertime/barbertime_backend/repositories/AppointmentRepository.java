package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findAllByBarberShopIdBarberShop(Long barberShopId, Pageable pageable);
    Page<Appointment> findAllByBarberShopIdBarberShopAndDate(Long barberShopId, Date date, Pageable pageable);
    Page<Appointment> findAllByCustomerIdUser(Long customerId, Pageable pageable);
}
