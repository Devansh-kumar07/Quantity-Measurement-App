package com.quantitymeasurementapp.controller;

import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;
import com.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;

public class QuantityMeasurementControllerTest {

    @Test
    void testPerformAdd() {
        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        new QuantityMeasurementServiceImpl(
                                new QuantityMeasurementCacheRepository()));

        QuantityDTO a = new QuantityDTO(1.0, "FEET");
        QuantityDTO b = new QuantityDTO(12.0, "INCH");

        controller.performAdd(a, b); // just check no crash
    }
}