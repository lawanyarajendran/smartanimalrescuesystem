package com.animalrescue.smartrescue.services;

import com.animalrescue.smartrescue.repositories.VolunteerRepository;
import com.animalrescue.smartrescue.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
@Service
public class VolunteerService {
    private static final Logger logger =
            LoggerFactory.getLogger(VolunteerService.class);
    @Autowired
    private VolunteerRepository repository;

    // CREATE
    public Volunteer createVolunteer(Volunteer volunteer) {

        logger.info("Creating volunteer: {}",
                volunteer.getVolunteerName());

        return repository.save(volunteer);
    }

    // GET BY ID
    public Volunteer getVolunteerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Volunteer not found"));
    }

    // GET ALL
    public List<Volunteer> getAllVolunteers() {
        return repository.findAll();
    }
    public Page<Volunteer> getAllVolunteersPages(int page, int size) {
        logger.info("Fetching all volunteers");
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
    // UPDATE
    public Volunteer updateVolunteer(Volunteer volunteer) {
        return repository.save(volunteer);
    }

    // DELETE
    public void deleteVolunteer(Long id) {

        logger.info("Deleting volunteer with id: {}", id);

        repository.deleteById(id);
    }
}