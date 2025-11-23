package br.com.fiap.MindMonitor.controller;

import br.com.fiap.MindMonitor.entity.DailyReport;
import br.com.fiap.MindMonitor.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reports")
public class DailyReportController {
    private final ReportService reportService;
    public DailyReportController(ReportService reportService) {
        this.reportService = reportService; }
    @PostMapping("/daily")
    public ResponseEntity<Void> submitDaily(@Valid @RequestBody
                                            DailyReport dto, @AuthenticationPrincipal String username) {
        reportService.saveDaily(username, dto);
        return ResponseEntity.status(201).build();
    }
}