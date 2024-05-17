package com.barbertime.barbertime_backend;

import com.barbertime.barbertime_backend.dtos.AdminDTO;
import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
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

    //@Bean
    CommandLineRunner commandLineRunner(AdminService adminService, OwnerService ownerService,
                                        CustomerService customerService) {
        return args -> {

            adminService.saveAllServices();
            adminService.saveAllRoles();

            adminService.saveAdmin(new AdminDTO("admin", "admin",
                    "0123456789", "admin@example.com",
                    "admin", "admin"));

            OwnerReqDTO owner1 = new OwnerReqDTO( "John", "Doe", "+1234567890",
                    "john.doe@example.com", "john1234", "john", "ok1234");

            OwnerReqDTO owner2 = new OwnerReqDTO("Jane", "Smith", "+1987654321",
                    "jane.smith@example.com", "jane1234", "jane", "ok4567");

            OwnerReqDTO owner3 = new OwnerReqDTO("Michael", "Johnson", "+1122334455",
                    "michael.johnson@example.com", "michael1234", "michael", "ok7890");

            BarberShopReqDTO barberShop1 = new BarberShopReqDTO( "Best Cuts", "123 Main St",
                    "+7894581632", ENeighborhood.Beausejour, "auth123", "Monday",
                    LocalTime.of(9,0), LocalTime.of(18,0), owner1);

            BarberShopReqDTO barberShop2 = new BarberShopReqDTO( "Style Haven", "456 Oak Ave",
                    "+1478523694", ENeighborhood.Al_Fida, "auth456", "Tuesday",
                    LocalTime.of(8,30), LocalTime.of(17,30), owner2);

            BarberShopReqDTO barberShop3 = new BarberShopReqDTO( "Shear Excellence", "789 Elm Blvd",
                    "+8521473698", ENeighborhood.Belvedere, "auth789", "Wednesday",
                    LocalTime.of(10,0), LocalTime.of(19,0), owner3);

            BarberShopResDTO barberShop4 = ownerService.createBarberShop(barberShop1);
            BarberShopResDTO barberShop5 = ownerService.createBarberShop(barberShop2);
            BarberShopResDTO barberShop6 = ownerService.createBarberShop(barberShop3);

            ownerService.assignServiceToBarberShop(barberShop4.getIdBarberShop(), 1L);
            ownerService.assignServiceToBarberShop(barberShop4.getIdBarberShop(), 2L);
            ownerService.assignServiceToBarberShop(barberShop4.getIdBarberShop(), 3L);
            ownerService.assignServiceToBarberShop(barberShop5.getIdBarberShop(), 1L);
            ownerService.assignServiceToBarberShop(barberShop5.getIdBarberShop(), 2L);
            ownerService.assignServiceToBarberShop(barberShop6.getIdBarberShop(), 3L);
            ownerService.assignServiceToBarberShop(barberShop6.getIdBarberShop(), 4L);

            HairdresserReqDTO hairdresser1 = new HairdresserReqDTO("Emily", "Wilson");
            HairdresserReqDTO hairdresser2 = new HairdresserReqDTO("David", "Martinez");
            HairdresserReqDTO hairdresser3 = new HairdresserReqDTO("Sarah", "Clark");

            ownerService.assignHairdresserToBarberShop(hairdresser1, barberShop4.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser2, barberShop5.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser3, barberShop6.getIdBarberShop());

            HolidayRangeReqDTO holiday1 = new HolidayRangeReqDTO(LocalDate.of(2024,5,26),
                    LocalDate.of(2024,5,30), "Memorial Day");
            HolidayRangeReqDTO holiday2 = new HolidayRangeReqDTO(LocalDate.of(2024,6,26),
                    LocalDate.of(2024,6,30), "Summer Break");
            HolidayRangeReqDTO holiday3 = new HolidayRangeReqDTO(LocalDate.of(2024,7,26),
                    LocalDate.of(2024,7,30), "Vacation");

            ownerService.addHolidayRangeToBarberShop(holiday1, 1L);
            ownerService.addHolidayRangeToBarberShop(holiday2, 2L);
            ownerService.addHolidayRangeToBarberShop(holiday3, 3L);

            CustomerReqDTO customer1 = new CustomerReqDTO( "Alice", "Johnson",
                    "+0987654321", "alice.johnson@example.com", "alice1234", "alice");
            CustomerReqDTO customer2 = new CustomerReqDTO( "Mark", "Davis",
                    "+6789032145", "mark.davis@example.com", "mark1234", "mark");
            CustomerReqDTO customer3 = new CustomerReqDTO( "Jessica", "Miller",
                    "+0293847561", "jessica.miller@example.com", "jessica1234", "jessica");

            CustomerResDTO customer4 = customerService.createCustomer(customer1);
            CustomerResDTO customer5 = customerService.createCustomer(customer2);
            CustomerResDTO customer6 = customerService.createCustomer(customer3);

            AppointmentReqDTO appointment1 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 2),
                    LocalTime.of(10, 0));
            AppointmentReqDTO appointment2 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 3),
                    LocalTime.of(11, 0));
            AppointmentReqDTO appointment3 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 4),
                    LocalTime.of(12, 0));

            customerService.saveAppointment(customer4.getIdUser(), barberShop4.getIdBarberShop(), appointment1);
            customerService.saveAppointment(customer5.getIdUser(), barberShop5.getIdBarberShop(), appointment2);
            customerService.saveAppointment(customer6.getIdUser(), barberShop6.getIdBarberShop(), appointment3);

            ReviewReqDTO review1 = new ReviewReqDTO( "Great service!", 5);
            ReviewReqDTO review2 = new ReviewReqDTO( "Nice haircut.", 4);
            ReviewReqDTO review3 = new ReviewReqDTO( "Could be better.", 3);

            customerService.addReview(review1, customer4.getIdUser(), barberShop4.getIdBarberShop());
            customerService.addReview(review2, customer5.getIdUser(), barberShop5.getIdBarberShop());
            customerService.addReview(review3, customer6.getIdUser(), barberShop6.getIdBarberShop());

        };
    }

}
