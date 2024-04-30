package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
}
