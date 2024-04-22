package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
