package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    // LENGTH TEST

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inch));
    }

    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                feet.convertTo(LengthUnit.INCH);

        assertEquals(12.0, inch.value, 1e-6);
    }

    @Test
    void testLengthAddition() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.value, 1e-6);
    }


    // WEIGHT TEST

    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> g =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(g));
    }

    @Test
    void testWeightConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> g =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, g.value, 1e-6);
    }

    @Test
    void testWeightAddition() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                a.add(b, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.value, 1e-6);
    }


    // CROSS CATEGORY TEST

    @Test
    void testCrossCategoryComparison() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
}