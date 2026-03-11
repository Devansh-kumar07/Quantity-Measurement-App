package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    // ---------------- LENGTH TESTS (UC1–UC8) ----------------

    @Test
    void testLengthEquality_FeetToInch() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inch));
    }

    @Test
    void testLengthEquality_DifferentValue() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertFalse(a.equals(b));
    }

    @Test
    void testLengthConversion_FeetToInch() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.value, 1e-6);
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


    // ---------------- WEIGHT TESTS (UC9) ----------------

    @Test
    void testWeightEquality_KgToGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    @Test
    void testWeightConversion_KgToGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, gram.value, 1e-6);
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


    // ---------------- VOLUME TESTS (UC11) ----------------

    @Test
    void testVolumeEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(litre.equals(ml));
    }

    @Test
    void testVolumeConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, ml.value, 1e-6);
    }

    @Test
    void testVolumeAddition() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                a.add(b, VolumeUnit.LITRE);

        assertEquals(2.0, result.value, 1e-6);
    }


    // ---------------- GENERIC EDGE TESTS ----------------

    @Test
    void testNullComparison() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(q.equals(null));
    }

    @Test
    void testSameReference() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testCrossCategoryComparison() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    @Test
    void testAdditionWithZero() {

        Quantity<LengthUnit> a =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> zero =
                new Quantity<>(0.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = a.add(zero);

        assertEquals(5.0,
                result.convertTo(LengthUnit.FEET).value,
                1e-6);
    }

    @Test
    void testNegativeValues() {

        Quantity<WeightUnit> a =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(-2000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = a.add(b);

        assertEquals(3.0,
                result.convertTo(WeightUnit.KILOGRAM).value,
                1e-6);
    }
}