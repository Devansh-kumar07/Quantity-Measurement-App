package com.quantitymeasurementapp;

import com.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.quantitymeasurementapp.service.IQuantityMeasurementService;
import com.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementCacheRepository repo =
                new QuantityMeasurementCacheRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController((IQuantityMeasurementService) service);

        QuantityDTO a = new QuantityDTO(2.0, "FEET");
        QuantityDTO b = new QuantityDTO(12.0, "INCH");

        controller.performAddition(a, b); 
    }
}