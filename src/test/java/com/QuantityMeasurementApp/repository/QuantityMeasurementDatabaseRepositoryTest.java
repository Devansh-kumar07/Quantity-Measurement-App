package com.quantitymeasurementapp.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;

public class QuantityMeasurementDatabaseRepositoryTest {

    @Test
    void testSaveToDatabase() {
        QuantityMeasurementDatabaseRepository repo =
                new QuantityMeasurementDatabaseRepository();

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", 2.0, "FEET");

        assertDoesNotThrow(() -> repo.save(entity));
    }
}