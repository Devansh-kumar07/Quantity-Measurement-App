package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {
	public static class Feet {
		private final double value;

		public Feet(double value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {

			if (this == obj) {
				return true;
			}

			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}

			Feet other = (Feet) obj;

			return Double.compare(this.value, other.value) == 0;
		}
	}

	// UC - 2
	public static class Inches {

		private final double value;

		public Inches(double value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {

			if (this == obj) {
				return true;
			}

			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}

			Inches other = (Inches) obj;
			return Double.compare(this.value, other.value) == 0;
		}
	}

	// UC - 3
	public enum Unit {

		FEET(12.0), INCH(1.0), YARDS(36.0), CENTIMETERS(0.393701);

		private final double conversionFactorToInch;

		Unit(double conversionFactorToInch) {
			this.conversionFactorToInch = conversionFactorToInch;
		}

		public double toInch(double value) {
			return value * conversionFactorToInch;
		}
	}

	public static class QuantityLength {

		private final double value;
		private final Unit unit;

		public QuantityLength(double value, Unit unit) {
			this.value = value;
			this.unit = unit;
		}

		@Override
		public boolean equals(Object obj) {

			if (this == obj) {
				return true;
			}

			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}

			QuantityLength other = (QuantityLength) obj;

			double thisInInches = this.unit.toInch(this.value);
			double otherInInches = other.unit.toInch(other.value);

			return Double.compare(thisInInches, otherInInches) == 0;
		}
	}

	public static void main(String[] args) {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(1.0);

		boolean result = feet1.equals(feet2);

		System.out.println("Input: 1.0 ft and 1.0 ft");
		System.out.println("Output: Equal (" + result + ")");

		// UC - 2
		Inches inch1 = new Inches(1.0);
		Inches inch2 = new Inches(1.0);

		boolean inchResult = inch1.equals(inch2);

		System.out.println("Input: 1.0 inch and 1.0 inch");
		System.out.println("Output: Equal (" + inchResult + ")");

		// UC - 3
		QuantityLength feet = new QuantityLength(1.0, Unit.FEET);
		QuantityLength inch = new QuantityLength(12.0, Unit.INCH);

		boolean resultUC3 = feet.equals(inch);

		System.out.println("Input: 1.0 ft and 12.0 inch");
		System.out.println("Output: Equal (" + resultUC3 + ")");

		// UC - 4
		System.out.println("UC4 → " + new QuantityLength(1.0, Unit.YARDS).equals(new QuantityLength(3.0, Unit.FEET)));

		System.out.println("UC4 → " + new QuantityLength(1.0, Unit.YARDS).equals(new QuantityLength(36.0, Unit.INCH)));

		System.out.println(
				"UC4 → " + new QuantityLength(1.0, Unit.CENTIMETERS).equals(new QuantityLength(0.393701, Unit.INCH)));
	}
}