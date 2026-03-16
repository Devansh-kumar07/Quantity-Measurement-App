package com.quantitymeasurementapp.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private String operation;
    private String result;
    private boolean error;

    public QuantityMeasurementEntity(String operation, String result) {
        this.operation = operation;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(String errorMessage) {
        this.result = errorMessage;
        this.error = true;
    }

    public boolean hasError() {
        return error;
    }

    public String getResult() {
        return result;
    }
}