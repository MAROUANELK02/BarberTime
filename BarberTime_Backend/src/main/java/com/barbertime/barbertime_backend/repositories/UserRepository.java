package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
