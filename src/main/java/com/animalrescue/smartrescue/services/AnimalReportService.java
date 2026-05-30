package com.animalrescue.smartrescue.services;

import com.animalrescue.smartrescue.models.AnimalReport;
import com.animalrescue.smartrescue.models.Volunteer;
import com.animalrescue.smartrescue.repositories.AnimalReportRepository;
import com.animalrescue.smartrescue.repositories.VolunteerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AnimalReportService {

    private static final Logger logger =
            LoggerFactory.getLogger(AnimalReportService.class);

    @Autowired
    private AnimalReportRepository repository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    // CREATE
    public AnimalReport createReport(AnimalReport report) {

        logger.info("Creating animal report: {}",
                report.getAnimalName());

        if (report.getAssignedVolunteer() != null) {

            Volunteer volunteer =
                    volunteerRepository.findById(
                            report.getAssignedVolunteer().getId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Volunteer not found"
                            ));

            report.setAssignedVolunteer(volunteer);
        }

        return repository.save(report);
    }

    // GET BY ID
    public AnimalReport getReportById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Animal Report not found"
                        ));
    }

    // GET ALL
    public List<AnimalReport> getAllReports() {

        logger.info("Fetching all animal reports");

        return repository.findAll();
    }

    // PAGINATION
    public Page<AnimalReport> getAllReportsPages(
            int page,
            int size
    ) {

        logger.info(
                "Fetching paginated animal reports"
        );

        Pageable pageable =
                PageRequest.of(page, size);

        return repository.findAll(pageable);
    }

    // UPDATE
    public AnimalReport updateReport(
            AnimalReport report
    ) {

        logger.info(
                "Updating animal report id: {}",
                report.getId()
        );

        AnimalReport existingReport =
                repository.findById(
                        report.getId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Animal Report not found"
                        ));

        // Update basic fields only if provided
        if (report.getAnimalName() != null) {
            existingReport.setAnimalName(
                    report.getAnimalName()
            );
        }

        if (report.getLocation() != null) {
            existingReport.setLocation(
                    report.getLocation()
            );
        }

        if (report.getInjuryDescription() != null) {
            existingReport.setInjuryDescription(
                    report.getInjuryDescription()
            );
        }

        if (report.getImageUrl() != null) {
            existingReport.setImageUrl(
                    report.getImageUrl()
            );
        }

        if (report.getReporterName() != null) {
            existingReport.setReporterName(
                    report.getReporterName()
            );
        }

        // STATUS UPDATE
        if (report.getRescueStatus() != null) {

            existingReport.setRescueStatus(
                    report.getRescueStatus()
            );
        }

        return repository.save(
                existingReport
        );
    }

    // DELETE
    public void deleteReport(Long id) {

        logger.info(
                "Deleting animal report id: {}",
                id
        );

        repository.deleteById(id);
    }
}