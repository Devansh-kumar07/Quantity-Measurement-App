package com.quantitymeasurementapp.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepositoryTest {

    @Test
    void testSave() {
        QuantityMeasurementCacheRepository repo =
                new QuantityMeasurementCacheRepository();

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", 2.0, "FEET");

        repo.save(entity);

        assertTrue(repo.findAll().size() > 0);
    }
}