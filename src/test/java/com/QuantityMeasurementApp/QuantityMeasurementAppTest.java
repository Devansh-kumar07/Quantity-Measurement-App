package com.QuantityMeasurementApp;

public class QuantityMeasurementAppTest {
	public static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
	}
        
        // UC - 2 
        
        public static class Inches {

            private final double value;

            public Inches(double value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {

                if (this == obj) {
                    return true;
                }

                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }

                Inches other = (Inches) obj;
                return Double.compare(this.value, other.value) == 0;
            }
        }
        
        
        // UC - 3
        
        public enum Unit {

            FEET(12.0),    
            INCH(1.0),
        	YARDS(36.0),         
        	CENTIMETERS(0.393701);

            private final double conversionFactorToInch;

            Unit(double conversionFactorToInch) {
                this.conversionFactorToInch = conversionFactorToInch;
            }

            public double toInch(double value) {
                return value * conversionFactorToInch;
            }
        }
        
        
        public static class QuantityLength {

            private final double value;
            private final Unit unit;

            public QuantityLength(double value, Unit unit) {
                this.value = value;
                this.unit = unit;
            }

            @Override
            public boolean equals(Object obj) {

                if (this == obj) {
                    return true;
                }

                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }

                QuantityLength other = (QuantityLength) obj;

                double thisInInches = this.unit.toInch(this.value);
                double otherInInches = other.unit.toInch(other.value);

                return Double.compare(thisInInches, otherInInches) == 0;
            }
         // UC5 → Static conversion method
            public static double convert(double value, Unit source, Unit target) {

                if (!Double.isFinite(value)) {
                    throw new IllegalArgumentException("Invalid numeric value");
                }

                if (source == null || target == null) {
                    throw new IllegalArgumentException("Unit cannot be null");
                }

                double valueInInches = source.toInch(value);

                return valueInInches / target.toInch(1.0);
            }


            // UC5 → Instance conversion
            public QuantityLength convertTo(Unit target) {

                double convertedValue = convert(this.value, this.unit, target);

                return new QuantityLength(convertedValue, target);
            }


            // UC5
            @Override
            public String toString() {
                return "Quantity(" + value + ", " + unit + ")";
            }
            
         // UC6 → Add two QuantityLength objects
            public QuantityLength add(QuantityLength other) {

                if (other == null) {
                    throw new IllegalArgumentException("Second operand cannot be null");
                }

                if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
                    throw new IllegalArgumentException("Invalid numeric value");
                }

                // convert both values to inches (base unit)
                double thisInInch = this.unit.toInch(this.value);
                double otherInInch = other.unit.toInch(other.value);

                double sumInInch = thisInInch + otherInInch;

                // convert result back to unit of first operand
                double resultValue = sumInInch / this.unit.toInch(1.0);

                return new QuantityLength(resultValue, this.unit);
            }


            // UC6 → Static addition API
            public static QuantityLength add(QuantityLength a, QuantityLength b) {

                if (a == null || b == null) {
                    throw new IllegalArgumentException("Operands cannot be null");
                }

                return a.add(b);
            }
        }
        
        
     // UC5 - Demonstrate conversion
        public static void demonstrateLengthConversion(double value, Unit from, Unit to) {

            double result = QuantityLength.convert(value, from, to);

            System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
        }


        // UC5 - Overloaded method
        public static void demonstrateLengthConversion(QuantityLength length, Unit to) {

            QuantityLength converted = length.convertTo(to);

            System.out.println(length + " → " + converted);
        }


        // UC5 - Equality demo
        public static void demonstrateLengthEquality(QuantityLength l1, QuantityLength l2) {

            System.out.println(l1 + " equals " + l2 + " ? " + l1.equals(l2));
        }
        
        
        public static void main(String[] args) {
        	Feet feet1 = new Feet(1.0);
            Feet feet2 = new Feet(1.0);

            boolean result = feet1.equals(feet2);

            System.out.println("Input: 1.0 ft and 1.0 ft");
            System.out.println("Output: Equal (" + result + ")");
            
            // UC - 2 
            
            Inches inch1 = new Inches(1.0);
            Inches inch2 = new Inches(1.0);

            boolean inchResult = inch1.equals(inch2);

            System.out.println("Input: 1.0 inch and 1.0 inch");
            System.out.println("Output: Equal (" + inchResult + ")");
            
            
            // UC - 3
            
            QuantityLength feet = new QuantityLength(1.0, Unit.FEET);
            QuantityLength inch = new QuantityLength(12.0, Unit.INCH);

            boolean resultUC3 = feet.equals(inch);

            System.out.println("Input: 1.0 ft and 12.0 inch");
            System.out.println("Output: Equal (" + resultUC3 + ")");
            
            
            // UC - 4
            
            System.out.println("UC4 → " +
                    new QuantityLength(1.0, Unit.YARDS)
                            .equals(new QuantityLength(3.0, Unit.FEET)));

   
            System.out.println("UC4 → " +
                    new QuantityLength(1.0, Unit.YARDS)
                            .equals(new QuantityLength(36.0, Unit.INCH)));

           
            System.out.println("UC4 → " +
                    new QuantityLength(1.0, Unit.CENTIMETERS)
                            .equals(new QuantityLength(0.393701, Unit.INCH)));
            
            
         // UC - 5

            System.out.println("\nUC5 Conversions:");

            demonstrateLengthConversion(1.0, Unit.FEET, Unit.INCH);
            demonstrateLengthConversion(3.0, Unit.YARDS, Unit.FEET);
            demonstrateLengthConversion(36.0, Unit.INCH, Unit.YARDS);
            demonstrateLengthConversion(1.0, Unit.CENTIMETERS, Unit.INCH);
            demonstrateLengthConversion(0.0, Unit.FEET, Unit.INCH);

            QuantityLength lengthInYard = new QuantityLength(2.0, Unit.YARDS);
            demonstrateLengthConversion(lengthInYard, Unit.FEET);
            
            
         // UC6 → Addition

            System.out.println("\nUC6 Addition:");

            QuantityLength a = new QuantityLength(1.0, Unit.FEET);
            QuantityLength b = new QuantityLength(12.0, Unit.INCH);

            System.out.println("Add: " + a + " + " + b + " → " + a.add(b));

            QuantityLength c = new QuantityLength(1.0, Unit.YARDS);
            QuantityLength d = new QuantityLength(3.0, Unit.FEET);

            System.out.println("Add: " + c + " + " + d + " → " + c.add(d));
        }
}