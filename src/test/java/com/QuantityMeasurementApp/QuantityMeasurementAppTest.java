package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	// FEET TEST CASES
	
	@Test
	void testFeetEquality_SameValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

		assertTrue(f1.equals(f2));
	}

	@Test
	void testFeetEquality_DifferentValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

		assertFalse(f1.equals(f2));
	}

	@Test
	void testFeetEquality_NullComparison() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		assertFalse(f1.equals(null));
	}

	@Test
	void testFeetEquality_NonNumericInput() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		String nonNumeric = "1.0";

		assertFalse(f1.equals(nonNumeric));
	}

	@Test
	void testFeetEquality_SameReference() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		assertTrue(f1.equals(f1));
	}

	// INCH TEST CASES

	@Test
	void testInchEquality_SameValue() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(1.0);

		assertTrue(i1.equals(i2));
	}

	@Test
	void testInchEquality_DifferentValue() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(2.0);

		assertFalse(i1.equals(i2));
	}

	@Test
	void testInchEquality_NullComparison() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		assertFalse(i1.equals(null));
	}

	@Test
	void testInchEquality_NonNumericInput() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		String nonNumeric = "1.0";

		assertFalse(i1.equals(nonNumeric));
	}

	@Test
	void testInchEquality_SameReference() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
		assertTrue(i1.equals(i1));
	}

	// UC - 3 QuantityLength TEST CASES

	@Test
	void testQuantityLength_SameUnitSameValue() {
		QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		assertTrue(q1.equals(q2));
	}

	@Test
	void testQuantityLength_SameUnitDifferentValue() {
		QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.INCH);

		QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(2.0,
				QuantityMeasurementApp.Unit.INCH);

		assertFalse(q1.equals(q2));
	}

	@Test
	void testQuantityLength_FeetToInch() {
		QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		QuantityMeasurementApp.QuantityLength inch = new QuantityMeasurementApp.QuantityLength(12.0,
				QuantityMeasurementApp.Unit.INCH);

		assertTrue(feet.equals(inch));
	}

	@Test
	void testQuantityLength_NullComparison() {
		QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		assertFalse(q1.equals(null));
	}

	@Test
	void testQuantityLength_SameReference() {
		QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0,
				QuantityMeasurementApp.Unit.FEET);

		assertTrue(q1.equals(q1));
	}
}