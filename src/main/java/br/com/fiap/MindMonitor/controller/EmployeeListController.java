package br.com.fiap.MindMonitor.controller;

import br.com.fiap.MindMonitor.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeListController {

    private final ManagerService managerService;

    public EmployeeListController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", managerService.findAllEmployees());
        return "list_employees";
    }
}
