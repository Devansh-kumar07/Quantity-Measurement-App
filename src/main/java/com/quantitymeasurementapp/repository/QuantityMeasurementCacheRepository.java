package com.quantitymeasurementapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return new ArrayList<>(cache);
    }
}