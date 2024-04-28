package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.BarberShop;
import com.barbertime.barbertime_backend.enums.ENeighborhood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberShopRepository extends JpaRepository<BarberShop, Long> {
    BarberShop findByOwnerIdUser(Long ownerId);
    Page<BarberShop> findAllByNeighborhood(ENeighborhood neighborhood, Pageable pageable);
}
