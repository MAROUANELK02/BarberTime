package com.barbertime.barbertime_backend;

import com.barbertime.barbertime_backend.dtos.AdminDTO;
import com.barbertime.barbertime_backend.dtos.BarberServiceDTO;
import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.OwnerResDTO;
import com.barbertime.barbertime_backend.entities.Hairdresser;
import com.barbertime.barbertime_backend.enums.ENeighborhood;
import com.barbertime.barbertime_backend.enums.EService;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.BarberShopNotFoundException;
import com.barbertime.barbertime_backend.exceptions.CustomerNotFoundException;
import com.barbertime.barbertime_backend.services.AdminService;
import com.barbertime.barbertime_backend.services.CustomerService;
import com.barbertime.barbertime_backend.services.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class BarberTimeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarberTimeBackendApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(AdminService adminService, OwnerService ownerService,
                                        CustomerService customerService) {
        return args -> {

            adminService.saveAllRoles();

            adminService.saveAdmin(new AdminDTO("admin", "admin",
                    "+212704896354", "admin@example.com",
                    "admin", "admin"));

            OwnerReqDTO owner1 = new OwnerReqDTO("Ahmed", "El Mansouri", "+212600112233", "ahmed.mansouri@example.com", "ahmed", "ahmed", "cin001");
            OwnerReqDTO owner2 = new OwnerReqDTO("Fatima", "Ben Ali", "+212600223344", "fatima.benali@example.com", "fatima", "fatima", "cin002");
            OwnerReqDTO owner3 = new OwnerReqDTO("Omar", "El Haouari", "+212600334455", "omar.haouari@example.com", "omar", "omar", "cin003");
            OwnerReqDTO owner4 = new OwnerReqDTO("Zineb", "El Idrissi", "+212600445566", "zineb.idrissi@example.com", "zineb", "zineb", "cin004");
            OwnerReqDTO owner5 = new OwnerReqDTO("Youssef", "El Yacoubi", "+212600556677", "youssef.yacoubi@example.com", "youssef", "youssef", "cin005");
            OwnerReqDTO owner6 = new OwnerReqDTO("Nour", "El Bakkali", "+212600667788", "nour.bakkali@example.com", "nour", "nour", "cin006");
            OwnerReqDTO owner7 = new OwnerReqDTO("Karim", "El Othmani", "+212600778899", "karim.othmani@example.com", "karim", "karim", "cin007");
            OwnerReqDTO owner8 = new OwnerReqDTO("Sara", "El Amrani", "+212600889900", "sara.amrani@example.com", "sara", "sara", "cin008");
            OwnerReqDTO owner9 = new OwnerReqDTO("Hicham", "El Fassi", "+212600990011", "hicham.fassi@example.com", "hicham", "hicham", "cin009");
            OwnerReqDTO owner10 = new OwnerReqDTO("Leila", "El Khatib", "+212600111122", "leila.khatib@example.com", "leila", "leila", "cin010");

            BarberShopReqDTO barberShop1 = new BarberShopReqDTO("Coiffure Ahmed", "12 Rue de Paris, Beausejour", "+212500334455", ENeighborhood.Beausejour, "auth123", "Monday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner1, List.of(new BarberServiceDTO(1L, EService.COUPE, 50.0), new BarberServiceDTO(4L, EService.MASSAGE, 100.0)));
            BarberShopReqDTO barberShop2 = new BarberShopReqDTO("Fatima's Beauty", "23 Rue de Marrakech, Maarif", "+212500556677", ENeighborhood.Maarif, "auth124", "Tuesday", LocalTime.of(10, 0), LocalTime.of(19, 0), owner2, List.of(new BarberServiceDTO(2L, EService.RASAGE, 30.0), new BarberServiceDTO(6L, EService.MANUCURE, 80.0)));
            BarberShopReqDTO barberShop3 = new BarberShopReqDTO("Omar Coiffure", "45 Avenue Hassan II, Belvedere", "+212500667788", ENeighborhood.Belvedere, "auth125", "Wednesday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner3, List.of(new BarberServiceDTO(3L, EService.COIFFURE, 60.0), new BarberServiceDTO(7L, EService.PEDICURE, 90.0)));
            BarberShopReqDTO barberShop4 = new BarberShopReqDTO("Zineb Salon", "67 Boulevard d'Anfa, Ain Sebaa", "+212500778899", ENeighborhood.Ain_Sebaa, "auth126", "Thursday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner4, List.of(new BarberServiceDTO(5L, EService.SHAMPOING, 20.0), new BarberServiceDTO(8L, EService.EPILATION, 70.0)));
            BarberShopReqDTO barberShop5 = new BarberShopReqDTO("Youssef Barber", "89 Rue Mohamed V, Bourgogne", "+212500889900", ENeighborhood.Bourgogne, "auth127", "Friday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner5, List.of(new BarberServiceDTO(1L, EService.COUPE, 50.0), new BarberServiceDTO(9L, EService.MAQUILLAGE, 50.0)));
            BarberShopReqDTO barberShop6 = new BarberShopReqDTO("Nour Spa", "101 Avenue Al Qods, Hay Hassani", "+212500990011", ENeighborhood.Hay_Hassani, "auth128", "Saturday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner6, List.of(new BarberServiceDTO(4L, EService.MASSAGE, 100.0), new BarberServiceDTO(6L, EService.MANUCURE, 80.0)));
            BarberShopReqDTO barberShop7 = new BarberShopReqDTO("Karim Studio", "123 Rue des Hôpitaux, Quartier des Hôpitaux", "+212500111122", ENeighborhood.Quartier_des_Hopitaux, "auth129", "Sunday", LocalTime.of(10, 0), LocalTime.of(19, 0), owner7, List.of(new BarberServiceDTO(2L, EService.RASAGE, 30.0), new BarberServiceDTO(5L, EService.SHAMPOING, 20.0)));
            BarberShopReqDTO barberShop8 = new BarberShopReqDTO("Sara Coiffure", "145 Boulevard Zerktouni, Sidi Maarouf", "+212500223399", ENeighborhood.Sidi_Maarouf, "auth130", "Monday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner8, List.of(new BarberServiceDTO(3L, EService.COLORATION, 60.0), new BarberServiceDTO(7L, EService.PEDICURE, 90.0)));
            BarberShopReqDTO barberShop9 = new BarberShopReqDTO("Hicham Hair", "167 Rue Anfa, Sidi Moumen", "+212500112299", ENeighborhood.Sidi_Moumen, "auth131", "Tuesday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner9, List.of(new BarberServiceDTO(1L, EService.COUPE, 50.0), new BarberServiceDTO(9L, EService.MAQUILLAGE, 50.0)));
            BarberShopReqDTO barberShop10 = new BarberShopReqDTO("Leila Beauty", "189 Avenue Mohamed VI, Sidi Bernoussi", "+212500223344", ENeighborhood.Sidi_Bernoussi, "auth132", "Wednesday", LocalTime.of(9, 0), LocalTime.of(18, 0), owner10, List.of(new BarberServiceDTO(4L, EService.MASSAGE, 100.0), new BarberServiceDTO(8L, EService.EPILATION, 70.0)));

            BarberShopResDTO barberShop11 = ownerService.createBarberShop(barberShop1);
            BarberShopResDTO barberShop12 = ownerService.createBarberShop(barberShop2);
            BarberShopResDTO barberShop13 = ownerService.createBarberShop(barberShop3);
            BarberShopResDTO barberShop14 = ownerService.createBarberShop(barberShop4);
            BarberShopResDTO barberShop15 = ownerService.createBarberShop(barberShop5);
            BarberShopResDTO barberShop16 = ownerService.createBarberShop(barberShop6);
            BarberShopResDTO barberShop17 = ownerService.createBarberShop(barberShop7);
            BarberShopResDTO barberShop18 = ownerService.createBarberShop(barberShop8);
            BarberShopResDTO barberShop19 = ownerService.createBarberShop(barberShop9);
            BarberShopResDTO barberShop20 = ownerService.createBarberShop(barberShop10);

            // Création des coiffeurs
            HairdresserReqDTO hairdresser1 = new HairdresserReqDTO("Rachid", "Bouazza");
            HairdresserReqDTO hairdresser2 = new HairdresserReqDTO("Khadija", "Bouhia");
            HairdresserReqDTO hairdresser3 = new HairdresserReqDTO("Mustapha", "Ait Lahcen");
            HairdresserReqDTO hairdresser4 = new HairdresserReqDTO("Mounia", "El Hariri");
            HairdresserReqDTO hairdresser5 = new HairdresserReqDTO("Saad", "El Kholti");
            HairdresserReqDTO hairdresser6 = new HairdresserReqDTO("Nadia", "El Gharbi");
            HairdresserReqDTO hairdresser7 = new HairdresserReqDTO("Hamid", "Fakhar");
            HairdresserReqDTO hairdresser8 = new HairdresserReqDTO("Yasmine", "El Hadi");
            HairdresserReqDTO hairdresser9 = new HairdresserReqDTO("Mohamed", "El Yousfi");
            HairdresserReqDTO hairdresser10 = new HairdresserReqDTO("Fatim-Zahra", "El Mahdi");
            HairdresserReqDTO hairdresser11 = new HairdresserReqDTO("Abdelkader", "El Kassimi");
            HairdresserReqDTO hairdresser12 = new HairdresserReqDTO("Nawal", "El Khatib");
            HairdresserReqDTO hairdresser13 = new HairdresserReqDTO("Abdellah", "El Kharraz");
            HairdresserReqDTO hairdresser14 = new HairdresserReqDTO("Naima", "El Kharraz");
            HairdresserReqDTO hairdresser15 = new HairdresserReqDTO("Abdelali", "El Kharraz");

            // Affectation des coiffeurs aux barbershops
            ownerService.assignHairdresserToBarberShop(hairdresser1, barberShop11.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser2, barberShop12.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser11, barberShop12.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser3, barberShop13.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser4, barberShop14.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser12, barberShop14.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser5, barberShop15.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser6, barberShop16.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser13, barberShop16.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser7, barberShop17.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser8, barberShop18.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser14, barberShop18.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser9, barberShop19.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser10, barberShop20.getIdBarberShop());
            ownerService.assignHairdresserToBarberShop(hairdresser15, barberShop20.getIdBarberShop());

            HolidayRangeReqDTO holiday1 = new HolidayRangeReqDTO(LocalDate.of(2024, 6, 10),
                    LocalDate.of(2024, 6, 13), "Fête nationale");
            HolidayRangeReqDTO holiday2 = new HolidayRangeReqDTO(LocalDate.of(2024, 7, 15),
                    LocalDate.of(2024, 7, 18), "Congés d'été");
            HolidayRangeReqDTO holiday3 = new HolidayRangeReqDTO(LocalDate.of(2024, 8, 20),
                    LocalDate.of(2024, 8, 23), "Repos annuel");
            HolidayRangeReqDTO holiday4 = new HolidayRangeReqDTO(LocalDate.of(2024, 9, 10),
                    LocalDate.of(2024, 9, 13), "Fête religieuse");
            HolidayRangeReqDTO holiday5 = new HolidayRangeReqDTO(LocalDate.of(2024, 6, 20),
                    LocalDate.of(2024, 6, 23), "Jours fériés");
            HolidayRangeReqDTO holiday6 = new HolidayRangeReqDTO(LocalDate.of(2024, 7, 5),
                    LocalDate.of(2024, 7, 8), "Congés d'été");
            HolidayRangeReqDTO holiday7 = new HolidayRangeReqDTO(LocalDate.of(2024, 8, 15),
                    LocalDate.of(2024, 8, 18), "Vacances");
            HolidayRangeReqDTO holiday8 = new HolidayRangeReqDTO(LocalDate.of(2024, 9, 25),
                    LocalDate.of(2024, 9, 28), "Jour de repos");
            HolidayRangeReqDTO holiday9 = new HolidayRangeReqDTO(LocalDate.of(2024, 6, 30),
                    LocalDate.of(2024, 7, 3), "Fête nationale");
            HolidayRangeReqDTO holiday10 = new HolidayRangeReqDTO(LocalDate.of(2024, 7, 20),
                    LocalDate.of(2024, 7, 23), "Repos d'été");

            ownerService.addHolidayRangeToBarberShop(holiday1, 1L);
            ownerService.addHolidayRangeToBarberShop(holiday2, 2L);
            ownerService.addHolidayRangeToBarberShop(holiday3, 3L);
            ownerService.addHolidayRangeToBarberShop(holiday4, 4L);
            ownerService.addHolidayRangeToBarberShop(holiday5, 5L);
            ownerService.addHolidayRangeToBarberShop(holiday6, 6L);
            ownerService.addHolidayRangeToBarberShop(holiday7, 7L);
            ownerService.addHolidayRangeToBarberShop(holiday8, 8L);
            ownerService.addHolidayRangeToBarberShop(holiday9, 9L);
            ownerService.addHolidayRangeToBarberShop(holiday10, 10L);

            CustomerReqDTO customer1 = new CustomerReqDTO("Mohamed", "El Amrani",
                    "+212612345678", "lasmak2002@gmail.com", "mohamed1234",
                    "mohamed");
            CustomerReqDTO customer2 = new CustomerReqDTO("Fatima", "Benkirane",
                    "+212612345679", "testseller085@gmail.com", "fatima1234",
                    "fatima");
            CustomerReqDTO customer3 = new CustomerReqDTO("Youssef", "El Idrissi",
                    "+212612345680", "marouanelk02@gmail.com", "youssef1234",
                    "youssef");
            CustomerReqDTO customer4 = new CustomerReqDTO("Saad", "Bennani",
                    "+212612345681", "laaumaris@gmail.com", "saad1234",
                    "saad");
            CustomerReqDTO customer5 = new CustomerReqDTO("Ahmed", "Kabbaj",
                    "+212612345682", "ahmed.kabbaj@example.com", "ahmed1234",
                    "ahmed");

            CustomerResDTO customer6 = customerService.createCustomer(customer1);
            CustomerResDTO customer7 = customerService.createCustomer(customer2);
            CustomerResDTO customer8 = customerService.createCustomer(customer3);
            CustomerResDTO customer9 = customerService.createCustomer(customer4);
            CustomerResDTO customer10 = customerService.createCustomer(customer5);

            AppointmentReqDTO appointment1 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 2),
                    LocalTime.of(10, 0), EService.COUPE);
            AppointmentReqDTO appointment2 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 3),
                    LocalTime.of(11, 0), EService.MANUCURE);
            AppointmentReqDTO appointment3 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 4),
                    LocalTime.of(12, 0), EService.COIFFURE);
            AppointmentReqDTO appointment4 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 5),
                    LocalTime.of(13, 0), EService.SHAMPOING);
            AppointmentReqDTO appointment5 = new AppointmentReqDTO(
                    LocalDate.of(2024, 5, 6),
                    LocalTime.of(14, 0), EService.COUPE);

            new Thread(() -> {
                try {
                    customerService.saveAppointment(customer6.getIdUser(), barberShop11.getIdBarberShop(), appointment1);
                } catch (CustomerNotFoundException | BarberShopNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            new Thread(() -> {
                try {
                    customerService.saveAppointment(customer7.getIdUser(), barberShop12.getIdBarberShop(), appointment2);
                } catch (CustomerNotFoundException | BarberShopNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            new Thread(() -> {
                try {
                    customerService.saveAppointment(customer8.getIdUser(), barberShop13.getIdBarberShop(), appointment3);
                } catch (CustomerNotFoundException | BarberShopNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            new Thread(() -> {
                try {
                    customerService.saveAppointment(customer9.getIdUser(), barberShop14.getIdBarberShop(), appointment4);
                } catch (CustomerNotFoundException | BarberShopNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            new Thread(() -> {
                try {
                    customerService.saveAppointment(customer10.getIdUser(), barberShop15.getIdBarberShop(), appointment5);
                } catch (CustomerNotFoundException | BarberShopNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            // Création des commentaires en français
            ReviewReqDTO review1 = new ReviewReqDTO("Service excellent !", 5);
            ReviewReqDTO review2 = new ReviewReqDTO("Bonne coupe de cheveux.", 4);
            ReviewReqDTO review3 = new ReviewReqDTO("Peut mieux faire.", 3);
            ReviewReqDTO review4 = new ReviewReqDTO("Très satisfait.", 5);
            ReviewReqDTO review5 = new ReviewReqDTO("Accueil chaleureux.", 4);

            customerService.addReview(review1, customer6.getIdUser(), barberShop11.getIdBarberShop());
            customerService.addReview(review2, customer7.getIdUser(), barberShop12.getIdBarberShop());
            customerService.addReview(review3, customer8.getIdUser(), barberShop13.getIdBarberShop());
            customerService.addReview(review4, customer9.getIdUser(), barberShop14.getIdBarberShop());
            customerService.addReview(review5, customer10.getIdUser(), barberShop15.getIdBarberShop());

        };
    }

}
