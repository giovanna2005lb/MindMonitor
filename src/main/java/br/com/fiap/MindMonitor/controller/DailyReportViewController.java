package br.com.fiap.MindMonitor.controller;

import br.com.fiap.MindMonitor.entity.DailyReport;
import br.com.fiap.MindMonitor.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DailyReportViewController {

    @GetMapping("/dailyreport/new")
    public String newReport(Model model) {
        model.addAttribute("report", new DailyReport());
        return "daily_report_form";
    }
}
