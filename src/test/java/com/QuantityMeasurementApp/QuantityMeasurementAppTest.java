package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.unit.LengthUnit;
import com.quantitymeasurementapp.unit.Quantity;
import com.quantitymeasurementapp.unit.TemperatureUnit;
import com.quantitymeasurementapp.unit.VolumeUnit;
import com.quantitymeasurementapp.unit.WeightUnit;

public class QuantityMeasurementAppTest {

    // =============================
    // UC1–UC4 LENGTH EQUALITY
    // =============================

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inch));
    }

    // =============================
    // UC5 LENGTH CONVERSION
    // =============================

    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                feet.convertTo(LengthUnit.INCH);

        assertEquals(12.0, inch.value, 1e-6);
    }

    // =============================
    // UC6–UC7 ADDITION
    // =============================

    @Test
    void testLengthAddition() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                a.add(b);

        assertEquals(2.0, result.value, 1e-6);
    }

    // =============================
    // UC8–UC9 WEIGHT
    // =============================

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

    // =============================
    // UC11 VOLUME
    // =============================

    @Test
    void testVolumeEquality() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(litre.equals(ml));
    }

    @Test
    void testVolumeAddition() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                a.add(b);

        assertEquals(2.0,
                result.convertTo(VolumeUnit.LITRE).value,
                1e-6);
    }

    // =============================
    // UC12 SUBTRACTION
    // =============================

    @Test
    void testSubtraction() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(6.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                a.subtract(b);

        assertEquals(9.5, result.value, 1e-6);
    }

    // =============================
    // UC12 DIVISION
    // =============================

    @Test
    void testDivision() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, a.divide(b), 1e-6);
    }

    // =============================
    // UC13 CROSS CATEGORY SAFETY
    // =============================

    @Test
    void testCrossCategoryComparison() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // =============================
    // UC14 TEMPERATURE
    // =============================

    @Test
    void testTemperatureEquality() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    @Test
    void testTemperatureConversion() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.value, 1e-6);
    }

    @Test
    void testTemperatureUnsupportedOperation() {

        Quantity<TemperatureUnit> a =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> b =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> a.add(b)
        );
    }
}