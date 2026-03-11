package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-6;

	@Test
	void testConversion_FeetToInches() {
		double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET,
				QuantityMeasurementApp.LengthUnit.INCH);

		assertEquals(12.0, result, EPSILON);
	}

	@Test
	void testConversion_YardsToFeet() {
		double result = QuantityMeasurementApp.convert(3.0, QuantityMeasurementApp.LengthUnit.YARD,
				QuantityMeasurementApp.LengthUnit.FEET);

		assertEquals(9.0, result, EPSILON);
	}

	@Test
	void testConversion_CentimetersToInches() {
		double result = QuantityMeasurementApp.convert(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETER,
				QuantityMeasurementApp.LengthUnit.INCH);

		assertEquals(1.0, result, EPSILON);
	}

	@Test
	void testConversion_ZeroValue() {
		double result = QuantityMeasurementApp.convert(0.0, QuantityMeasurementApp.LengthUnit.FEET,
				QuantityMeasurementApp.LengthUnit.INCH);

		assertEquals(0.0, result, EPSILON);
	}

	@Test
	void testConversion_NegativeValue() {
		double result = QuantityMeasurementApp.convert(-1.0, QuantityMeasurementApp.LengthUnit.FEET,
				QuantityMeasurementApp.LengthUnit.INCH);

		assertEquals(-12.0, result, EPSILON);
	}

	@Test
	void testConversion_RoundTrip() {

		double original = 5.0;

		double toInches = QuantityMeasurementApp.convert(original, QuantityMeasurementApp.LengthUnit.FEET,
				QuantityMeasurementApp.LengthUnit.INCH);

		double backToFeet = QuantityMeasurementApp.convert(toInches, QuantityMeasurementApp.LengthUnit.INCH,
				QuantityMeasurementApp.LengthUnit.FEET);

		assertEquals(original, backToFeet, EPSILON);
	}

	@Test
	void testConversion_InvalidUnit() {
		assertThrows(IllegalArgumentException.class,
				() -> QuantityMeasurementApp.convert(1.0, null, QuantityMeasurementApp.LengthUnit.FEET));
	}

	@Test
	void testConversion_NaN_Throws() {
		assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.convert(Double.NaN,
				QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH));
	}
}