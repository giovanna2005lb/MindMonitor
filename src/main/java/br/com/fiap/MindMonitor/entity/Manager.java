package br.com.fiap.MindMonitor.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "managers")
public class Manager extends User {
    @OneToMany(mappedBy = "manager")

    private List<Employee> employees;
}
