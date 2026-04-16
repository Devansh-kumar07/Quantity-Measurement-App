package com.qma.historyservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "quantity_measurements")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;   // ← YE NAYA ADD KARO

    @NotBlank
    @Pattern(regexp = "^(add|subtract|compare|convert|divide)$")
    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private double value;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String unit;

    public HistoryEntity() {}

    public HistoryEntity(String username, String operation, double value, String unit) {
        this.username  = username;    // ← CONSTRUCTOR MEIN BHI ADD KARO
        this.operation = operation;
        this.value     = value;
        this.unit      = unit;
    }

    public Long getId()           { return id; }
    public String getUsername()   { return username; }   // ← GETTER
    public String getOperation()  { return operation; }
    public double getValue()      { return value; }
    public String getUnit()       { return unit; }
}