package com.animalrescue.smartrescue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {

    @GetMapping("/hello")
    public String hello() {
        return "Animal Rescue Backend Working";
    }
}