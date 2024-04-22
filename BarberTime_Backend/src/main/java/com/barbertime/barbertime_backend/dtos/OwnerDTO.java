package com.barbertime.barbertime_backend.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OwnerDTO extends UserDTO {
    private String cin;
}