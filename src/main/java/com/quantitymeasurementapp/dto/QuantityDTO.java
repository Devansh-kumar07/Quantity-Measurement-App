package com.quantitymeasurementapp.dto;

public class QuantityDTO {

    public double value;
    public String unit;

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}