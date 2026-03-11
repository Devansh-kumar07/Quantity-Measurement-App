package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.QuantityMeasurementApp.QuantityMeasurementApp.Inches;
import com.QuantityMeasurementApp.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    // ---------------- UC1 FEET TESTS ----------------

    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        String nonNumeric = "1.0";

        assertFalse(f1.equals(nonNumeric));
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1));
    }


    // ---------------- UC2 INCH TESTS ----------------

    @Test
    void testInchEquality_SameValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);

        assertTrue(i1.equals(i2));
    }

    @Test
    void testInchEquality_DifferentValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(2.0);

        assertFalse(i1.equals(i2));
    }

    @Test
    void testInchEquality_NullComparison() {
        Inches i1 = new Inches(1.0);

        assertFalse(i1.equals(null));
    }

    @Test
    void testInchEquality_NonNumericInput() {
        Inches i1 = new Inches(1.0);
        String nonNumeric = "1.0";

        assertFalse(i1.equals(nonNumeric));
    }

    @Test
    void testInchEquality_SameReference() {
        Inches i1 = new Inches(1.0);

        assertTrue(i1.equals(i1));
    }


    // ---------------- UC3 ----------------

    @Test
    void testEquality_SameUnitSameValue() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_SameUnitDifferentValue() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.INCH);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToInch() {

        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inch));
    }


    // ---------------- UC4 ----------------

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        assertTrue(
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(3.0, LengthUnit.FEET))
        );
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        assertTrue(
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(36.0, LengthUnit.INCH))
        );
    }


    // ---------------- UC5 CONVERSION ----------------

    @Test
    void testConversion_FeetToInches() {

        double result = QuantityLength.convert(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCH
        );

        assertEquals(12.0, result);
    }

    @Test
    void testConversion_InchesToFeet() {

        double result = QuantityLength.convert(
                24.0,
                LengthUnit.INCH,
                LengthUnit.FEET
        );

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_YardsToInches() {

        double result = QuantityLength.convert(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCH
        );

        assertEquals(36.0, result);
    }


    // ---------------- UC6 ADDITION ----------------

    @Test
    void testAddition_FeetPlusInches() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inch =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = feet.add(inch);

        assertEquals(
                2.0,
                result.convertTo(LengthUnit.FEET).value,
                1e-6
        );
    }


    // ---------------- UC7 ADDITION TARGET UNIT ----------------

    @Test
    void testAddition_TargetUnit_Feet() {

        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertTrue(result.equals(
                new QuantityLength(2.0, LengthUnit.FEET)
        ));
    }


    // ---------------- UC8 ENUM TEST ----------------

    @Test
    void testLengthUnitEnum_FeetConstant() {

        assertEquals(
                1.0,
                LengthUnit.FEET.convertToBaseUnit(1.0)
        );
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {

        double result =
                LengthUnit.INCH.convertToBaseUnit(12.0);

        assertEquals(1.0, result, 1e-6);
    }

}