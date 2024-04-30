package com.barbertime.barbertime_backend.dtos.req;

import com.barbertime.barbertime_backend.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentReqDTO {
    private Long idAppointment;
    private LocalDate date;
    private LocalTime time;
    private EStatus status;
}