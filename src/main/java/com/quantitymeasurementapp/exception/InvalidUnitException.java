package com.quantitymeasurementapp.exception;

/**
 * Thrown when an unsupported or invalid measurement unit is provided.
 * E.g., user sends unit "PARSEC" which is not in our supported enums.
 * Maps to HTTP 400 Bad Request.
 */
public class InvalidUnitException extends RuntimeException {

    private final String unit;

    public InvalidUnitException(String unit) {
        super("Invalid or unsupported measurement unit: '" + unit + "'");
        this.unit = unit;
    }

    public String getUnit() { return unit; }
}
