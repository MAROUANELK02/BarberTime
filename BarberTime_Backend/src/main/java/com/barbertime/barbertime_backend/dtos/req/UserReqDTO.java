package com.barbertime.barbertime_backend.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class UserReqDTO {
    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    private String username;
    private String password;
}