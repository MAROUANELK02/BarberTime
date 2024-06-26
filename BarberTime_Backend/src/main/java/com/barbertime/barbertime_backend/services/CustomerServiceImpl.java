package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.req.AppointmentReqDTO;
import com.barbertime.barbertime_backend.dtos.req.CustomerReqDTO;
import com.barbertime.barbertime_backend.dtos.req.ReviewReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.ReviewResDTO;
import com.barbertime.barbertime_backend.entities.*;
import com.barbertime.barbertime_backend.enums.ENeighborhood;
import com.barbertime.barbertime_backend.enums.ERole;
import com.barbertime.barbertime_backend.enums.EService;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.BarberShopNotFoundException;
import com.barbertime.barbertime_backend.exceptions.CustomerNotFoundException;
import com.barbertime.barbertime_backend.mappers.Mappers;
import com.barbertime.barbertime_backend.repositories.*;
import com.barbertime.barbertime_backend.security.Email.EmailSenderService;
import com.google.zxing.WriterException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private HolidayRepository holidayRepository;
    private BarberServiceRepository barberServiceRepository;
    private EmailSenderService emailSenderService;
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomerResDTO createCustomer(CustomerReqDTO customerDTO) {
        log.info("Creating customer");
        Customer customer = mappers.toCustomer(customerDTO);
        customer.getRole().add(roleRepository.findByRoleName(ERole.ROLE_USER));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setPassword(customer.getPassword());
        customerRepository.save(customer);
        log.info("Customer created");
        return mappers.toCustomerResDTO(customer);
    }

    @Override
    public Page<AppointmentResDTO> getAppointments(Long idCustomer, int page, int size) {
        return appointmentRepository.findAllByCustomerIdUserAndStatus(idCustomer, EStatus.CONFIRMED, PageRequest.of(page, size))
                .map(mappers::toAppointmentResDTO);
    }

    @Override
    public AppointmentResDTO saveAppointment(Long idCustomer, Long idBarber, AppointmentReqDTO appointmentReqDTO) throws CustomerNotFoundException, BarberShopNotFoundException {
        log.info("Saving appointment");
        Appointment appointment = mappers.toAppointment(appointmentReqDTO);
        appointment.setStatus(EStatus.CONFIRMED);

        BarberShop barberShop = barberShopRepository.findById(idBarber)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        Customer customer = customerRepository.findById(idCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Holiday holiday = holidayRepository.findByBarberShopIdBarberShopAndHolidayDate(idBarber, appointment.getDate());

        if(holiday != null) {
                throw new RuntimeException("Holiday on this date");
        }

        if(appointment.getTime().isBefore(barberShop.getStartTime()) || appointment.getTime()
                .isAfter(barberShop.getEndTime().minusHours(1)))
            throw new RuntimeException("Barber shop is closed on this time");

        List<Appointment> appointments = appointmentRepository.findAllByBarberShopIdBarberShopAndDateAndTime(idBarber,appointment.getDate(),appointment.getTime());
        int size = appointments.size();
        if(size > 0) {
            if(size >= barberShop.getCapacity()) {
                throw new RuntimeException("Barber shop is full on this time");
            }else {
                for(Appointment appointment1 : appointments) {
                    if(appointment1.getCustomer().getIdUser().equals(idCustomer)) {
                        throw new RuntimeException("You already have an appointment on this time");
                    }
                }
            }
        }
        BarberService barberService = barberServiceRepository.findByBarberShopIdBarberShopAndServiceName(idBarber, appointmentReqDTO.getService());
        appointment.setBarberService(barberService);
        appointment.setBarberShop(barberShop);
        appointment.setCustomer(customer);
        Appointment save = appointmentRepository.save(appointment);
        String qrCodeData = "http://localhost:5000/appointment/" + save.getIdAppointment();
        try {
            // Include the appointment ID in the file name
            String qrCodePath = "QRCode_" + save.getIdAppointment() + ".png";
            emailSenderService.generateQRCodeImage(qrCodeData, 350, 350, qrCodePath);
            emailSenderService.sendEmailWithAttachment(customer.getEmail(), "Your Appointment", "Please check your appointment details using the attached QR code.", qrCodePath);
        } catch (WriterException | IOException e) {
            log.error("Could not generate QR code", e);
        }

        log.info("Appointment saved");
        return mappers.toAppointmentResDTO(save);
    }

    @Override
    public CustomerResDTO getCustomer(Long idCustomer) throws CustomerNotFoundException {
        return mappers.toCustomerResDTO(customerRepository.findById(idCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found")));
    }

    @Override
    public CustomerResDTO updateCustomer(Long customerId, CustomerReqDTO customerDTO) {
        log.info("Updating customer");
        try {
            Customer customerFound = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
            if(customerDTO.getFirstName() != null)
                customerFound.setFirstName(customerDTO.getFirstName());
            if(customerDTO.getLastName() != null)
                customerFound.setLastName(customerDTO.getLastName());
            if(customerDTO.getEmail() != null)
                customerFound.setEmail(customerDTO.getEmail());
            if(customerDTO.getTelNumber() != null)
                customerFound.setTelNumber(customerDTO.getTelNumber());
            if(customerDTO.getUsername() != null)
                customerFound.setUsername(customerDTO.getUsername());
            if(customerDTO.getPassword() != null)
                customerFound.setPassword(customerDTO.getPassword());
            Customer save = customerRepository.save(customerFound);
            return mappers.toCustomerResDTO(save);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
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
    public void cancelAppointment(Long idAppointment) {
        log.info("Cancelling appointment");
        try {
            Appointment appointment = appointmentRepository.findById(idAppointment).orElse(null);
            if(appointment != null) {
                appointment.setStatus(EStatus.CANCELED);
                appointmentRepository.save(appointment);
            }
            log.info("Appointment cancelled");
        } catch (Exception e) {
            log.error("Error cancelling appointment", e);
        }
    }

    @Override
    public Page<BarberShopResDTO> getAllBarberShopsByLocation(ENeighborhood location, int page, int size) {
        log.info("Getting all barber shops by location");
        return barberShopRepository.findAllByNeighborhood(location, PageRequest.of(page, size))
                .map(mappers::toBarberShopResDTO);
    }

    @Override
    public ReviewResDTO addReview(ReviewReqDTO reviewReqDTO, Long customerId, Long barberShopId) throws CustomerNotFoundException, BarberShopNotFoundException {
        log.info("Adding review");
        Review review = mappers.toReview(reviewReqDTO);
        review.setCustomer(customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found")));
        review.setBarberShop(barberShopRepository.findById(barberShopId)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found")));
        Review save = reviewRepository.save(review);
        BarberShop barberShop = barberShopRepository.findById(barberShopId).orElse(null);
        List<Review> reviews = reviewRepository.findAllByBarberShopIdBarberShop(barberShopId);
        int ratings = 0;
        if(!reviews.isEmpty() && barberShop != null) {
            for (Review r : reviews) {
                ratings += r.getRating();
            }
            barberShop.setRatings(ratings / reviews.size());
            barberShop.setTotalVoters(reviews.size());
            barberShopRepository.save(barberShop);
        }
        log.info("Review added");
        return mappers.toReviewResDTO(save);
    }

    @Override
    public Page<ReviewResDTO> getReviews(Long idCustomer, int page, int size) {
        log.info("Getting reviews");
        return reviewRepository.findAllByCustomerIdUser(idCustomer, PageRequest.of(page, size))
                .map(mappers::toReviewResDTO);
    }

    @Override
    public BarberShopResDTO getBarberShop(Long idBarberShop) throws BarberShopNotFoundException {
        return mappers.toBarberShopResDTO(barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found")));
    }

    public List<ReviewResDTO> getReviewsByBarberShopId(Long idBarberShop) {
        return reviewRepository.findAllByBarberShopIdBarberShop(idBarberShop).stream()
                .map(mappers::toReviewResDTO)
                .toList();
    }

    @Override
    public List<BarberShopResDTO> getBarberShopsByService(EService service) {
        log.info("Getting barber shops by service");
        List<BarberShop> barberShops = new ArrayList<>();
        List<BarberService> services = barberServiceRepository.findAllByServiceName(service);
        services.forEach(s -> {
            barberShops.addAll(barberShopRepository.findAllByBarberServicesIdService(s.getIdService()));
        });
        return barberShops.stream()
                .map(mappers::toBarberShopResDTO)
                .toList();
    }
}
