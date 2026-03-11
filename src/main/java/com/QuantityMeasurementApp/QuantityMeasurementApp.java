package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCH);

		System.out.println("Length Equal? " + l1.equals(l2));

		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("Weight Equal? " + w1.equals(w2));
	}
}