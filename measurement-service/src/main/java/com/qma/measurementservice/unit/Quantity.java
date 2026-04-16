package com.qma.measurementservice.unit;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    public final double value;
    private final U unit;

    private enum ArithmeticOperation {
        ADD { double compute(double a, double b) { return a + b; } },
        SUBTRACT { double compute(double a, double b) { return a - b; } },
        DIVIDE {
            double compute(double a, double b) {
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };
        abstract double compute(double a, double b);
    }

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid numeric value");
        this.value = value;
        this.unit = unit;
    }

    private void validateArithmeticOperands(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Quantity cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        validateArithmeticOperands(other);
        this.unit.validateOperationSupport(op.name());
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return op.compute(base1, base2);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double base = unit.convertToBaseUnit(value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(base), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        double result = this.unit.convertFromBaseUnit(performBaseArithmetic(other, ArithmeticOperation.ADD));
        return new Quantity<>(result, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        double result = this.unit.convertFromBaseUnit(performBaseArithmetic(other, ArithmeticOperation.SUBTRACT));
        return new Quantity<>(result, this.unit);
    }

    public double divide(Quantity<U> other) {
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
        if (unit.getClass() != other.unit.getClass()) return false;
        return Math.abs(unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value)) < 1e-6;
    }

    @Override public int hashCode() { return Objects.hash(unit.convertToBaseUnit(value), unit.getClass()); }
    public double getValue() { return value; }
    public U getUnit() { return unit; }
    @Override public String toString() { return "Quantity(" + value + ", " + unit.getUnitName() + ")"; }
}
