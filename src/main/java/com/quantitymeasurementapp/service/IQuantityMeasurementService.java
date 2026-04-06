package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO a, QuantityDTO b);

    QuantityDTO convert(QuantityDTO input, String targetUnit);

    QuantityDTO add(QuantityDTO a, QuantityDTO b);

    QuantityDTO subtract(QuantityDTO a, QuantityDTO b);

    double divide(QuantityDTO a, QuantityDTO b);

    List<QuantityMeasurementEntity> getHistory();

    List<QuantityMeasurementEntity> getHistoryByOperation(String operation);
}
