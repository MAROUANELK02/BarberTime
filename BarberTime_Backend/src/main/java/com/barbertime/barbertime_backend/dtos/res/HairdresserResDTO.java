package com.barbertime.barbertime_backend.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HairdresserResDTO {
    private Long idHairdresser;
    private String firstName;
    private String lastName;
    private BarberShopResDTO barberShopDTO;
}
