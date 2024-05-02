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
    private String name;
    private String address;
    private String phone;
    private ENeighborhood neighborhood;
    private String authorizationNumber;
    private String dayOff;
    private LocalTime startTime;
    private LocalTime endTime;
    private OwnerReqDTO ownerDTO;
}
