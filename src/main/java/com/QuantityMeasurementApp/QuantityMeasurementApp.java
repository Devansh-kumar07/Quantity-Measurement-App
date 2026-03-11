package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    // UC1
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // UC2
    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }


    // UC3 → QuantityLength
    public static class QuantityLength {

        public double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            double thisBase = this.unit.convertToBaseUnit(this.value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            return Double.compare(thisBase, otherBase) == 0;
        }

        // UC5 conversion
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            if (source == null || target == null)
                throw new IllegalArgumentException("Unit cannot be null");

            double base = source.convertToBaseUnit(value);

            return target.convertFromBaseUnit(base);
        }

        public QuantityLength convertTo(LengthUnit target) {

            double converted = convert(this.value, this.unit, target);

            return new QuantityLength(converted, target);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }

        // UC6 addition
        public QuantityLength add(QuantityLength other) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            double base1 = this.unit.convertToBaseUnit(this.value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            double sum = base1 + base2;

            double result = this.unit.convertFromBaseUnit(sum);

            return new QuantityLength(result, this.unit);
        }

        public static QuantityLength add(QuantityLength a, QuantityLength b) {

            if (a == null || b == null)
                throw new IllegalArgumentException("Operands cannot be null");

            return a.add(b);
        }

        // UC7 addition with target unit
        public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {

            if (a == null || b == null)
                throw new IllegalArgumentException("Operands cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double base1 = a.unit.convertToBaseUnit(a.value);
            double base2 = b.unit.convertToBaseUnit(b.value);

            double sum = base1 + base2;

            double result = targetUnit.convertFromBaseUnit(sum);

            return new QuantityLength(result, targetUnit);
        }
    }


    // UC5 helpers
    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);

        System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength length, LengthUnit to) {

        QuantityLength converted = length.convertTo(to);

        System.out.println(length + " → " + converted);
    }

    public static void demonstrateLengthEquality(QuantityLength l1, QuantityLength l2) {

        System.out.println(l1 + " equals " + l2 + " ? " + l1.equals(l2));
    }


    public static void main(String[] args) {

        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feet1.equals(feet2) + ")");


        Inches inch1 = new Inches(1.0);
        Inches inch2 = new Inches(1.0);

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + inch1.equals(inch2) + ")");


        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Input: 1.0 ft and 12.0 inch");
        System.out.println("Output: Equal (" + feet.equals(inch) + ")");


        System.out.println("UC4 → " +
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(3.0, LengthUnit.FEET)));


        System.out.println("\nUC5 Conversions:");

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);


        System.out.println("\nUC6 Addition:");

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Add: " + a + " + " + b + " → " + a.add(b));


        System.out.println("\nUC7 Addition with Target Unit:");

        System.out.println("Add in FEET → " +
                QuantityLength.add(a, b, LengthUnit.FEET));

        System.out.println("Add in INCH → " +
                QuantityLength.add(a, b, LengthUnit.INCH));

        System.out.println("Add in YARDS → " +
                QuantityLength.add(a, b, LengthUnit.YARDS));
    }
}