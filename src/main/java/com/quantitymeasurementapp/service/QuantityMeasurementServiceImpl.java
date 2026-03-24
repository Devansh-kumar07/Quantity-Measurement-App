package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // 🔹 COMMON SAVE METHOD
    private void record(String operation, double value, String unit) {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(operation, value, unit);
        repository.save(entity);
    }

    // 🔹 COMPARE
    @Override
    public boolean compare(QuantityDTO a, QuantityDTO b) {

        boolean result = a.value == b.value; // simple logic (improve later)

        record("COMPARE", result ? 1 : 0, a.unit);

        return result;
    }

    // 🔹 CONVERT
    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {

        // dummy conversion (replace with real logic)
        QuantityDTO result =
                new QuantityDTO(input.value, targetUnit);

        record("CONVERT", result.value, targetUnit);

        return result;
    }

    // 🔹 ADD
    @Override
    public QuantityDTO add(QuantityDTO a, QuantityDTO b) {

        double resultValue = a.value + b.value;

        QuantityDTO result =
                new QuantityDTO(resultValue, a.unit);

        record("ADD", resultValue, a.unit);

        return result;
    }

    // 🔹 SUBTRACT
    @Override
    public QuantityDTO subtract(QuantityDTO a, QuantityDTO b) {

        double resultValue = a.value - b.value;

        QuantityDTO result =
                new QuantityDTO(resultValue, a.unit);

        record("SUBTRACT", resultValue, a.unit);

        return result;
    }

    // 🔹 DIVIDE
    @Override
    public double divide(QuantityDTO a, QuantityDTO b) {

        if (b.value == 0) {
            throw new ArithmeticException("Division by zero");
        }

        double result = a.value / b.value;

        record("DIVIDE", result, "RATIO");

        return result;
    }
}