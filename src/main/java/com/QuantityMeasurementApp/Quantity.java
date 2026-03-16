package com.QuantityMeasurementApp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    public final double value;
    private final U unit;
    private enum ArithmeticOperation {

        ADD {
            double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            double compute(double a, double b) {
                if (b == 0)
                    throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }
    private void validateArithmeticOperands(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");
    }
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        validateArithmeticOperands(other);

        // UC14 check
        this.unit.validateOperationSupport(operation.name());

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(result, targetUnit);
    }



    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, targetUnit);
    }
    public Quantity<U> add(Quantity<U> other) {

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result =
                this.unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, this.unit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 1e-6;
    }

    public Quantity<U> subtract(Quantity<U> other) {

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result =
                this.unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, targetUnit);
    }
    public double divide(Quantity<U> other) {

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value), unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
}