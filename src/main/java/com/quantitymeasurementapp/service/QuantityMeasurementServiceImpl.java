package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.repository.QuantityMeasurementRepository;
import com.quantitymeasurementapp.unit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // Resolve unit string to IMeasurable enum constant
    private IMeasurable resolveUnit(String unitName) {
        try { return LengthUnit.valueOf(unitName); } catch (Exception ignored) {}
        try { return WeightUnit.valueOf(unitName); } catch (Exception ignored) {}
        try { return VolumeUnit.valueOf(unitName); } catch (Exception ignored) {}
        try { return TemperatureUnit.valueOf(unitName); } catch (Exception ignored) {}
        throw new IllegalArgumentException("Unknown unit: " + unitName);
    }

    @Override
    public boolean compare(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = resolveUnit(a.getUnit());
        IMeasurable unitB = resolveUnit(b.getUnit());

        Quantity<IMeasurable> q1 = new Quantity<>(a.getValue(), unitA);
        Quantity<IMeasurable> q2 = new Quantity<>(b.getValue(), unitB);

        boolean result = q1.equals(q2);

        repository.save(new QuantityMeasurementEntity("compare", a.getValue(), a.getUnit()));
        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {
        IMeasurable fromUnit = resolveUnit(input.getUnit());
        IMeasurable toUnit   = resolveUnit(targetUnit);

        Quantity<IMeasurable> quantity = new Quantity<>(input.getValue(), fromUnit);
        Quantity<IMeasurable> converted = quantity.convertTo(toUnit);

        repository.save(new QuantityMeasurementEntity("convert", input.getValue(), input.getUnit()));
        return new QuantityDTO(converted.getValue(), toUnit.getUnitName());
    }

    @Override
    public QuantityDTO add(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = resolveUnit(a.getUnit());
        IMeasurable unitB = resolveUnit(b.getUnit());

        Quantity<IMeasurable> q1 = new Quantity<>(a.getValue(), unitA);
        Quantity<IMeasurable> q2 = new Quantity<>(b.getValue(), unitB);

        Quantity<IMeasurable> result = q1.add(q2);

        repository.save(new QuantityMeasurementEntity("add", a.getValue(), a.getUnit()));
        return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = resolveUnit(a.getUnit());
        IMeasurable unitB = resolveUnit(b.getUnit());

        Quantity<IMeasurable> q1 = new Quantity<>(a.getValue(), unitA);
        Quantity<IMeasurable> q2 = new Quantity<>(b.getValue(), unitB);

        Quantity<IMeasurable> result = q1.subtract(q2);

        repository.save(new QuantityMeasurementEntity("subtract", a.getValue(), a.getUnit()));
        return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
    }

    @Override
    public double divide(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = resolveUnit(a.getUnit());
        IMeasurable unitB = resolveUnit(b.getUnit());

        Quantity<IMeasurable> q1 = new Quantity<>(a.getValue(), unitA);
        Quantity<IMeasurable> q2 = new Quantity<>(b.getValue(), unitB);

        double result = q1.divide(q2);

        repository.save(new QuantityMeasurementEntity("divide", a.getValue(), a.getUnit()));
        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getHistory() {
        return repository.findAll();
    }

    @Override
    public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
        return repository.findByOperation(operation);
    }
}
