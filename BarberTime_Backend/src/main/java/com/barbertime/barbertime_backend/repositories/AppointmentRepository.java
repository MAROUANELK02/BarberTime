package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
