package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.enums.EStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentDTO {
    private Long idAppointment;
    private LocalDate date;
    private LocalTime time;
    private EStatus status;
    private CustomerDTO customerDTO;
    private BarberShopDTO barberShopDTO;
}