package com.quantitymeasurementapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // operation - "add", "subtract", "compare", "convert", "divide"
    @NotBlank(message = "Operation is required")
    @Pattern(regexp = "^(add|subtract|compare|convert|divide)$",
             message = "Operation must be one of: add, subtract, compare, convert, divide")
    @Column(nullable = false)
    private String operation;

    // value - finite number hona chahiye, NaN ya Infinity nahi
    @Column(nullable = false)
    private double value;

    // unit - blank nahi hona chahiye, max 20 chars
    @NotBlank(message = "Unit is required")
    @Size(max = 20, message = "Unit must not exceed 20 characters")
    @Column(nullable = false)
    private String unit;

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(String operation, double value, String unit) {
        this.operation = operation;
        this.value = value;
        this.unit = unit;
    }

    public Long getId() { return id; }
    public String getOperation() { return operation; }
    public double getValue() { return value; }
    public String getUnit() { return unit; }
}
