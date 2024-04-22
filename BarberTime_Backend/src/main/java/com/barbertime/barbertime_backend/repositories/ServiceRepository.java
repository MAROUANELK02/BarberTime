package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
