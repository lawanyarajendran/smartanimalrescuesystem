package com.animalrescue.smartrescue.controllers;
import com.animalrescue.smartrescue.repositories.AppUserRepository;
import com.animalrescue.smartrescue.models.AppUser;
import com.animalrescue.smartrescue.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserRepository userRepository;

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser appUser) {
        return appUserService.registerUser(appUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginData) {

        return appUserService.loginUser(
                loginData.get("email"),
                loginData.get("password")
        );
    }
    @GetMapping("/me")
    public ResponseEntity<AppUser> getLoggedInUser(Authentication authentication) {

        String email = authentication.getName();

        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }
}