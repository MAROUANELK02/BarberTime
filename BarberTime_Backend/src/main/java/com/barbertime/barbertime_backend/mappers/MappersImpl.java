package com.barbertime.barbertime_backend.mappers;

import com.barbertime.barbertime_backend.dtos.*;
import com.barbertime.barbertime_backend.entities.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MappersImpl implements Mappers {
    @Override
    public AppointmentDTO toAppointmentDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .idAppointment(appointment.getIdAppointment())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .status(appointment.getStatus())
                .barberShopDTO(toBarberShopDTO(appointment.getBarberShop()))
                .build();
    }

    @Override
    public Appointment toAppointment(AppointmentDTO appointmentDTO) {
        return Appointment.builder()
                .idAppointment(appointmentDTO.getIdAppointment())
                .date(appointmentDTO.getDate())
                .time(appointmentDTO.getTime())
                .status(appointmentDTO.getStatus())
                .barberShop(toBarberShop(appointmentDTO.getBarberShopDTO()))
                .build();
    }

    @Override
    public BarberShopDTO toBarberShopDTO(BarberShop barberShop) {
        return BarberShopDTO.builder()
                .idBarberShop(barberShop.getIdBarberShop())
                .name(barberShop.getName())
                .address(barberShop.getAddress())
                .phone(barberShop.getPhone())
                .neighborhood(barberShop.getNeighborhood())
                .authorizationNumber(barberShop.getAuthorizationNumber())
                .capacity(barberShop.getCapacity())
                .dayOff(barberShop.getDayOff())
                .ownerDTO(toOwnerDTO(barberShop.getOwner()))
                .startTime(barberShop.getStartTime())
                .endTime(barberShop.getEndTime())
                .ratings(barberShop.getRatings())
                .build();
    }

    @Override
    public BarberShop toBarberShop(BarberShopDTO barberShopDTO) {
        return BarberShop.builder()
                .idBarberShop(barberShopDTO.getIdBarberShop())
                .name(barberShopDTO.getName())
                .address(barberShopDTO.getAddress())
                .phone(barberShopDTO.getPhone())
                .neighborhood(barberShopDTO.getNeighborhood())
                .authorizationNumber(barberShopDTO.getAuthorizationNumber())
                .capacity(barberShopDTO.getCapacity())
                .dayOff(barberShopDTO.getDayOff())
                .startTime(barberShopDTO.getStartTime())
                .endTime(barberShopDTO.getEndTime())
                .ratings(barberShopDTO.getRatings())
                .owner(toOwner(barberShopDTO.getOwnerDTO()))
                .build();
    }

    @Override
    public CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getAppointments().stream().map(this::toAppointmentDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public Customer toCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getAppointmentsDTO().stream().map(this::toAppointment)
                .collect(Collectors.toList()));
    }

    @Override
    public HairdresserDTO toHairdresserDTO(Hairdresser hairdresser) {
        return HairdresserDTO.builder()
                .idHairdresser(hairdresser.getIdHairdresser())
                .firstName(hairdresser.getFirstName())
                .lastName(hairdresser.getLastName())
                .barberShopDTO(toBarberShopDTO(hairdresser.getBarberShop()))
                .build();
    }

    @Override
    public Hairdresser toHairdresser(HairdresserDTO hairdresserDTO) {
        return Hairdresser.builder()
                .idHairdresser(hairdresserDTO.getIdHairdresser())
                .firstName(hairdresserDTO.getFirstName())
                .lastName(hairdresserDTO.getLastName())
                .barberShop(toBarberShop(hairdresserDTO.getBarberShopDTO()))
                .build();
    }

    @Override
    public HolidayDTO toHolidayDTO(Holiday holiday) {
        return HolidayDTO.builder()
                .idHoliday(holiday.getIdHoliday())
                .holidayDate(holiday.getHolidayDate())
                .reason(holiday.getReason())
                .barberShopDTO(toBarberShopDTO(holiday.getBarberShop()))
                .build();
    }

    @Override
    public Holiday toHoliday(HolidayDTO holidayDTO) {
        return Holiday.builder()
                .idHoliday(holidayDTO.getIdHoliday())
                .holidayDate(holidayDTO.getHolidayDate())
                .reason(holidayDTO.getReason())
                .barberShop(toBarberShop(holidayDTO.getBarberShopDTO()))
                .build();
    }

    @Override
    public OwnerDTO toOwnerDTO(Owner owner) {
        return new OwnerDTO(owner.getCin());
    }

    @Override
    public Owner toOwner(OwnerDTO ownerDTO) {
        return new Owner(ownerDTO.getCin());
    }

    @Override
    public RoleDTO toRoleDTO(Role role) {
        return RoleDTO.builder()
                .idRole(role.getIdRole())
                .roleName(role.getRoleName())
                .build();
    }

    @Override
    public Role toRole(RoleDTO roleDTO) {
        return Role.builder()
                .idRole(roleDTO.getIdRole())
                .roleName(roleDTO.getRoleName())
                .build();
    }

    @Override
    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getIdUser(), user.getFirstName(),
                user.getLastName(), user.getTelNumber(), user.getEmail(), user.getUsername(), user.getPassword(),
                toRoleDTO(user.getRole()));
    }

    @Override
    public User toUser(UserDTO userDTO) {
        return new User(userDTO.getIdUser(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getTelNumber(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword(),
                toRole(userDTO.getRoleDTO()));
    }

    @Override
    public BarberServiceDTO toBarberServiceDTO(BarberService barberService) {
        return BarberServiceDTO.builder()
                .idService(barberService.getIdService())
                .serviceName(barberService.getServiceName())
                .price(barberService.getPrice())
                .build();
    }

    @Override
    public BarberService toBarberService(BarberServiceDTO barberServiceDTO) {
        return BarberService.builder()
                .idService(barberServiceDTO.getIdService())
                .serviceName(barberServiceDTO.getServiceName())
                .price(barberServiceDTO.getPrice())
                .build();
    }

    @Override
    public ReviewDTO toReviewDTO(Review review) {
        return ReviewDTO.builder()
                .idReview(review.getIdReview())
                .rating(review.getRating())
                .comment(review.getComment())
                .customerDTO(toCustomerDTO(review.getCustomer()))
                .barberShopDTO(toBarberShopDTO(review.getBarberShop()))
                .build();
    }

    @Override
    public Review toReview(ReviewDTO reviewDTO) {
        return Review.builder()
                .idReview(reviewDTO.getIdReview())
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .customer(toCustomer(reviewDTO.getCustomerDTO()))
                .barberShop(toBarberShop(reviewDTO.getBarberShopDTO()))
                .build();
    }
}
