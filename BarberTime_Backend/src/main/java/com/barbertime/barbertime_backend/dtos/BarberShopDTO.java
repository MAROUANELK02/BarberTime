package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.entities.*;
import com.barbertime.barbertime_backend.enums.ENeighborhood;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BarberShopDTO {
    private Long idBarberShop;
    private String name;
    private String address;
    private ENeighborhood neighborhood;
    private String authorizationNumber;
    private int capacity;
    private LocalDate dayOff;
    private OwnerDTO ownerDTO;
}
