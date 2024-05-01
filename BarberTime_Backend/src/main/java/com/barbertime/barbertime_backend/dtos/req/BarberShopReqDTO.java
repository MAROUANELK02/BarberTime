package com.barbertime.barbertime_backend.dtos.req;

import com.barbertime.barbertime_backend.enums.ENeighborhood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BarberShopReqDTO {
    private Long idBarberShop;
    private String name;
    private String address;
    private String phone;
    private ENeighborhood neighborhood;
    private String authorizationNumber;
    private String dayOff;
    private LocalTime startTime;
    private LocalTime endTime;
    private OwnerReqDTO ownerDTO;

    public BarberShopReqDTO(String name, String address, String phone, ENeighborhood neighborhood,
                             String authorizationNumber, String dayOff, LocalTime startTime, LocalTime endTime,
                             OwnerReqDTO ownerDTO) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.neighborhood = neighborhood;
        this.authorizationNumber = authorizationNumber;
        this.dayOff = dayOff;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ownerDTO = ownerDTO;
    }
}
