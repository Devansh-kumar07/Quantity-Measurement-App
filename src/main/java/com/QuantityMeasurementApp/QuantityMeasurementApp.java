package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	// UC - 1
	
	public static class Feet {
		private final double value;

		public Feet(double value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj == null || getClass() != obj.getClass())
				return false;

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
			if (this == obj)
				return true;

			if (obj == null || getClass() != obj.getClass())
				return false;

			Inches other = (Inches) obj;
			return Double.compare(this.value, other.value) == 0;
		}
	}

	// UC - 3
	public enum Unit {
		FEET(12.0), INCH(1.0);

		private final double conversionFactorToInch;

		Unit(double conversionFactorToInch) {
			this.conversionFactorToInch = conversionFactorToInch;
		}

		public double toInch(double value) {
			return value * conversionFactorToInch;
		}
	}

	// Made static to avoid inner class error
	public static class QuantityLength {

		private final double value;
		private final Unit unit;

		public QuantityLength(double value, Unit unit) {
			this.value = value;
			this.unit = unit;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj == null || getClass() != obj.getClass())
				return false;

			QuantityLength other = (QuantityLength) obj;

			double thisInInches = this.unit.toInch(this.value);
			double otherInInches = other.unit.toInch(other.value);

			return Double.compare(thisInInches, otherInInches) == 0;
		}
	}

	public static void main(String[] args) {

		// UC - 1
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(1.0);

		boolean feetResult = feet1.equals(feet2);

		System.out.println("Input: 1.0 ft and 1.0 ft");
		System.out.println("Output: Equal (" + feetResult + ")");

		// UC - 2
		Inches inch1 = new Inches(1.0);
		Inches inch2 = new Inches(1.0);

		boolean inchResult = inch1.equals(inch2);

		System.out.println("Input: 1.0 inch and 1.0 inch");
		System.out.println("Output: Equal (" + inchResult + ")");

		// UC - 3
		QuantityLength lengthFeet = new QuantityLength(1.0, Unit.FEET);
		QuantityLength lengthInch = new QuantityLength(12.0, Unit.INCH);

		boolean lengthResult = lengthFeet.equals(lengthInch);

		System.out.println("Input: 1.0 ft and 12.0 inch");
		System.out.println("Output: Equal (" + lengthResult + ")");
	}
}