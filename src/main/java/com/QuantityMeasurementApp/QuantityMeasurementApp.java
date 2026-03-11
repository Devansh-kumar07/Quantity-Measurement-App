package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	// ENUM: LengthUnit
	enum LengthUnit {

		FEET(1.0), INCH(1.0 / 12), YARD(3.0), CENTIMETER(0.393701 / 12);

		private final double conversionFactor; // relative to FEET

		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}

		public double getConversionFactor() {
			return conversionFactor;
		}
	}

	// VALUE OBJECT: QuantityLength
	public static class QuantityLength {

		private final double value;
		private final LengthUnit unit;

		private static final double EPSILON = 1e-6;

		public QuantityLength(double value, LengthUnit unit) {

			if (!Double.isFinite(value)) {
				throw new IllegalArgumentException("Value must be finite.");
			}

			if (unit == null) {
				throw new IllegalArgumentException("Unit cannot be null.");
			}

			this.value = value;
			this.unit = unit;
		}

		private double toBaseUnit() {
			return value * unit.getConversionFactor();
		}

		public QuantityLength convertTo(LengthUnit targetUnit) {

			if (targetUnit == null) {
				throw new IllegalArgumentException("Target unit cannot be null.");
			}

			double baseValue = this.toBaseUnit();
			double convertedValue = baseValue / targetUnit.getConversionFactor();

			return new QuantityLength(convertedValue, targetUnit);
		}

		@Override
		public boolean equals(Object obj) {

			if (this == obj)
				return true;

			if (obj == null || getClass() != obj.getClass())
				return false;

			QuantityLength other = (QuantityLength) obj;

			return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
		}

		@Override
		public String toString() {
			return value + " " + unit;
		}
	}

	// STATIC API METHOD
	public static double convert(double value, LengthUnit source, LengthUnit target) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}

		if (source == null || target == null) {
			throw new IllegalArgumentException("Units cannot be null.");
		}

		double baseValue = value * source.getConversionFactor();
		return baseValue / target.getConversionFactor();
	}

	// OVERLOADED DEMONSTRATION METHODS

	public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {

		double result = convert(value, from, to);
		System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
	}

	public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit toUnit) {

		QuantityLength converted = quantity.convertTo(toUnit);
		System.out.println(quantity + " → " + converted);
	}

	public static void demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {

		System.out.println(q1 + " == " + q2 + " ? " + q1.equals(q2));
	}

	// MAIN METHOD
	public static void main(String[] args) {

		demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
		demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
		demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
		demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

		QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
		QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

		demonstrateLengthEquality(yard, feet);
	}
}