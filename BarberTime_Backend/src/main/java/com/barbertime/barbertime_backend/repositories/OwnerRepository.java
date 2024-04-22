package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
