package com.animalrescue.smartrescue;

public class ReportService {
    private ReportRepository reportRepository;

    public ReportService()
    {
        reportRepository = new ReportRepository();
    }

    public void printReport()
    {
        System.out.println(reportRepository.getAllReports());
    }
}
