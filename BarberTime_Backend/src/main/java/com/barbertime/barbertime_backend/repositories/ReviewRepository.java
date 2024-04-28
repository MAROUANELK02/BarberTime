package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByCustomerIdUser(Long idCustomer, Pageable pageable);
    List<Review> findAllByBarberShopIdBarberShop(Long idBarberShop);
}
