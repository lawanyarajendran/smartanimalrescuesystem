package com.animalrescue.smartrescue;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rescue")
public class RescueController {

    @GetMapping("/get")
    public String rescue() {
        return "Animal Rescue Backend working";
    }

    //path variable
    @GetMapping("/{id}")
    public String rescueById(@PathVariable long id) {
        return "Animal Rescue with Id " + id;
    }
    // Request param
    @GetMapping("")
    public String rescueByIdparam(@RequestParam long id) {
        return "Animal Rescue with Id " + id;
    }
    @PostMapping("/create")
    public String createUser(@RequestBody String body)
    {
        return body;
    }
    @PutMapping
}