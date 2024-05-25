package com.barbertime.barbertime_backend.security.controllers;

import com.barbertime.barbertime_backend.dtos.res.UserResDTO;
import com.barbertime.barbertime_backend.entities.User;
import com.barbertime.barbertime_backend.repositories.UserRepository;
import com.barbertime.barbertime_backend.security.payload.request.SignupChangePass;
import com.barbertime.barbertime_backend.security.payload.request.SignupCheck;
import com.barbertime.barbertime_backend.security.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SignUp {
    private AccountService accountService;

    @PostMapping("/register")
    public int sendEmailForRegister(@RequestBody SignupCheck signupCheck) {
            return accountService.sendMail(signupCheck.getEmail());
    }

    @PostMapping("/changePassword")
    public int sendEmailForChangePassword(@RequestBody SignupCheck signupCheck) {
        return accountService.checkingEmail(signupCheck.getEmail());
    }

    @PutMapping("/changePassword")
    public UserResDTO changePassword(@RequestBody SignupChangePass signupChangePass) {
        return accountService.changePassword(signupChangePass.getEmail(),signupChangePass.getPassword(),
                signupChangePass.getConfirmedPassword());
    }
}
