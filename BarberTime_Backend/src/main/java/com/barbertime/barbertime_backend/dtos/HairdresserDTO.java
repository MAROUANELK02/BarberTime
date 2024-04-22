package com.barbertime.barbertime_backend.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HairdresserDTO {
    private Long idHairdresser;
    private String firstName;
    private String lastName;
    private BarberShopDTO barberShopDTO;
}
