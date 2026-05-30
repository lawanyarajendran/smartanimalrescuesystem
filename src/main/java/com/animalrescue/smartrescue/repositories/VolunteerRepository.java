package com.animalrescue.smartrescue.repositories;

import com.animalrescue.smartrescue.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository
        extends JpaRepository<Volunteer, Long> {

}