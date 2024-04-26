package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.BarberServiceDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.OwnerResDTO;
import org.springframework.data.domain.Page;

public interface AdminService {
    void saveAllServices();
    void saveAllRoles();
    Page<CustomerResDTO> getAllCustomers(int page, int size);
    Page<OwnerResDTO> getAllOwners(int page, int size);
    Page<BarberShopResDTO> getAllBarberShops(int page, int size);
    Page<BarberServiceDTO> getAllBarberServices(int page, int size);
}
