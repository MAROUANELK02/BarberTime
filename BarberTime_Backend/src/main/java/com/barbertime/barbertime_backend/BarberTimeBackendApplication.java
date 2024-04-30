package com.barbertime.barbertime_backend;

import com.barbertime.barbertime_backend.dtos.AdminDTO;
import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.OwnerResDTO;
import com.barbertime.barbertime_backend.enums.ENeighborhood;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.services.AdminService;
import com.barbertime.barbertime_backend.services.CustomerService;
import com.barbertime.barbertime_backend.services.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class BarberTimeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarberTimeBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AdminService adminService, OwnerService ownerService,
                                        CustomerService customerService) {
        return args -> {

            adminService.saveAllServices();
            adminService.saveAllRoles();

            adminService.saveAdmin(new AdminDTO(1L,"admin", "admin",
                    "0123456789", "admin@example.com",
                    "admin", "admin"));

            OwnerReqDTO owner1 = new OwnerReqDTO(1L, "John", "Doe", "+1234567890",
                    "john.doe@example.com", "john1234", "john", "ok1234");

            OwnerReqDTO owner2 = new OwnerReqDTO(2L, "Jane", "Smith", "+1987654321",
                    "jane.smith@example.com", "jane1234", "jane", "ok4567");

            OwnerReqDTO owner3 = new OwnerReqDTO(3L, "Michael", "Johnson", "+1122334455",
                    "michael.johnson@example.com", "michael1234", "michael", "ok7890");

            BarberShopReqDTO barberShop1 = new BarberShopReqDTO(1L, "Best Cuts", "123 Main St",
                    "+7894581632", ENeighborhood.Beausejour, "auth123", "Monday",
                    LocalTime.of(9,0), LocalTime.of(18,0), owner1);

            BarberShopReqDTO barberShop2 = new BarberShopReqDTO(2L, "Style Haven", "456 Oak Ave",
                    "+1478523694", ENeighborhood.Al_Fida, "auth456", "Tuesday",
                    LocalTime.of(8,30), LocalTime.of(17,30), owner2);

            BarberShopReqDTO barberShop3 = new BarberShopReqDTO(3L, "Shear Excellence", "789 Elm Blvd",
                    "+8521473698", ENeighborhood.Belvedere, "auth789", "Wednesday",
                    LocalTime.of(10,0), LocalTime.of(19,0), owner3);

            ownerService.createBarberShop(barberShop1);
            ownerService.createBarberShop(barberShop2);
            ownerService.createBarberShop(barberShop3);

            ownerService.assignServiceToBarberShop(1L, 1L);
            ownerService.assignServiceToBarberShop(1L, 2L);
            ownerService.assignServiceToBarberShop(1L, 3L);
            ownerService.assignServiceToBarberShop(2L, 1L);
            ownerService.assignServiceToBarberShop(2L, 2L);
            ownerService.assignServiceToBarberShop(3L, 3L);
            ownerService.assignServiceToBarberShop(3L, 4L);

            HairdresserReqDTO hairdresser1 = new HairdresserReqDTO("Emily", "Wilson");
            HairdresserReqDTO hairdresser2 = new HairdresserReqDTO("David", "Martinez");
            HairdresserReqDTO hairdresser3 = new HairdresserReqDTO("Sarah", "Clark");

            ownerService.assignHairdresserToBarberShop(hairdresser1, 1L);
            ownerService.assignHairdresserToBarberShop(hairdresser2, 2L);
            ownerService.assignHairdresserToBarberShop(hairdresser3, 3L);

            HolidayRangeReqDTO holiday1 = new HolidayRangeReqDTO(LocalDate.of(2024,5,26),
                    LocalDate.of(2024,5,30), "Memorial Day");
            HolidayRangeReqDTO holiday2 = new HolidayRangeReqDTO(LocalDate.of(2024,6,26),
                    LocalDate.of(2024,6,30), "Summer Break");
            HolidayRangeReqDTO holiday3 = new HolidayRangeReqDTO(LocalDate.of(2024,7,26),
                    LocalDate.of(2024,7,30), "Vacation");

            ownerService.addHolidayRangeToBarberShop(holiday1, 1L);
            ownerService.addHolidayRangeToBarberShop(holiday2, 2L);
            ownerService.addHolidayRangeToBarberShop(holiday3, 3L);

            CustomerReqDTO customer1 = new CustomerReqDTO(1L, "Alice", "Johnson",
                    "+0987654321", "alice.johnson@example.com", "alice1234", "alice");
            CustomerReqDTO customer2 = new CustomerReqDTO(2L, "Mark", "Davis",
                    "+6789032145", "mark.davis@example.com", "mark1234", "mark");
            CustomerReqDTO customer3 = new CustomerReqDTO(3L, "Jessica", "Miller",
                    "+0293847561", "jessica.miller@example.com", "jessica1234", "jessica");

            customerService.createCustomer(customer1);
            customerService.createCustomer(customer2);
            customerService.createCustomer(customer3);

            AppointmentReqDTO appointment1 = new AppointmentReqDTO(1L,
                    LocalDate.of(2024, 5, 2),
                    LocalTime.of(10, 0), EStatus.CONFIRMED);
            AppointmentReqDTO appointment2 = new AppointmentReqDTO(2L,
                    LocalDate.of(2024, 5, 3),
                    LocalTime.of(11, 0), EStatus.CONFIRMED);
            AppointmentReqDTO appointment3 = new AppointmentReqDTO(3L,
                    LocalDate.of(2024, 5, 4),
                    LocalTime.of(12, 0), EStatus.CONFIRMED);

            customerService.saveAppointment(1L, 1L, appointment1);
            customerService.saveAppointment(2L, 2L, appointment2);
            customerService.saveAppointment(3L, 3L, appointment3);

            ReviewReqDTO review1 = new ReviewReqDTO(1L, "Great service!", 5);
            ReviewReqDTO review2 = new ReviewReqDTO(2L, "Nice haircut.", 4);
            ReviewReqDTO review3 = new ReviewReqDTO(3L, "Could be better.", 3);

            customerService.addReview(review1, 1L, 1L);
            customerService.addReview(review2, 2L, 2L);
            customerService.addReview(review3, 3L, 3L);

        };
    }

}
