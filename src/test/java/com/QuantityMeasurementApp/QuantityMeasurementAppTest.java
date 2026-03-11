package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	// ---------- FEET TESTS ----------

	@Test
	void testFeet_SameValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);
		assertTrue(f1.equals(f2));
	}

	@Test
	void testFeet_DifferentValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);
		assertFalse(f1.equals(f2));
	}

	@Test
	void testFeet_NullComparison() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		assertFalse(f1.equals(null));
	}

	@Test
	void testFeet_SameReference() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		assertTrue(f1.equals(f1));
	}

	// ---------- INCH TESTS ----------

	@Test
	void testInch_SameValue() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(1.0);
		assertTrue(i1.equals(i2));
	}

	@Test
	void testInch_DifferentValue() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(2.0);
		assertFalse(i1.equals(i2));
	}

	@Test
	void testInch_NullComparison() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		assertFalse(i1.equals(null));
	}

	@Test
	void testInch_SameReference() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		assertTrue(i1.equals(i1));
	}

	// ---------- UC3 & UC4 QuantityLength TESTS ----------

	@Test
	void testQuantity_SameUnitSameValue() {
		QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		assertTrue(q1.equals(q2));
	}

	@Test
	void testQuantity_FeetToInch() {
		assertTrue(new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.Unit.FEET)
				.equals(new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.Unit.INCH)));
	}

	@Test
	void testQuantity_YardToFeet() {
		assertTrue(new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.Unit.YARDS)
				.equals(new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.Unit.FEET)));
	}

	@Test
	void testQuantity_YardToInch() {
		assertTrue(new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.Unit.YARDS)
				.equals(new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.Unit.INCH)));
	}

	@Test
	void testQuantity_CentimeterToInch() {
		assertTrue(new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.Unit.CENTIMETERS)
				.equals(new QuantityMeasurementApp.QuantityLength(0.393701, QuantityMeasurementApp.Unit.INCH)));
	}

	@Test
	void testQuantity_TransitiveProperty() {

		QuantityMeasurementApp.QuantityLength yard = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.YARDS);

		QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(3.0,
				QuantityMeasurementApp.Unit.FEET);

		QuantityMeasurementApp.QuantityLength inch = new QuantityMeasurementApp.QuantityLength(36.0,
				QuantityMeasurementApp.Unit.INCH);

		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inch));
		assertTrue(yard.equals(inch));
	}

	@Test
	void testQuantity_NullComparison() {
		QuantityMeasurementApp.QuantityLength q = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		assertFalse(q.equals(null));
	}

	@Test
	void testQuantity_SameReference() {
		QuantityMeasurementApp.QuantityLength q = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		assertTrue(q.equals(q));
	}
}