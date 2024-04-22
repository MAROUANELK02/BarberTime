package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.enums.EService;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceDTO {
    private Long idService;
    private EService serviceName;
    private double price;
}