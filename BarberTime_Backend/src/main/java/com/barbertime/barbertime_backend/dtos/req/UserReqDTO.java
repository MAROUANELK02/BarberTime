package com.barbertime.barbertime_backend.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserReqDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    private String username;
    private String password;

    public UserReqDTO(String firstName, String lastName, String telNumber, String email,
                       String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}