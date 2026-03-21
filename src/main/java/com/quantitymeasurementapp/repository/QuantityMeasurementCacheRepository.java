package com.quantitymeasurementapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private List<QuantityMeasurementEntity> cache = new ArrayList<>();

    public QuantityMeasurementCacheRepository() {}

    public static QuantityMeasurementCacheRepository getInstance() {

        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    public List<QuantityMeasurementEntity> findAll() {
        return cache;
    }
}