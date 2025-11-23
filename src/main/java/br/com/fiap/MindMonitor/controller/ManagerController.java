package br.com.fiap.MindMonitor.controller;

import br.com.fiap.MindMonitor.entity.WeeklySummary;
import br.com.fiap.MindMonitor.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
    private final ReportService reportService;
    public ManagerController(ReportService reportService) {
        this.reportService = reportService; }
    @GetMapping("/employee/{id}/weekly-summary")
    public WeeklySummary getWeekly(@PathVariable Long id,
                                   @RequestParam @DateTimeFormat(iso =
                                           DateTimeFormat.ISO.DATE) LocalDate start,
                                   @RequestParam @DateTimeFormat(iso =
                                           DateTimeFormat.ISO.DATE) LocalDate end) {
        return reportService.calculateWeeklyForEmployee(id, start, end);
    }
}
