package com.barbertime.barbertime_backend.repositories;

import com.barbertime.barbertime_backend.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
    List<FileData> findAllByBarberShopIdBarberShop(Long barberShopId);
}
