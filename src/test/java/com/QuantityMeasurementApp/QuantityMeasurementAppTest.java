package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
	//Implementing interface.
    @Test
    void testIMeasurable_LengthUnitImplementation() {
        IMeasurable unit = LengthUnit.FEET;

        assertNotNull(unit.getConversionFactor());
        assertEquals("FEET", unit.getUnitName());
    }

    @Test
    void testIMeasurable_WeightUnitImplementation() {
        IMeasurable unit = WeightUnit.KILOGRAM;

        assertNotNull(unit.getConversionFactor());
        assertEquals("KILOGRAM", unit.getUnitName());
    }

    
    //Lenght equality
    @Test
    void testGenericQuantity_LengthEquality() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertTrue(a.equals(b));
    }

    // Weight Equality
    @Test
    void testGenericQuantity_WeightEquality() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    // Length Conversion
     @Test
    void testGenericQuantity_LengthConversion() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                a.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), 1e-6);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    // Weight Conversion
        @Test
    void testGenericQuantity_WeightConversion() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                a.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 1e-6);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // Length Addition
     @Test
    void testGenericQuantity_LengthAddition() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

     // Weight Addition
   
    @Test
    void testGenericQuantity_WeightAddition() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                a.add(b, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    // Cross Category Prevention
     @Test
    void testCrossCategory_LengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // Constructor Validation
     @Test
    void testConstructor_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }

    @Test
    void testConstructor_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET)
        );
    }

}