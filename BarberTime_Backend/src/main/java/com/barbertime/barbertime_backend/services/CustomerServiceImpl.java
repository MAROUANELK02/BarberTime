package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.req.AppointmentReqDTO;
import com.barbertime.barbertime_backend.dtos.req.CustomerReqDTO;
import com.barbertime.barbertime_backend.dtos.req.ReviewReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.ReviewResDTO;
import com.barbertime.barbertime_backend.entities.Appointment;
import com.barbertime.barbertime_backend.entities.Customer;
import com.barbertime.barbertime_backend.entities.Review;
import com.barbertime.barbertime_backend.enums.ERole;
import com.barbertime.barbertime_backend.exceptions.CustomerNotFoundException;
import com.barbertime.barbertime_backend.mappers.Mappers;
import com.barbertime.barbertime_backend.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final AppointmentRepository appointmentRepository;
    private Mappers mappers;
    private CustomerReposirtory customerRepository;
    private RoleRepository roleRepository;
    private BarberShopRepository barberShopRepository;
    private ReviewRepository reviewRepository;

    @Override
    public CustomerResDTO createCustomer(CustomerReqDTO customerDTO) {
        log.info("Creating customer");
        Customer customer = mappers.toCustomer(customerDTO);
        customer.setRole(roleRepository.findByRoleName(ERole.ROLE_USER));
        customerRepository.save(customer);
        log.info("Customer created");
        return mappers.toCustomerResDTO(customer);
    }

    @Override
    public Page<AppointmentResDTO> getAppointments(Long idCustomer, int page, int size) {
        return appointmentRepository.findAllByCustomerIdUser(idCustomer, PageRequest.of(page, size))
                .map(mappers::toAppointmentResDTO);
    }

    @Override
    public AppointmentResDTO saveAppointment(Long idCustomer, AppointmentReqDTO appointmentReqDTO) throws CustomerNotFoundException {
        log.info("Saving appointment");
        Appointment appointment = mappers.toAppointment(appointmentReqDTO);
        Customer customer = customerRepository.findById(idCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(idCustomer));
        appointment.setCustomer(customer);
        Appointment save = appointmentRepository.save(appointment);
        customer.getAppointments().add(save);
        customerRepository.save(customer);
        log.info("Appointment saved");
        return mappers.toAppointmentResDTO(save);
    }

    @Override
    public CustomerResDTO getCustomer(Long idCustomer) throws CustomerNotFoundException {
        return mappers.toCustomerResDTO(customerRepository.findById(idCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(idCustomer)));
    }

    @Override
    public CustomerResDTO updateCustomer(CustomerReqDTO customerDTO) {
        log.info("Updating customer");
        return mappers.toCustomerResDTO(customerRepository.save(mappers.toCustomer(customerDTO)));
    }

    @Override
    public void deleteCustomer(Long idCustomer) {
        log.info("Deleting customer");
        try {
            customerRepository.deleteById(idCustomer);
            log.info("Customer deleted");
        } catch (Exception e) {
            log.error("Error deleting customer", e);
        }
    }

    @Override
    public Page<BarberShopResDTO> getAllBarberShops(int page, int size) {
        log.info("Getting all barber shops");
        return barberShopRepository.findAll(PageRequest.of(page, size)).map(mappers::toBarberShopResDTO);
    }

    @Override
    public ReviewResDTO addReview(ReviewReqDTO reviewReqDTO) {
        log.info("Adding review");
        Review review = mappers.toReview(reviewReqDTO);
        Review save = reviewRepository.save(review);
        review.getCustomer().getReviews().add(save);
        review.getBarberShop().getReviews().add(save);
        log.info("Review added");
        return mappers.toReviewResDTO(save);
    }

    @Override
    public Page<ReviewResDTO> getReviews(Long idCustomer, int page, int size) {
        log.info("Getting reviews");
        return reviewRepository.findAllByCustomerIdUser(idCustomer, PageRequest.of(page, size))
                .map(mappers::toReviewResDTO);
    }
}
