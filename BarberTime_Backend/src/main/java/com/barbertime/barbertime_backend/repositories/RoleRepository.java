package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Role;
import com.barbertime.barbertime_backend.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleNameContains(ERole roleName);
}
