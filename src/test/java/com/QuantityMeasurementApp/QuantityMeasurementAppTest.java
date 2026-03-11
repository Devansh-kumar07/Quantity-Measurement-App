package com.QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.QuantityMeasurementApp.QuantityMeasurementApp.Inches;
import com.QuantityMeasurementApp.QuantityMeasurementApp.QuantityLength;
import com.QuantityMeasurementApp.QuantityMeasurementApp.Unit;

public class QuantityMeasurementAppTest {
	@Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    void testEquality_NullComparison1() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals(null), "Feet object should not be equal to null");
    }

    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        String nonNumeric = "1.0";

        assertFalse(f1.equals(nonNumeric), "Feet should not be equal to non-numeric object");
    }

    @Test
    void testEquality_SameReference1() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1), "Object must be equal to itself");
    }
    
    
    
    
    //  INCH TEST CASES

    @Test
    void testInchEquality_SameValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);

        assertTrue(i1.equals(i2), "1.0 inch should be equal to 1.0 inch");
    }

    @Test
    void testInchEquality_DifferentValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(2.0);

        assertFalse(i1.equals(i2), "1.0 inch should not be equal to 2.0 inch");
    }

    @Test
    void testInchEquality_NullComparison() {
        Inches i1 = new Inches(1.0);

        assertFalse(i1.equals(null), "Inches object should not be equal to null");
    }

    @Test
    void testInchEquality_NonNumericInput() {
        Inches i1 = new Inches(1.0);
        String nonNumeric = "1.0";

        assertFalse(i1.equals(nonNumeric), "Inches should not be equal to non-numeric object");
    }

    @Test
    void testInchEquality_SameReference() {
        Inches i1 = new Inches(1.0);

        assertTrue(i1.equals(i1), "Object must be equal to itself");
    }
    
    // UC - 3 
    @Test
    void testEquality_SameUnitSameValue() {
        QuantityLength q1 = new QuantityLength(1.0, Unit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, Unit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_SameUnitDifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, Unit.INCH);
        QuantityLength q2 = new QuantityLength(2.0, Unit.INCH);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToInch() {
        QuantityLength feet = new QuantityLength(1.0, Unit.FEET);
        QuantityLength inch = new QuantityLength(12.0, Unit.INCH);

        assertTrue(feet.equals(inch));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q1 = new QuantityLength(1.0, Unit.FEET);
        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q1 = new QuantityLength(1.0, Unit.FEET);
        assertTrue(q1.equals(q1));
    }
    
    
    
    //UC - 4 
    
    @Test
    void testEquality_YardToYard_SameValue() {
        QuantityMeasurementApp.QuantityLength y1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS);

        QuantityMeasurementApp.QuantityLength y2 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS);

        assertTrue(y1.equals(y2),
                "1.0 yard should be equal to 1.0 yard");
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        QuantityMeasurementApp.QuantityLength y1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS);

        QuantityMeasurementApp.QuantityLength y2 =
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.Unit.YARDS);

        assertFalse(y1.equals(y2),
                "1.0 yard should not be equal to 2.0 yard");
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS)
                        .equals(
                                new QuantityMeasurementApp.QuantityLength(3.0,
                                        QuantityMeasurementApp.Unit.FEET)
                        ),
                "1.0 yard should be equal to 3.0 feet"
        );
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(
                new QuantityMeasurementApp.QuantityLength(3.0,
                        QuantityMeasurementApp.Unit.FEET)
                        .equals(
                                new QuantityMeasurementApp.QuantityLength(1.0,
                                        QuantityMeasurementApp.Unit.YARDS)
                        ),
                "3.0 feet should be equal to 1.0 yard"
        );
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS)
                        .equals(
                                new QuantityMeasurementApp.QuantityLength(36.0,
                                        QuantityMeasurementApp.Unit.INCH)
                        ),
                "1.0 yard should be equal to 36.0 inches"
        );
    }

    // ---------- UC4 : CENTIMETERS TESTS ----------

    @Test
    void testEquality_CentimeterToCentimeter_SameValue() {
        QuantityMeasurementApp.QuantityLength c1 =
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.Unit.CENTIMETERS);

        QuantityMeasurementApp.QuantityLength c2 =
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.Unit.CENTIMETERS);

        assertTrue(c1.equals(c2),
                "2.0 cm should be equal to 2.0 cm");
    }

    @Test
    void testEquality_CentimeterToInches_EquivalentValue() {
        assertTrue(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.CENTIMETERS)
                        .equals(
                                new QuantityMeasurementApp.QuantityLength(0.393701,
                                        QuantityMeasurementApp.Unit.INCH)
                        ),
                "1.0 cm should be equal to 0.393701 inch"
        );
    }

    @Test
    void testEquality_CentimeterToFeet_NonEquivalentValue() {
        assertFalse(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.CENTIMETERS)
                        .equals(
                                new QuantityMeasurementApp.QuantityLength(1.0,
                                        QuantityMeasurementApp.Unit.FEET)
                        ),
                "1.0 cm should not be equal to 1.0 feet"
        );
    }

    // ---------- UC4 : TRANSITIVE PROPERTY ----------

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS);

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(3.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength inch =
                new QuantityMeasurementApp.QuantityLength(36.0,
                        QuantityMeasurementApp.Unit.INCH);

        assertTrue(yard.equals(feet),
                "1 yard should equal 3 feet");

        assertTrue(feet.equals(inch),
                "3 feet should equal 36 inches");

        assertTrue(yard.equals(inch),
                "Transitive property failed: yard → inch");
    }

    // ---------- NULL & REFLEXIVE TESTS ----------

    @Test
    void testEquality_YardSameReference() {
        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS);

        assertTrue(yard.equals(yard),
                "Object must be equal to itself");
    }

    @Test
    void testEquality_YardNullComparison() {
        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.YARDS);

        assertFalse(yard.equals(null),
                "QuantityLength should not be equal to null");
    }
    
    
 // UC5 : CONVERSION TESTS 

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                1.0,
                QuantityMeasurementApp.Unit.FEET,
                QuantityMeasurementApp.Unit.INCH
        );

        assertEquals(12.0, result);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                24.0,
                QuantityMeasurementApp.Unit.INCH,
                QuantityMeasurementApp.Unit.FEET
        );

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                1.0,
                QuantityMeasurementApp.Unit.YARDS,
                QuantityMeasurementApp.Unit.INCH
        );

        assertEquals(36.0, result);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                72.0,
                QuantityMeasurementApp.Unit.INCH,
                QuantityMeasurementApp.Unit.YARDS
        );

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                2.54,
                QuantityMeasurementApp.Unit.CENTIMETERS,
                QuantityMeasurementApp.Unit.INCH
        );

        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                6.0,
                QuantityMeasurementApp.Unit.FEET,
                QuantityMeasurementApp.Unit.YARDS
        );

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                0.0,
                QuantityMeasurementApp.Unit.FEET,
                QuantityMeasurementApp.Unit.INCH
        );

        assertEquals(0.0, result);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                -1.0,
                QuantityMeasurementApp.Unit.FEET,
                QuantityMeasurementApp.Unit.INCH
        );

        assertEquals(-12.0, result);
    }

    @Test
    void testConversion_SameUnit() {
        double result = QuantityMeasurementApp.QuantityLength.convert(
                5.0,
                QuantityMeasurementApp.Unit.FEET,
                QuantityMeasurementApp.Unit.FEET
        );

        assertEquals(5.0, result);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {

        double original = 10.0;

        double converted =
                QuantityMeasurementApp.QuantityLength.convert(
                        original,
                        QuantityMeasurementApp.Unit.FEET,
                        QuantityMeasurementApp.Unit.INCH
                );

        double roundTrip =
                QuantityMeasurementApp.QuantityLength.convert(
                        converted,
                        QuantityMeasurementApp.Unit.INCH,
                        QuantityMeasurementApp.Unit.FEET
                );

        assertEquals(original, roundTrip, 1e-6);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.QuantityLength.convert(
                    1.0,
                    null,
                    QuantityMeasurementApp.Unit.FEET
            );
        });
    }

    @Test
    void testConversion_NaNValue_Throws() {

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.QuantityLength.convert(
                    Double.NaN,
                    QuantityMeasurementApp.Unit.FEET,
                    QuantityMeasurementApp.Unit.INCH
            );
        });
    }

 // ---------------- UC6 ADDITION TESTS ----------------

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength result = a.add(b);

        assertEquals(3.0, result.convertTo(QuantityMeasurementApp.Unit.FEET).value, 1e-6);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(6.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(6.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result = a.add(b);

        assertEquals(12.0,
                result.convertTo(QuantityMeasurementApp.Unit.INCH).value,
                1e-6);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength inch =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result = feet.add(inch);

        assertEquals(2.0,
                result.convertTo(QuantityMeasurementApp.Unit.FEET).value,
                1e-6);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        QuantityMeasurementApp.QuantityLength inch =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength result = inch.add(feet);

        assertEquals(24.0,
                result.convertTo(QuantityMeasurementApp.Unit.INCH).value,
                1e-6);
    }

    @Test
    void testAddition_WithZero() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength zero =
                new QuantityMeasurementApp.QuantityLength(0.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result = a.add(zero);

        assertEquals(5.0,
                result.convertTo(QuantityMeasurementApp.Unit.FEET).value,
                1e-6);
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(-2.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength result = a.add(b);

        assertEquals(3.0,
                result.convertTo(QuantityMeasurementApp.Unit.FEET).value,
                1e-6);
    }

    @Test
    void testAddition_Commutativity() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    @Test
    void testAddition_NullSecondOperand() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> a.add(null));
    }
    
 // ---------------- UC7 ADDITION WITH TARGET UNIT ----------------

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(a, b,
                        QuantityMeasurementApp.Unit.FEET);

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.Unit.FEET)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(a, b,
                        QuantityMeasurementApp.Unit.INCH);

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(24.0,
                        QuantityMeasurementApp.Unit.INCH)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(a, b,
                        QuantityMeasurementApp.Unit.YARDS);

        assertEquals(0.666666, result.convertTo(
                QuantityMeasurementApp.Unit.YARDS).value, 1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(a, b,
                        QuantityMeasurementApp.Unit.CENTIMETERS);

        assertEquals(5.08,
                result.convertTo(
                        QuantityMeasurementApp.Unit.CENTIMETERS).value,
                1e-2);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength r1 =
                QuantityMeasurementApp.QuantityLength.add(a, b,
                        QuantityMeasurementApp.Unit.YARDS);

        QuantityMeasurementApp.QuantityLength r2 =
                QuantityMeasurementApp.QuantityLength.add(b, a,
                        QuantityMeasurementApp.Unit.YARDS);

        assertTrue(r1.equals(r2));
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength zero =
                new QuantityMeasurementApp.QuantityLength(0.0,
                        QuantityMeasurementApp.Unit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(a, zero,
                        QuantityMeasurementApp.Unit.YARDS);

        assertEquals(1.666666,
                result.convertTo(
                        QuantityMeasurementApp.Unit.YARDS).value,
                1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.Unit.INCH);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength.add(a, b, null));
    }

}