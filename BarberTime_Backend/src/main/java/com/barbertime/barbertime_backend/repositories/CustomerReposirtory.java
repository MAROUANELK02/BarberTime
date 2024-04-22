package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReposirtory extends JpaRepository<Customer, Long> {
}
