package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberShopRepository extends JpaRepository<BarberShop, Long> {
    BarberShop findByOwnerIdUser(Long ownerId);
}
