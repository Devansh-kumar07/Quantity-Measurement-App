package com.quantitymeasurementapp.entity;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementEntityTest {

    @Test
    void testEntityCreation() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", 2.0, "FEET");

        assertEquals("ADD", entity.getOperation());
        assertEquals(2.0, entity.getValue());
    }
}
