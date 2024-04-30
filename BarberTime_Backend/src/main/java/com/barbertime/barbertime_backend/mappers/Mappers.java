package com.barbertime.barbertime_backend.mappers;

import com.barbertime.barbertime_backend.dtos.*;
import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.*;
import com.barbertime.barbertime_backend.entities.*;

public interface Mappers {
    AppointmentResDTO toAppointmentResDTO(Appointment appointment);
    Appointment toAppointment(AppointmentReqDTO appointmentDTO);
    BarberShopResDTO toBarberShopResDTO(BarberShop barberShop);
    BarberShop toBarberShop(BarberShopReqDTO barberShopDTO);
    CustomerResDTO toCustomerResDTO(Customer customer);
    Customer toCustomer(CustomerReqDTO customerDTO);
    HairdresserResDTO toHairdresserResDTO(Hairdresser hairdresser);
    Hairdresser toHairdresser(HairdresserReqDTO hairdresserReqDTO);
    HolidayResDTO toHolidayResDTO(Holiday holiday);
    Holiday toHoliday(HolidayReqDTO holidayDTO);
    OwnerResDTO toOwnerResDTO(Owner owner);
    Owner toOwner(OwnerReqDTO ownerDTO);
    RoleDTO toRoleDTO(Role role);
    Role toRole(RoleDTO roleDTO);
    BarberServiceDTO toBarberServiceDTO(BarberService barberService);
    BarberService toBarberService(BarberServiceDTO barberServiceDTO);
    ReviewResDTO toReviewResDTO(Review review);
    Review toReview(ReviewReqDTO reviewDTO);
    FileDataResDTO toFileDataResDTO(FileData fileData);
    Admin toAdmin(AdminDTO adminDTO);
}
