package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.dtos.BarberServiceDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.OwnerResDTO;
import com.barbertime.barbertime_backend.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
//@PreAuthorize("hasRole('ADMIN')")
public class AdminRestController {
    private AdminService adminService;

    @GetMapping("/customers")
    public Page<CustomerResDTO> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size) {
        return adminService.getAllCustomers(page, size);
    }

    @GetMapping("/owners")
    public Page<OwnerResDTO> getAllOwners(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "3") int size) {
        return adminService.getAllOwners(page, size);
    }

    @GetMapping("/barberShops")
    public Page<BarberShopResDTO> getAllBarberShops(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {
        return adminService.getAllBarberShops(page, size);
    }

    @GetMapping("/barberServices")
    public Page<BarberServiceDTO> getAllBarberServices(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "3") int size) {
        return adminService.getAllBarberServices(page, size);
    }
}
