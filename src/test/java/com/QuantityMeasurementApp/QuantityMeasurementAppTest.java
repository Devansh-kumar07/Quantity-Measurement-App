package com.QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



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
    void testEquality_NullComparison() {
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
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1), "Object must be equal to itself");
    }
    
    
    
    
    //  INCH TEST CASES

    @Test
    void testInchEquality_SameValue() {
    	QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
    	QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(1.0);

        assertTrue(i1.equals(i2), "1.0 inch should be equal to 1.0 inch");
    }

    @Test
    void testInchEquality_DifferentValue() {
    	QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
    	QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(2.0);

        assertFalse(i1.equals(i2), "1.0 inch should not be equal to 2.0 inch");
    }

    @Test
    void testInchEquality_NullComparison() {
    	QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);

        assertFalse(i1.equals(null), "Inches object should not be equal to null");
    }

    @Test
    void testInchEquality_NonNumericInput() {
    	QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
        String nonNumeric = "1.0";

        assertFalse(i1.equals(nonNumeric), "Inches should not be equal to non-numeric object");
    }

    @Test
    void testInchEquality_SameReference() {
    	QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);

        assertTrue(i1.equals(i1), "Object must be equal to itself");
    }

}