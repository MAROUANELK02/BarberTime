/*
package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.enums.ENeighborhood;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BarberShopDTO {
    private Long idBarberShop;
    private String name;
    private String address;
    private String phone;
    private ENeighborhood neighborhood;
    private String authorizationNumber;
    private int capacity;
    private LocalDate dayOff;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private int ratings;
    private OwnerDTO ownerDTO;
}
*/
