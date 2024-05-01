package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.dtos.req.UserReqDTO;
import com.barbertime.barbertime_backend.entities.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class AdminDTO extends UserReqDTO {
    public AdminDTO(String firstName, String lastName, String telNumber, String email,
                          String username, String password) {
        super(firstName, lastName, telNumber, email, username, password);
    }
}
