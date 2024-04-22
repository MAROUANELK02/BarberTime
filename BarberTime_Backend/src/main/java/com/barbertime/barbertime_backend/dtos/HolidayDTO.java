package com.barbertime.barbertime_backend.dtos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HolidayDTO {
    private Long idHoliday;
    private LocalDate holidayDate;
    private String reason;
    private BarberShopDTO barberShopDTO;
}