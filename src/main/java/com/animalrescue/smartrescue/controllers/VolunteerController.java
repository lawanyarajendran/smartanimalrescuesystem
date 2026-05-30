package com.animalrescue.smartrescue.controllers;

import com.animalrescue.smartrescue.models.Volunteer;
import com.animalrescue.smartrescue.services.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class VolunteerController {
    private static final Logger logger =
            LoggerFactory.getLogger(VolunteerController.class);
    @Autowired
    private VolunteerService volunteerService;


    // CREATE VOLUNTEER
    @PostMapping("/volunteer")
    public ResponseEntity<Volunteer> createVolunteer(
           @Valid @RequestBody Volunteer volunteer) {

        return new ResponseEntity<>(
                volunteerService.createVolunteer(volunteer),
                HttpStatus.CREATED
        );
    }


    // GET VOLUNTEER BY ID
    @GetMapping("/volunteer/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(
            @PathVariable Long id) {

        return new ResponseEntity<>(
                volunteerService.getVolunteerById(id),
                HttpStatus.OK
        );
    }


    // GET ALL VOLUNTEERS
    @GetMapping("/volunteers/page")
    public Page<Volunteer> getVolunteersPages(
            @RequestParam int page,
            @RequestParam int size) {

        return volunteerService.getAllVolunteersPages(page, size);
    }

    @GetMapping("/volunteers")
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        logger.info("Fetching all volunteers");
        return new ResponseEntity<>(
                volunteerService.getAllVolunteers(),
                HttpStatus.OK
        );
    }


    // UPDATE VOLUNTEER
    @PutMapping("/volunteer/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(
            @PathVariable Long id,
            @RequestBody Volunteer volunteer) {

        volunteer.setId(id);

        return new ResponseEntity<>(
                volunteerService.updateVolunteer(volunteer),
                HttpStatus.OK
        );
    }


    // DELETE VOLUNTEER
    @DeleteMapping("/volunteer/{id}")
    public ResponseEntity<Void> deleteVolunteer(
            @PathVariable Long id) {

        volunteerService.deleteVolunteer(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}