package com.quantitymeasurementapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuantityDTO {

    // value - required field; frontend se koi bhi valid double aana chahiye
    @NotNull(message = "Value is required")
    private Double value;

    // unit - blank nahi hona chahiye (e.g. "FEET", "INCH", "CELSIUS")
    @NotBlank(message = "Unit is required")
    @Size(max = 20, message = "Unit must not exceed 20 characters")
    private String unit;

    public QuantityDTO() {}

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
