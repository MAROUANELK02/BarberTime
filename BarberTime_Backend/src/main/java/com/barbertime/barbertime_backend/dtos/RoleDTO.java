package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.enums.ERole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    private Long idRole;
    private ERole roleName;
}