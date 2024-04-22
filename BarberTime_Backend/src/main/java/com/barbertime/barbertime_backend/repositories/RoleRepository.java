package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
