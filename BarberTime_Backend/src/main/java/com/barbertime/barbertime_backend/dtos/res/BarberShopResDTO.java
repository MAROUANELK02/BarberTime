package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.dtos.BarberServiceDTO;
import com.barbertime.barbertime_backend.enums.ENeighborhood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BarberShopResDTO {
    private Long idBarberShop;
    private String name;
    private String address;
    private String phone;
    private ENeighborhood neighborhood;
    private String authorizationNumber;
    private int capacity;
    private String dayOff;
    private LocalTime startTime;
    private LocalTime endTime;
    private int ratings;
    private OwnerResDTO ownerDTO;
    private List<BarberServiceDTO> services;
    private List<FileDataResDTO> images;
}
