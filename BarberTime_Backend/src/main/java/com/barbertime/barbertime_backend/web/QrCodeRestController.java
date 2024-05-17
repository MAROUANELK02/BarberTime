package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.entities.Appointment;
import com.barbertime.barbertime_backend.entities.BarberShop;
import com.barbertime.barbertime_backend.entities.Customer;
import com.barbertime.barbertime_backend.repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class QrCodeRestController {
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/appointment/{id}")
    public String showAppointment(@PathVariable("id") Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        BarberShop barberShop = appointment.getBarberShop();
        Customer customer = appointment.getCustomer();
        model.addAttribute("customer", customer);
        model.addAttribute("barberShop", barberShop);
        model.addAttribute("appointment", appointment);
        return "appointment";
    }
}
