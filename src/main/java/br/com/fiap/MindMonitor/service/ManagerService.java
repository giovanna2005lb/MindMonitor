package br.com.fiap.MindMonitor.service;

import br.com.fiap.MindMonitor.entity.Employee;
import br.com.fiap.MindMonitor.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    private final EmployeeRepository employeeRepository;

    public ManagerService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
