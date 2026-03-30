package com.quantitymeasurementapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private double amount;
    private String unit;

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(String operation, double amount, String unit) {
        this.operation = operation;
        this.amount = amount;
        this.unit = unit;
    }

    public Long getId() { return id; }
    public String getOperation() { return operation; }
    public double getAmount() { return amount; }
    public String getUnit() { return unit; }
}
