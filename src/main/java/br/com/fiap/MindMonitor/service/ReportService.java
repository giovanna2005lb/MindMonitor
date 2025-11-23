package br.com.fiap.MindMonitor.service;

import br.com.fiap.MindMonitor.entity.DailyReport;
import br.com.fiap.MindMonitor.entity.Employee;
import br.com.fiap.MindMonitor.entity.User;
import br.com.fiap.MindMonitor.entity.WeeklySummary;
import br.com.fiap.MindMonitor.repository.DailyReportRepository;
import br.com.fiap.MindMonitor.repository.UserRepository;
import br.com.fiap.MindMonitor.repository.WeeklySummaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {
    private final DailyReportRepository dailyReportRepository;
    private final UserRepository userRepository;
    private final WeeklySummaryRepository weeklySummaryRepository;
    public ReportService(DailyReportRepository dailyReportRepository,
                         UserRepository userRepository, WeeklySummaryRepository
                                 weeklySummaryRepository) {
        this.dailyReportRepository = dailyReportRepository;
        this.userRepository = userRepository;
        this.weeklySummaryRepository = weeklySummaryRepository;
    }
    @Transactional
    public void saveDaily(String username, DailyReport dto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!(user instanceof Employee)) throw new RuntimeException("Only employees can submit reports");
        Employee emp = (Employee) user;
        LocalDate date = dto.getDate();
        List<DailyReport> existing =
                dailyReportRepository.findAllByEmployeeIdAndDateBetween(emp.getId(), date,
                        date);
        DailyReport dr;
        if (existing.isEmpty()) {
            dr = new DailyReport();
            dr.setEmployee(emp);
            dr.setDate(date);
        } else {
            dr = existing.get(0);
        }
        dr.setDesmotivation(dto.getDesmotivation());
        dr.setOverload(dto.getOverload());
        dr.setStress(dto.getStress());
        dr.setComment(dto.getComment());
        dailyReportRepository.save(dr);
    }
    public WeeklySummary calculateWeeklyForEmployee(Long employeeId,
                                                    LocalDate weekStart, LocalDate weekEnd) {
        List<DailyReport> list =
                dailyReportRepository.findAllByEmployeeIdAndDateBetween(employeeId,
                        weekStart, weekEnd);
        double avgDes =
                list.stream().mapToInt(DailyReport::getDesmotivation).average().orElse(0);
        double avgOv =
                list.stream().mapToInt(DailyReport::getOverload).average().orElse(0);
        double avgSt =
                list.stream().mapToInt(DailyReport::getStress).average().orElse(0);
        WeeklySummary s = new WeeklySummary();
        Employee e = new Employee(); e.setId(employeeId); s.setEmployee(e);
        s.setWeekStart(weekStart);
        s.setWeekEnd(weekEnd);
        s.setAvgDesmotivation(avgDes);
        s.setAvgOverload(avgOv);
        s.setAvgStress(avgSt);
        s.setBurnoutRisk(avgSt >= 7 || avgDes >= 8);
        s.setAiFeedback(null);
        return weeklySummaryRepository.save(s);
    }
    public List<DailyReport> getRecentReports(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!(user instanceof Employee)) {
            throw new RuntimeException("Only employees have daily reports");
        }

        Employee emp = (Employee) user;

        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(7); // últimos 7 dias

        return dailyReportRepository.findAllByEmployeeIdAndDateBetween(
                emp.getId(),
                weekAgo,
                today
        );
    }
    public WeeklySummary calculateWeeklyForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!(user instanceof Employee)) {
            throw new RuntimeException("Only employees have weekly summaries");
        }

        Employee emp = (Employee) user;

        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusDays(today.getDayOfWeek().getValue() - 1); // segunda
        LocalDate weekEnd = weekStart.plusDays(6); // domingo

        return calculateWeeklyForEmployee(emp.getId(), weekStart, weekEnd);
    }

}