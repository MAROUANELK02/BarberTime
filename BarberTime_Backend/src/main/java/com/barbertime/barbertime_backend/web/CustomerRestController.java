package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.dtos.req.AppointmentReqDTO;
import com.barbertime.barbertime_backend.dtos.req.CustomerReqDTO;
import com.barbertime.barbertime_backend.dtos.req.ReviewReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.ReviewResDTO;
import com.barbertime.barbertime_backend.exceptions.CustomerNotFoundException;
import com.barbertime.barbertime_backend.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerRestController {
    private CustomerService customerService;

    @PostMapping("/customer")
    public CustomerResDTO createCustomer(@RequestBody CustomerReqDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping("/customer/appointments/{idCustomer}")
    public Page<AppointmentResDTO> getAppointments(@PathVariable(name = "idCustomer") Long idCustomer,
                                                   @RequestParam int page,
                                                   @RequestParam int size) {
        return customerService.getAppointments(idCustomer, page, size);
    }

    @PostMapping("/customer/appointment/{idCustomer}")
    public AppointmentResDTO saveAppointment(@PathVariable(name = "idCustomer") Long idCustomer,
                                             @RequestBody AppointmentReqDTO appointmentReqDTO) {
        try {
            return customerService.saveAppointment(idCustomer, appointmentReqDTO);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customer/{idCustomer}")
    public CustomerResDTO getCustomer(@PathVariable(name = "idCustomer") Long idCustomer) {
        try {
            return customerService.getCustomer(idCustomer);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/customer")
    public CustomerResDTO updateCustomer(@RequestBody CustomerReqDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customer/{idCustomer}")
    public void deleteCustomer(@PathVariable(name = "idCustomer") Long idCustomer) {
        customerService.deleteCustomer(idCustomer);
    }

    @GetMapping("/barberShops")
    public Page<BarberShopResDTO> getAllBarberShops(@RequestParam int page,
                                                    @RequestParam int size) {
        return customerService.getAllBarberShops(page, size);
    }

    @PostMapping("/review")
    public ReviewResDTO addReview(@RequestBody ReviewReqDTO reviewReqDTO) {
        return customerService.addReview(reviewReqDTO);
    }

    @GetMapping("/reviews/{idCustomer}")
    public Page<ReviewResDTO> getReviews(@PathVariable(name = "idCustomer") Long idCustomer,
                                         @RequestParam int page,
                                         @RequestParam int size) {
        return customerService.getReviews(idCustomer, page, size);
    }

}
