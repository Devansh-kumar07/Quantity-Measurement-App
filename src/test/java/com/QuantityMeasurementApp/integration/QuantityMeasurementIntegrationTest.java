package com.quantitymeasurementapp.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementIntegrationTest {

    @Test
    void testEndToEndAdd() {

        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        new QuantityMeasurementServiceImpl(
                                new QuantityMeasurementCacheRepository()));

        QuantityDTO a = new QuantityDTO(1.0, "FEET");
        QuantityDTO b = new QuantityDTO(12.0, "INCH");

        assertDoesNotThrow(() -> controller.performAddition(a, b));
    }
}