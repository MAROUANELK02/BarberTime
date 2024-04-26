package com.barbertime.barbertime_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    private String username;
    @JsonIgnore
    private String password;
    private RoleDTO roleDTO;
}