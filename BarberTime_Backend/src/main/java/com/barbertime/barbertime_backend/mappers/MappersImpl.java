package com.barbertime.barbertime_backend.mappers;

import com.barbertime.barbertime_backend.dtos.*;
import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.*;
import com.barbertime.barbertime_backend.entities.*;
import com.barbertime.barbertime_backend.repositories.HairdresserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MappersImpl implements Mappers {

    private final HairdresserRepository hairdresserRepository;

    public MappersImpl(HairdresserRepository hairdresserRepository) {
        this.hairdresserRepository = hairdresserRepository;
    }

    @Override
    public AppointmentResDTO toAppointmentResDTO(Appointment appointment) {
        return AppointmentResDTO.builder()
                .idAppointment(appointment.getIdAppointment())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .status(appointment.getStatus())
                .barberShopDTO(toBarberShopResDTO(appointment.getBarberShop()))
                .build();
    }

    @Override
    public Appointment toAppointment(AppointmentReqDTO appointmentDTO) {
        return Appointment.builder()
                .idAppointment(appointmentDTO.getIdAppointment())
                .date(appointmentDTO.getDate())
                .time(appointmentDTO.getTime())
                .status(appointmentDTO.getStatus())
                .barberShop(toBarberShop(appointmentDTO.getBarberShopDTO()))
                .build();
    }

    @Override
    public BarberShopResDTO toBarberShopResDTO(BarberShop barberShop) {
        BarberShopResDTO build = BarberShopResDTO.builder()
                .idBarberShop(barberShop.getIdBarberShop())
                .name(barberShop.getName())
                .address(barberShop.getAddress())
                .phone(barberShop.getPhone())
                .neighborhood(barberShop.getNeighborhood())
                .authorizationNumber(barberShop.getAuthorizationNumber())
                .capacity(barberShop.getCapacity())
                .dayOff(barberShop.getDayOff())
                .ownerDTO(toOwnerResDTO(barberShop.getOwner()))
                .startTime(barberShop.getStartTime())
                .endTime(barberShop.getEndTime())
                .ratings(barberShop.getRatings())
                .ownerDTO(toOwnerResDTO(barberShop.getOwner()))
                .build();
        if(barberShop.getBarberServices() != null)
            build.setServices(barberShop.getBarberServices().stream().map(this::toBarberServiceDTO)
                    .collect(Collectors.toList()));
        return build;
    }

    @Override
    public BarberShop toBarberShop(BarberShopReqDTO barberShopDTO) {
        return BarberShop.builder()
                .idBarberShop(barberShopDTO.getIdBarberShop())
                .name(barberShopDTO.getName())
                .address(barberShopDTO.getAddress())
                .phone(barberShopDTO.getPhone())
                .neighborhood(barberShopDTO.getNeighborhood())
                .authorizationNumber(barberShopDTO.getAuthorizationNumber())
                .dayOff(barberShopDTO.getDayOff())
                .startTime(barberShopDTO.getStartTime())
                .endTime(barberShopDTO.getEndTime())
                .owner(toOwner(barberShopDTO.getOwnerDTO()))
                .build();
    }

    @Override
    public CustomerResDTO toCustomerResDTO(Customer customer) {
        RoleDTO roleDTO = toRoleDTO(customer.getRole());
        return new CustomerResDTO(customer.getIdUser(), customer.getFirstName(),
                customer.getLastName(), customer.getTelNumber(), customer.getEmail(),
                customer.getUsername(),roleDTO,
                customer.getAppointments().stream().map(this::toAppointmentResDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public Customer toCustomer(CustomerReqDTO customerDTO) {
        return new Customer(customerDTO.getIdUser(), customerDTO.getFirstName(),
                customerDTO.getLastName(), customerDTO.getTelNumber(), customerDTO.getEmail(),
                customerDTO.getUsername(), customerDTO.getPassword());
    }

    @Override
    public HairdresserResDTO toHairdresserResDTO(Hairdresser hairdresser) {
        HairdresserResDTO build = HairdresserResDTO.builder()
                .idHairdresser(hairdresser.getIdHairdresser())
                .firstName(hairdresser.getFirstName())
                .lastName(hairdresser.getLastName())
                .build();
        if(hairdresser.getBarberShop() != null) {
            build.setBarberShopDTO(toBarberShopResDTO(hairdresser.getBarberShop()));
        }
        else {
            hairdresserRepository.findById(hairdresser.getIdHairdresser())
                    .ifPresent(hairdresser1 ->
                            build.setBarberShopDTO(toBarberShopResDTO(hairdresser1.getBarberShop())));
        }
        return build;
    }

    @Override
    public Hairdresser toHairdresser(HairdresserReqDTO hairdresserReqDTO) {
        return Hairdresser.builder()
                .firstName(hairdresserReqDTO.getFirstName())
                .lastName(hairdresserReqDTO.getLastName())
                .build();
    }

    @Override
    public HolidayResDTO toHolidayResDTO(Holiday holiday) {
        return HolidayResDTO.builder()
                .idHoliday(holiday.getIdHoliday())
                .holidayDate(holiday.getHolidayDate())
                .reason(holiday.getReason())
                .barberShopDTO(toBarberShopResDTO(holiday.getBarberShop()))
                .build();
    }

    @Override
    public Holiday toHoliday(HolidayReqDTO holidayDTO) {
        return Holiday.builder()
                .idHoliday(holidayDTO.getIdHoliday())
                .holidayDate(holidayDTO.getHolidayDate())
                .reason(holidayDTO.getReason())
                .barberShop(toBarberShop(holidayDTO.getBarberShopDTO()))
                .build();
    }

    @Override
    public OwnerResDTO toOwnerResDTO(Owner owner) {
        RoleDTO roleDTO = toRoleDTO(owner.getRole());
        return new OwnerResDTO(owner.getIdUser(), owner.getFirstName(),
                owner.getLastName(), owner.getTelNumber(), owner.getEmail(),
                owner.getUsername(), roleDTO, owner.getCin());
    }

    @Override
    public Owner toOwner(OwnerReqDTO ownerDTO) {
        return new Owner(ownerDTO.getIdUser(), ownerDTO.getFirstName(),
                ownerDTO.getLastName(), ownerDTO.getTelNumber(), ownerDTO.getEmail(),
                ownerDTO.getUsername(), ownerDTO.getPassword(), ownerDTO.getCin());
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
    public UserResDTO toUserResDTO(User user) {
        return new UserResDTO(user.getIdUser(), user.getFirstName(),
                user.getLastName(), user.getTelNumber(), user.getEmail(),
                user.getUsername(), toRoleDTO(user.getRole()));
    }

    @Override
    public User toUser(UserReqDTO userDTO) {
        return new User(userDTO.getIdUser(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getTelNumber(), userDTO.getEmail(),
                userDTO.getUsername(), userDTO.getPassword());
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
    public ReviewResDTO toReviewResDTO(Review review) {
        return ReviewResDTO.builder()
                .idReview(review.getIdReview())
                .rating(review.getRating())
                .comment(review.getComment())
                .customerDTO(toCustomerResDTO(review.getCustomer()))
                .barberShopDTO(toBarberShopResDTO(review.getBarberShop()))
                .build();
    }

    @Override
    public Review toReview(ReviewReqDTO reviewDTO) {
        return Review.builder()
                .idReview(reviewDTO.getIdReview())
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .customer(toCustomer(reviewDTO.getCustomerDTO()))
                .barberShop(toBarberShop(reviewDTO.getBarberShopDTO()))
                .build();
    }
}
