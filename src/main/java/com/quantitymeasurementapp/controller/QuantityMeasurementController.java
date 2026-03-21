package com.quantitymeasurementapp.controller;

import com.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementController {

    private final QuantityMeasurementServiceImpl service;

    public QuantityMeasurementController(
            QuantityMeasurementServiceImpl service) {
        this.service = service;
    }

    public void performAdd(double result, String unit) {

        service.record("ADD", result, unit);

        System.out.println("Saved: " + result + " " + unit);
    }
}