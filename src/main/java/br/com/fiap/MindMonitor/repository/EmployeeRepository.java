package br.com.fiap.MindMonitor.repository;

import br.com.fiap.MindMonitor.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
