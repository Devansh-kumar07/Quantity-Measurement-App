package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCH);

		System.out.println("Length Equal? " + l1.equals(l2));

		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("Weight Equal? " + w1.equals(w2));
		//uc -12 
		Quantity<LengthUnit> a =
		        new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> b =
		        new Quantity<>(6.0, LengthUnit.INCH);

		System.out.println(a.subtract(b));  
		// Output → Quantity(9.5 FEET)

		System.out.println(a.divide(b));  
		// Output → 20.0
		//UC-14 example
		Quantity<TemperatureUnit> c =
		        new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> f =
		        new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		System.out.println(c.equals(f)); // true
		//cocnversion
		Quantity<TemperatureUnit> temp =
		        new Quantity<>(100.0, TemperatureUnit.CELSIUS);

		System.out.println(
		        temp.convertTo(TemperatureUnit.FAHRENHEIT)
		);
	}
}