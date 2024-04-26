package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentResDTO {
    private Long idAppointment;
    private Date date;
    private Time time;
    private EStatus status;
    private BarberShopResDTO barberShopDTO;
}