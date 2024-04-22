package com.barbertime.barbertime_backend.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO extends UserDTO {
    private List<AppointmentDTO> appointmentsDTO;
}
