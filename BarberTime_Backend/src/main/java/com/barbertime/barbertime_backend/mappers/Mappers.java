package com.barbertime.barbertime_backend.mappers;

import com.barbertime.barbertime_backend.dtos.*;
import com.barbertime.barbertime_backend.entities.*;

public interface Mappers {
    AppointmentDTO toAppointmentDTO(Appointment appointment);
    Appointment toAppointment(AppointmentDTO appointmentDTO);
    BarberShopDTO toBarberShopDTO(BarberShop barberShop);
    BarberShop toBarberShop(BarberShopDTO barberShopDTO);
    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
    HairdresserDTO toHairdresserDTO(Hairdresser hairdresser);
    Hairdresser toHairdresser(HairdresserDTO hairdresserDTO);
    HolidayDTO toHolidayDTO(Holiday holiday);
    Holiday toHoliday(HolidayDTO holidayDTO);
    OwnerDTO toOwnerDTO(Owner owner);
    Owner toOwner(OwnerDTO ownerDTO);
    RoleDTO toRoleDTO(Role role);
    Role toRole(RoleDTO roleDTO);
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    BarberServiceDTO toBarberServiceDTO(BarberService barberService);
    BarberService toBarberService(BarberServiceDTO barberServiceDTO);
    ReviewDTO toReviewDTO(Review review);
    Review toReview(ReviewDTO reviewDTO);
}
