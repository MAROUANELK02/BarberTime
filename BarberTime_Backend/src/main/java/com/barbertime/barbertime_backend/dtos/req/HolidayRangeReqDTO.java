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
public class HolidayRangeReqDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
