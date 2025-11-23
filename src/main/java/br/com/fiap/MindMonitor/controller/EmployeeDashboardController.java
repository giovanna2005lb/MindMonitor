package br.com.fiap.MindMonitor.controller;

import br.com.fiap.MindMonitor.service.ReportService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeDashboardController {

    private final ReportService reportService;

    public EmployeeDashboardController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/employee/dashboard")
    public String dashboard(@AuthenticationPrincipal String username, Model model) {
        model.addAttribute("reports", reportService.getRecentReports(username));
        return "dashboard_employee";
    }
}
