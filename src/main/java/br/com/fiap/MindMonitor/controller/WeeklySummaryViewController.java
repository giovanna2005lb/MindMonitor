package br.com.fiap.MindMonitor.controller;

import br.com.fiap.MindMonitor.service.ReportService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeeklySummaryViewController {

    private final ReportService reportService;

    public WeeklySummaryViewController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/weeklysummary/view")
    public String view(@AuthenticationPrincipal String username, Model model) {
        model.addAttribute("summary", reportService.calculateWeeklyForUser(username));
        return "weekly_summary";
    }
}
