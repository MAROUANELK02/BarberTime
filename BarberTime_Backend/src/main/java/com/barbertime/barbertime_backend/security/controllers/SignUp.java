package com.barbertime.barbertime_backend.security.controllers;

import com.barbertime.barbertime_backend.dtos.req.BarberShopReqDTO;
import com.barbertime.barbertime_backend.dtos.req.CustomerReqDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.CustomerResDTO;
import com.barbertime.barbertime_backend.dtos.res.UserResDTO;
import com.barbertime.barbertime_backend.entities.User;
import com.barbertime.barbertime_backend.repositories.UserRepository;
import com.barbertime.barbertime_backend.security.payload.request.SignupChangePass;
import com.barbertime.barbertime_backend.security.payload.request.SignupCheck;
import com.barbertime.barbertime_backend.security.services.AccountService;
import com.barbertime.barbertime_backend.services.CustomerService;
import com.barbertime.barbertime_backend.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*",maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SignUp {
    private AccountService accountService;
    private CustomerService customerService;
    private OwnerService ownerService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Integer>> sendEmailForRegister(@RequestBody SignupCheck signupCheck) {
        int verificationCode = accountService.sendMail(signupCheck.getEmail());
        Map<String, Integer> response = new HashMap<>();
        response.put("verificationCode", verificationCode);
        return ResponseEntity.ok(response);
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

    @PostMapping("/createCustomer")
    public CustomerResDTO createCustomer(@RequestBody CustomerReqDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @PostMapping("/createBarberShop")
    public BarberShopResDTO createBarberShop(@RequestBody BarberShopReqDTO barberShopDTO) {
        return ownerService.createBarberShop(barberShopDTO);
    }
}
