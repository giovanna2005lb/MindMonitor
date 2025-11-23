package br.com.fiap.MindMonitor.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "daily_reports", uniqueConstraints =
@UniqueConstraint(columnNames = {"employee_id","date"}))
public class DailyReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate date;

    @NotNull(message = "{dailyreport.emotions.notnull}")
    @Min(1) @Max(10)
    private int desmotivation;

    @NotNull(message = "{dailyreport.emotions.notnull}")
    @Min(1) @Max(10)
    private int overload;

    @NotNull(message = "{dailyreport.emotions.notnull}")
    @Min(1) @Max(10)
    private int stress;

    @Size(min=10,max = 255, message = "{dailyreport.comment.size}")
    private String comment;
}
