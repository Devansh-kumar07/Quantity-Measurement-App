package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {
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
        
       static public class Inches {

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
        }
}