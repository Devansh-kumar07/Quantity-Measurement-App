package com.quantitymeasurementapp.controller;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public QuantityDTO performAddition(QuantityDTO a, QuantityDTO b) {
        return service.add(a, b);
    }

    public void  performComparison(QuantityDTO a, QuantityDTO b) {

        boolean result = service.compare(a, b);

        System.out.println("Comparison Result: " + result);
		
    }
}