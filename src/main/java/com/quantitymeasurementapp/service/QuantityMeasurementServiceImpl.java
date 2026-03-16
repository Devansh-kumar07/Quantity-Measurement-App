package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.service.*;
import com.quantitymeasurementapp.LengthUnit;
import com.quantitymeasurementapp.Quantity;
import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO a, QuantityDTO b) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(a.value, LengthUnit.valueOf(a.unit));

        Quantity<LengthUnit> q2 =
                new Quantity<>(b.value, LengthUnit.valueOf(b.unit));

        return q1.equals(q2);
    }

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {

        Quantity<LengthUnit> q =
                new Quantity<>(input.value, LengthUnit.valueOf(input.unit));

        Quantity<LengthUnit> result =
                q.convertTo(LengthUnit.valueOf(targetUnit));

        return new QuantityDTO(result.getValue(), targetUnit);
    }

    @Override
    public QuantityDTO add(QuantityDTO a, QuantityDTO b) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(a.value, LengthUnit.valueOf(a.unit));

        Quantity<LengthUnit> q2 =
                new Quantity<>(b.value, LengthUnit.valueOf(b.unit));

        Quantity<LengthUnit> result = q1.add(q2);

        return new QuantityDTO(result.getValue(), a.unit);
    }

    @Override
    public QuantityDTO subtract(QuantityDTO a, QuantityDTO b) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(a.value, LengthUnit.valueOf(a.unit));

        Quantity<LengthUnit> q2 =
                new Quantity<>(b.value, LengthUnit.valueOf(b.unit));

        Quantity<LengthUnit> result = q1.subtract(q2);

        return new QuantityDTO(result.getValue(), a.unit);
    }

    @Override
    public double divide(QuantityDTO a, QuantityDTO b) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(a.value, LengthUnit.valueOf(a.unit));

        Quantity<LengthUnit> q2 =
                new Quantity<>(b.value, LengthUnit.valueOf(b.unit));

        return q1.divide(q2);
    }
}