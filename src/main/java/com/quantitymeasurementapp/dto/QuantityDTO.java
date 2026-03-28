package com.quantitymeasurementapp.dto;

public class QuantityDTO {

    private double value;
    private String unit;

    // No-arg constructor needed for JSON deserialization
    public QuantityDTO() {}

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
