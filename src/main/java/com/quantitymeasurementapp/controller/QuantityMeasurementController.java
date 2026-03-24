package com.quantitymeasurementapp.controller;

import java.util.List;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performAddition(QuantityDTO d, QuantityDTO string) {

        QuantityDTO result = service.add(d, string);

        System.out.println("Addition Result: "
                + result.getValue() + " " + result.getUnit());
    }

    public void performComparison(QuantityDTO a, QuantityDTO b) {

        boolean result = service.compare(a, b);

        System.out.println("Comparison Result: " + result);
    }
    public void showAllRecords() {

        List<QuantityMeasurementEntity> list = service.getAllRecords();

        for (QuantityMeasurementEntity e : list) {
            System.out.println(
                    e.getOperation() + " | " +
                    e.getValue() + " | " +
                    e.getUnit()
            );
        }
    }
}