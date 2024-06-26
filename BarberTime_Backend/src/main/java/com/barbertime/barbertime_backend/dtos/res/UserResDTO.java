package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.dtos.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class UserResDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    private String username;
    private List<RoleDTO> roleDTO;
}