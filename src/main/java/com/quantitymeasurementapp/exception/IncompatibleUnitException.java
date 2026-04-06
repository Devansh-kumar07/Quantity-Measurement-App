package com.quantitymeasurementapp.exception;

/**
 * Thrown when two units of different types are used together.
 * E.g., trying to add FEET + CELSIUS (length + temperature) is not allowed.
 * Maps to HTTP 422 Unprocessable Entity.
 */
public class IncompatibleUnitException extends RuntimeException {

    private final String unitA;
    private final String unitB;

    public IncompatibleUnitException(String unitA, String unitB) {
        super(String.format(
            "Units '%s' and '%s' are incompatible - they belong to different measurement types", unitA, unitB
        ));
        this.unitA = unitA;
        this.unitB = unitB;
    }

    public String getUnitA() { return unitA; }
    public String getUnitB() { return unitB; }
}
