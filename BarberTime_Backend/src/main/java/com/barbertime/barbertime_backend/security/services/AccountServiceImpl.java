/*
package com.barbertime.barbertime_backend.security.services;

import com.barbertime.barbertime_backend.dtos.res.UserResDTO;
import com.barbertime.barbertime_backend.entities.Customer;
import com.barbertime.barbertime_backend.entities.Owner;
import com.barbertime.barbertime_backend.entities.User;
import com.barbertime.barbertime_backend.enums.ERole;
import com.barbertime.barbertime_backend.mappers.Mappers;
import com.barbertime.barbertime_backend.repositories.CustomerReposirtory;
import com.barbertime.barbertime_backend.repositories.OwnerRepository;
import com.barbertime.barbertime_backend.repositories.UserRepository;
import com.barbertime.barbertime_backend.security.Email.EmailSenderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private UserRepository userRepository;
    private CustomerReposirtory customerReposirtory;
    private OwnerRepository ownerRepository;
    private EmailSenderService emailSenderService;
    private PasswordEncoder passwordEncoder;
    private Mappers dtoMapper;

    @Override
    public int generateCode() {
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public int sendMail(String toEmail) {
        int code = generateCode();
        emailSenderService.sendEmail(toEmail,"Verification","Votre code de vérification est : "+code);
        return code;
    }

    @Override
    public UserResDTO changePassword(String email, String password, String confirmedPassword) {
        if(password.equals(confirmedPassword)) {

            User user = userRepository.findByEmailContains(email);

            boolean hasAdminRole = user.getRole().stream().anyMatch(role ->
                    role.getRoleName().equals(ERole.ROLE_ADMIN));
            boolean hasOwnerRole = user.getRole().stream().anyMatch(role ->
                    role.getRoleName().equals(ERole.ROLE_OWNER));

            if(hasAdminRole) {
                throw new RuntimeException("Impossible de changer le mot de passe");
            }
            user.setPassword(passwordEncoder.encode(password));
            if(hasOwnerRole) {
                return dtoMapper.toOwnerResDTO(ownerRepository.save((Owner) user));
            } else {
                return dtoMapper.toCustomerResDTO(customerReposirtory.save((Customer) user));
            }

        }else{
            throw new RuntimeException("Les mots de passe sont différents");
        }
    }

    @Override
    public int checkingEmail(String address) {
        if(userRepository.existsByEmail(address)) {
            return sendMail(address);
        }else{
            throw new RuntimeException("User not found !");
        }
    }
}
*/
