package com.qma.measurementservice.service;

import com.qma.measurementservice.dto.QuantityDTO;

public interface IMeasurementService {
    boolean compare(QuantityDTO a, QuantityDTO b);
    QuantityDTO convert(QuantityDTO input, String targetUnit);
    QuantityDTO add(QuantityDTO a, QuantityDTO b);
    QuantityDTO subtract(QuantityDTO a, QuantityDTO b);
    double divide(QuantityDTO a, QuantityDTO b);
}
