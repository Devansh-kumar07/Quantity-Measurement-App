package com.quantitymeasurementapp;

import com.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementCacheRepository repo =
                new QuantityMeasurementCacheRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        controller.performAdd(2.0, "FEET");
    }
}