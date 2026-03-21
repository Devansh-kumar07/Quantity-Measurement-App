package com.quantitymeasurementapp.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;

public class QuantityMeasurementServiceImplTest {

    @Test
    void testAddition() {
        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        new QuantityMeasurementCacheRepository());

        QuantityDTO a = new QuantityDTO(1.0, "FEET");
        QuantityDTO b = new QuantityDTO(12.0, "INCH");

        QuantityDTO result = service.add(a, b);

        assertEquals(2.0, result.getValue(), 1e-6);
    }
}