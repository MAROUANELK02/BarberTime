package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.req.AppointmentReqDTO;
import com.barbertime.barbertime_backend.dtos.req.CustomerReqDTO;
import com.barbertime.barbertime_backend.dtos.req.ReviewReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.ReviewResDTO;
import com.barbertime.barbertime_backend.exceptions.CustomerNotFoundException;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerResDTO createCustomer(CustomerReqDTO customerDTO);
    Page<AppointmentResDTO> getAppointments(Long idCustomer, int page, int size);
    AppointmentResDTO saveAppointment(Long idCustomer, AppointmentReqDTO appointmentReqDTO) throws CustomerNotFoundException;
    CustomerResDTO getCustomer(Long idCustomer) throws CustomerNotFoundException;
    CustomerResDTO updateCustomer(CustomerReqDTO customerDTO);
    void deleteCustomer(Long idCustomer);
    Page<BarberShopResDTO> getAllBarberShops(int page, int size);
    ReviewResDTO addReview(ReviewReqDTO reviewReqDTO);
    Page<ReviewResDTO> getReviews(Long idCustomer, int page, int size);
}
