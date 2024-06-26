package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.dtos.req.AppointmentReqDTO;
import com.barbertime.barbertime_backend.dtos.req.CustomerReqDTO;
import com.barbertime.barbertime_backend.dtos.req.ReviewReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.ReviewResDTO;
import com.barbertime.barbertime_backend.enums.ENeighborhood;
import com.barbertime.barbertime_backend.enums.EService;
import com.barbertime.barbertime_backend.exceptions.BarberShopNotFoundException;
import com.barbertime.barbertime_backend.exceptions.CustomerNotFoundException;
import com.barbertime.barbertime_backend.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('OWNER')")
public class CustomerRestController {
    private CustomerService customerService;

    @GetMapping("/barberShops/{idBarberShop}")
    public BarberShopResDTO getBarberShop(@PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            return customerService.getBarberShop(idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/barberShops/service")
    public List<BarberShopResDTO> getBarberShopsByService(@RequestParam EService service) {
        return customerService.getBarberShopsByService(service);
    }

    @GetMapping("/reviews/{idBarberShop}")
    public List<ReviewResDTO> getReviewsByBarberShopId(@PathVariable(name = "idBarberShop") Long idBarberShop) {
        return customerService.getReviewsByBarberShopId(idBarberShop);
    }

    @GetMapping("/appointments/{idCustomer}")
    public Page<AppointmentResDTO> getAppointments(@PathVariable(name = "idCustomer") Long idCustomer,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return customerService.getAppointments(idCustomer, page, size);
    }

    @PatchMapping("/appointment/{idAppointment}/cancel")
    public void cancelAppointment(@PathVariable(name = "idAppointment") Long idAppointment) {
            customerService.cancelAppointment(idAppointment);
    }

    @PostMapping("/appointment")
    public AppointmentResDTO saveAppointment(@RequestParam Long idCustomer,
                                             @RequestParam Long idBarber,
                                             @RequestBody AppointmentReqDTO appointmentReqDTO) {
        try {
            return customerService.saveAppointment(idCustomer, idBarber, appointmentReqDTO);
        } catch (CustomerNotFoundException | BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{idCustomer}")
    public CustomerResDTO getCustomer(@PathVariable(name = "idCustomer") Long idCustomer) {
        try {
            return customerService.getCustomer(idCustomer);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/{idCustomer}")
    public CustomerResDTO updateCustomer(@PathVariable(name = "idCustomer") Long idCustomer,
            @RequestBody CustomerReqDTO customerDTO) {
        return customerService.updateCustomer(idCustomer, customerDTO);
    }

    @DeleteMapping("/{idCustomer}")
    public void deleteCustomer(@PathVariable(name = "idCustomer") Long idCustomer) {
        customerService.deleteCustomer(idCustomer);
    }

    @GetMapping("/barberShops")
    public Page<BarberShopResDTO> getAllBarberShops(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "15") int size) {
        return customerService.getAllBarberShops(page, size);
    }

    @GetMapping("/barberShops/location")
    public Page<BarberShopResDTO> getAllBarberShopsByLocation(@RequestParam ENeighborhood location,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "15") int size) {
        return customerService.getAllBarberShopsByLocation(location, page, size);
    }

    @PostMapping("/review")
    public ReviewResDTO addReview(@RequestBody ReviewReqDTO reviewReqDTO,
                                  @RequestParam Long customerId,
                                  @RequestParam Long barberShopId) {
        try {
            return customerService.addReview(reviewReqDTO, customerId, barberShopId);
        } catch (CustomerNotFoundException | BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
