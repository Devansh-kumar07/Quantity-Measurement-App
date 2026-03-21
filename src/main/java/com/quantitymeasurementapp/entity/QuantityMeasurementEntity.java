package com.quantitymeasurementapp.entity;

public class QuantityMeasurementEntity {

    private String operation;
    private double value;
    private String unit;

    public QuantityMeasurementEntity(
            String operation,
            double value,
            String unit) {

        this.operation = operation;
        this.value = value;
        this.unit = unit;
    }

    public String getOperation() { return operation; }
    public double getValue() { return value; }
    public String getUnit() { return unit; }
}