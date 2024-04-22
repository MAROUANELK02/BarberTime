package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HairdresserRepository extends JpaRepository<Hairdresser, Long> {
}
