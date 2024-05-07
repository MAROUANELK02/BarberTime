/*
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
@RequestMapping("/api/register")
public class SignUp {
    private AccountService accountService;
    private UserRepository userRepository;

    @PostMapping("/sendEmail")
    public int sendEmail(@RequestBody SignupCheck signupCheck) {
        User user = userRepository.findByEmailContains(signupCheck.getEmail());
        if(user != null) {
            return accountService.checkingEmail(signupCheck.getEmail());
        }else{
            throw new RuntimeException("Email inexistant !");
        }
    }

    @PutMapping("/changePassword")
    public UserResDTO changePassword(@RequestBody SignupChangePass signupChangePass) {
        return accountService.changePassword(signupChangePass.getEmail(),signupChangePass.getPassword(),
                signupChangePass.getConfirmedPassword());
    }
}
*/
