package com.qma.measurementservice.unit;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();

    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() { return supportsArithmetic.isSupported(); }

    default void validateOperationSupport(String operation) {
        // overridden in TemperatureUnit
    }
}
