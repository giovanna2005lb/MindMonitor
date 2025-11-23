package br.com.fiap.MindMonitor.repository;

import br.com.fiap.MindMonitor.entity.WeeklySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface WeeklySummaryRepository extends JpaRepository<WeeklySummary, Long> {
    List<WeeklySummary> findAllByEmployeeIdOrderByWeekStartDesc(Long employeeId);
}
