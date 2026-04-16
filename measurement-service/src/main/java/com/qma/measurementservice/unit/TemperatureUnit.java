package com.qma.measurementservice.unit;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS {
        public double convertToBaseUnit(double value) { return value; }
        public double convertFromBaseUnit(double base) { return base; }
    },
    FAHRENHEIT {
        public double convertToBaseUnit(double value) { return (value - 32) * 5 / 9; }
        public double convertFromBaseUnit(double base) { return (base * 9 / 5) + 32; }
    },
    KELVIN {
        public double convertToBaseUnit(double value) { return value - 273.15; }
        public double convertFromBaseUnit(double base) { return base + 273.15; }
    };

    SupportsArithmetic supportsArithmetic = () -> false;
    @Override public boolean supportsArithmetic() { return false; }
    @Override public void validateOperationSupport(String op) {
        throw new UnsupportedOperationException("Temperature does not support " + op);
    }
    @Override public double getConversionFactor() { return 1.0; }
    @Override public String getUnitName() { return name(); }
}
