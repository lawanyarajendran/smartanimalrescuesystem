package com.animalrescue.smartrescue.services;

import com.animalrescue.smartrescue.models.AppUser;
import com.animalrescue.smartrescue.repositories.AppUserRepository;
import com.animalrescue.smartrescue.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public AppUser registerUser(AppUser appUser) {

        appUser.setPassword(
                passwordEncoder.encode(appUser.getPassword())
        );

        return appUserRepository.save(appUser);
    }

    public String loginUser(String email, String password) {

        AppUser user = appUserRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                password,
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(email,user.getRole().name());
    }
}