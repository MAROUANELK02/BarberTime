package com.barbertime.barbertime_backend.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HolidayResDTO {
    private Long idHoliday;
    private LocalDate holidayDate;
    private String reason;
    private BarberShopResDTO barberShopDTO;
}