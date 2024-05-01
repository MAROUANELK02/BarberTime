package com.barbertime.barbertime_backend.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HolidayReqDTO {
    private LocalDate holidayDate;
    private String reason;
}