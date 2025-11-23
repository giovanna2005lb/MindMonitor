package br.com.fiap.MindMonitor.repository;

import br.com.fiap.MindMonitor.entity.DailyReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

    Page<DailyReport> findAllByEmployeeId(Long employeeId, Pageable pageable);
    List<DailyReport> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate start, LocalDate end);

}
