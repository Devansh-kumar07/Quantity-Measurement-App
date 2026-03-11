package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        System.out.println(q1 + " equals " + q2 + " → " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> q,
            U targetUnit) {

        System.out.println(q + " → " + q.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> a,
            Quantity<U> b,
            U targetUnit) {

        System.out.println(a + " + " + b + " → " + a.add(b, targetUnit));
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        demonstrateEquality(feet, inch);
        demonstrateConversion(feet, LengthUnit.INCH);
        demonstrateAddition(feet, inch, LengthUnit.FEET);


        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> g =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(kg, g);
        demonstrateConversion(kg, WeightUnit.GRAM);
        demonstrateAddition(kg, g, WeightUnit.KILOGRAM);
    }
}