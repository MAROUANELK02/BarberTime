package com.barbertime.barbertime_backend.mappers;

import com.barbertime.barbertime_backend.dtos.*;
import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.*;
import com.barbertime.barbertime_backend.entities.*;
import com.barbertime.barbertime_backend.repositories.HairdresserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .service(appointment.getBarberService().getServiceName())
                .customerDTO(toCustomerResDTO(appointment.getCustomer()))
                .barberShopResDTO(toBarberShopResDTO(appointment.getBarberShop()))
                .build();
    }

    @Override
    public Appointment toAppointment(AppointmentReqDTO appointmentDTO) {
        return Appointment.builder()
                .date(appointmentDTO.getDate())
                .time(appointmentDTO.getTime())
                //.status(appointmentDTO.getStatus())
                .barberService(new BarberService(null, appointmentDTO.getService(), 0, null))
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
                .totalVoters(barberShop.getTotalVoters())
                .ownerDTO(toOwnerResDTO(barberShop.getOwner()))
                .build();
        if(barberShop.getBarberServices() != null)
            build.setServices(barberShop.getBarberServices().stream().map(this::toBarberServiceDTO)
                    .collect(Collectors.toList()));
        if(barberShop.getImages() != null)
            build.setImages(barberShop.getImages().stream().map(this::toFileDataResDTO)
                    .collect(Collectors.toList()));
        return build;
    }

    @Override
    public BarberShop toBarberShop(BarberShopReqDTO barberShopDTO) {
        return BarberShop.builder()
                .name(barberShopDTO.getName())
                .address(barberShopDTO.getAddress())
                .phone(barberShopDTO.getPhone())
                .neighborhood(barberShopDTO.getNeighborhood())
                .authorizationNumber(barberShopDTO.getAuthorizationNumber())
                .dayOff(barberShopDTO.getDayOff())
                .startTime(barberShopDTO.getStartTime())
                .endTime(barberShopDTO.getEndTime())
                .owner(toOwner(barberShopDTO.getOwnerDTO()))
                .barberServices(barberShopDTO.getServices().stream().map(this::toBarberService)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public CustomerResDTO toCustomerResDTO(Customer customer) {
        List<RoleDTO> roleDTO = customer.getRole().stream()
                .map(this::toRoleDTO)
                .collect(Collectors.toList());
        return new CustomerResDTO(customer.getIdUser(), customer.getFirstName(),
                customer.getLastName(), customer.getTelNumber(), customer.getEmail(),
                customer.getUsername(), roleDTO);
    }

    @Override
    public Customer toCustomer(CustomerReqDTO customerDTO) {
        return new Customer(customerDTO.getFirstName(),
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
                .build();
    }

    @Override
    public Holiday toHoliday(HolidayReqDTO holidayDTO) {
        return Holiday.builder()
                .holidayDate(holidayDTO.getHolidayDate())
                .reason(holidayDTO.getReason())
                .build();
    }

    @Override
    public OwnerResDTO toOwnerResDTO(Owner owner) {
        List<RoleDTO> roleDTO = owner.getRole().stream()
                .map(this::toRoleDTO)
                .collect(Collectors.toList());
        return new OwnerResDTO(owner.getIdUser(), owner.getFirstName(),
                owner.getLastName(), owner.getTelNumber(), owner.getEmail(),
                owner.getUsername(), roleDTO, owner.getCin());
    }

    @Override
    public Owner toOwner(OwnerReqDTO ownerDTO) {
        return new Owner(ownerDTO.getFirstName(),
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
                .build();
    }

    @Override
    public Review toReview(ReviewReqDTO reviewDTO) {
        return Review.builder()
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .build();
    }

    @Override
    public FileDataResDTO toFileDataResDTO(FileData fileData) {
        FileDataResDTO fileDataResDTO = new FileDataResDTO();
        BeanUtils.copyProperties(fileData, fileDataResDTO);
        return fileDataResDTO;
    }

    @Override
    public Admin toAdmin(AdminDTO adminDTO) {
        return new Admin(adminDTO.getFirstName(),
                adminDTO.getLastName(), adminDTO.getTelNumber(), adminDTO.getEmail(),
                adminDTO.getUsername(), adminDTO.getPassword());
    }
}
