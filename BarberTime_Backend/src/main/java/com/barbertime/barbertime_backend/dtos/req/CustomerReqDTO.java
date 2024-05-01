package com.barbertime.barbertime_backend.dtos.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerReqDTO extends UserReqDTO {
    public CustomerReqDTO(String firstName, String lastName, String telNumber, String email,
                          String username, String password) {
        super(firstName, lastName, telNumber, email, username, password);
    }
}
