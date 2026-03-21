package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    public void record(String operation, double value, String unit) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(operation, value, unit);

        repository.save(entity);
    }
}