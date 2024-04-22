package com.barbertime.barbertime_backend.mappers;

import com.barbertime.barbertime_backend.dtos.*;
import com.barbertime.barbertime_backend.entities.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class IMappersImpl implements IMappers {
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
                .owner(toOwner(barberShopDTO.getOwnerDTO()))
                .build();
    }

    @Override
    public CustomerDTO toCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .appointmentsDTO(customer.getAppointments().stream().map(this::toAppointmentDTO).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Customer toCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .appointments(customerDTO.getAppointmentsDTO().stream().map(this::toAppointment).collect(Collectors.toList()))
                .build();
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
        return OwnerDTO.builder()
                .cin(owner.getCin())
                .build();
    }

    @Override
    public Owner toOwner(OwnerDTO ownerDTO) {
        return Owner.builder()
                .cin(ownerDTO.getCin())
                .build();
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
        return UserDTO.builder()
                .idUser(user.getIdUser())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .telNumber(user.getTelNumber())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .roleDTO(toRoleDTO(user.getRole()))
                .build();
    }

    @Override
    public User toUser(UserDTO userDTO) {
        return User.builder()
                .idUser(userDTO.getIdUser())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .telNumber(userDTO.getTelNumber())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(toRole(userDTO.getRoleDTO()))
                .build();
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
}
