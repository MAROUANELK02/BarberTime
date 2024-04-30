package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.dtos.req.UserReqDTO;
import com.barbertime.barbertime_backend.entities.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdminDTO extends UserReqDTO {
    public AdminDTO(Long idUser, String firstName, String lastName, String telNumber, String email,
                          String username, String password) {
        super(idUser, firstName, lastName, telNumber, email, username, password);
    }
}
