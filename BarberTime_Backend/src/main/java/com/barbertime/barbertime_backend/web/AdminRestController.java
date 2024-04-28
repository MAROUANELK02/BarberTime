package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {
    private AdminService adminService;

    @GetMapping("/customers")
    public void getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size) {
        adminService.getAllCustomers(page, size);
    }

    @GetMapping("/owners")
    public void getAllOwners(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size) {
        adminService.getAllOwners(page, size);
    }

    @GetMapping("/barberShops")
    public void getAllBarberShops(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size) {
        adminService.getAllBarberShops(page, size);
    }

    @GetMapping("/barberServices")
    public void getAllBarberServices(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size) {
        adminService.getAllBarberServices(page, size);
    }
}
