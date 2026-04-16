package com.qma.measurementservice.dto;

import jakarta.validation.constraints.*;

public class QuantityDTO {
    @NotNull(message = "Value is required")
    private Double value;

    @NotBlank(message = "Unit is required")
    @Size(max = 20)
    private String unit;

    public QuantityDTO() {}
    public QuantityDTO(double value, String unit) { this.value = value; this.unit = unit; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
