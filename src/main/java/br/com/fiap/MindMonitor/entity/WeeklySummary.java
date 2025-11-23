package br.com.fiap.MindMonitor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "weekly_summaries")
public class WeeklySummary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Employee employee;

    private LocalDate weekStart;

    private LocalDate weekEnd;

    private double avgDesmotivation;

    private double avgOverload;

    private double avgStress;

    private boolean burnoutRisk;

    @Column(length = 2000)
    private String aiFeedback;
}