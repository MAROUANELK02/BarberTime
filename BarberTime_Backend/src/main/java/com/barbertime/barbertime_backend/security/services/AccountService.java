package com.barbertime.barbertime_backend.security.services;

import com.barbertime.barbertime_backend.dtos.res.UserResDTO;

public interface AccountService {

    int generateCode();
    int sendMail(String toEmail);

    //check mail to change password
    int checkingEmail(String address);
    UserResDTO changePassword(String email, String password, String confirmedPassword);
}
