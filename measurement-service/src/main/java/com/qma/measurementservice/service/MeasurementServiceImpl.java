package com.qma.measurementservice.service;

import com.qma.measurementservice.dto.QuantityDTO;
import com.qma.measurementservice.unit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementServiceImpl implements IMeasurementService {

    @Autowired
    private HistoryClient historyClient;

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
        return q1.equals(q2);
    }

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {
        IMeasurable fromUnit = resolveUnit(input.getUnit());
        IMeasurable toUnit   = resolveUnit(targetUnit);
        Quantity<IMeasurable> qty = new Quantity<>(input.getValue(), fromUnit);
        Quantity<IMeasurable> converted = qty.convertTo(toUnit);
        return new QuantityDTO(converted.getValue(), toUnit.getUnitName());
    }

    @Override
    public QuantityDTO add(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = resolveUnit(a.getUnit());
        IMeasurable unitB = resolveUnit(b.getUnit());
        Quantity<IMeasurable> q1 = new Quantity<>(a.getValue(), unitA);
        Quantity<IMeasurable> q2 = new Quantity<>(b.getValue(), unitB);
        Quantity<IMeasurable> result = q1.add(q2);
        return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = resolveUnit(a.getUnit());
        IMeasurable unitB = resolveUnit(b.getUnit());
        Quantity<IMeasurable> q1 = new Quantity<>(a.getValue(), unitA);
        Quantity<IMeasurable> q2 = new Quantity<>(b.getValue(), unitB);
        Quantity<IMeasurable> result = q1.subtract(q2);
        return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
    }

    @Override
    public double divide(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = resolveUnit(a.getUnit());
        IMeasurable unitB = resolveUnit(b.getUnit());
        Quantity<IMeasurable> q1 = new Quantity<>(a.getValue(), unitA);
        Quantity<IMeasurable> q2 = new Quantity<>(b.getValue(), unitB);
        return q1.divide(q2);
    }
}
