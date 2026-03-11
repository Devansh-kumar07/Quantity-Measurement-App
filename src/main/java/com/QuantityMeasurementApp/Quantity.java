package com.QuantityMeasurementApp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    public final double value;
    private final U unit;

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

    public Quantity<U> add(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = unit.convertFromBaseUnit(sum);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Quantity<>(result, targetUnit);
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

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = this.unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }
    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
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
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getUnit() {
		// TODO Auto-generated method stub
		return null;
	}
}