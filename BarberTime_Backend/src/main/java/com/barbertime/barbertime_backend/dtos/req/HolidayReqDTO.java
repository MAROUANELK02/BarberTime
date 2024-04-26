package com.barbertime.barbertime_backend.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HolidayReqDTO {
    private Long idHoliday;
    private Date holidayDate;
    private String reason;
    private BarberShopReqDTO barberShopDTO;
}