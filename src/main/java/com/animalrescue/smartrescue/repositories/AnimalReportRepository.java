package com.animalrescue.smartrescue.repositories;

import com.animalrescue.smartrescue.models.AnimalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalReportRepository extends JpaRepository<AnimalReport, Long> {

}