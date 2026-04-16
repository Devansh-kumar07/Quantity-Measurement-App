package com.qma.measurementservice.controller;

import com.qma.measurementservice.dto.QuantityDTO;
import com.qma.measurementservice.service.HistoryClient;
import com.qma.measurementservice.service.IMeasurementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/quantities")
public class MeasurementController {

    @Autowired private IMeasurementService service;
    @Autowired private HistoryClient historyClient;

    // POST /api/v1/quantities/compare
    @PostMapping("/compare")
    public ResponseEntity<Map<String, Object>> compare(
            @RequestBody Map<String, @Valid QuantityDTO> body,
            @RequestHeader("Authorization") String authHeader) {

        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        boolean result = service.compare(a, b);

        // Save to history-service (fire and forget)
        historyClient.saveHistory("compare", a.getValue(), a.getUnit(), authHeader);

        return ResponseEntity.ok(Map.of("result", result));
    }

    // POST /api/v1/quantities/convert?targetUnit=INCH
    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(
            @Valid @RequestBody QuantityDTO input,
            @RequestParam String targetUnit,
            @RequestHeader("Authorization") String authHeader) {

        QuantityDTO result = service.convert(input, targetUnit);
        historyClient.saveHistory("convert", input.getValue(), input.getUnit(), authHeader);
        return ResponseEntity.ok(result);
    }

    // POST /api/v1/quantities/add
    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(
            @RequestBody Map<String, @Valid QuantityDTO> body,
            @RequestHeader("Authorization") String authHeader) {

        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        QuantityDTO result = service.add(a, b);
        historyClient.saveHistory("add", a.getValue(), a.getUnit(), authHeader);
        return ResponseEntity.ok(result);
    }

    // POST /api/v1/quantities/subtract
    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(
            @RequestBody Map<String, @Valid QuantityDTO> body,
            @RequestHeader("Authorization") String authHeader) {

        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        QuantityDTO result = service.subtract(a, b);
        historyClient.saveHistory("subtract", a.getValue(), a.getUnit(), authHeader);
        return ResponseEntity.ok(result);
    }

    // POST /api/v1/quantities/divide
    @PostMapping("/divide")
    public ResponseEntity<Map<String, Object>> divide(
            @RequestBody Map<String, @Valid QuantityDTO> body,
            @RequestHeader("Authorization") String authHeader) {

        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        double result = service.divide(a, b);
        historyClient.saveHistory("divide", a.getValue(), a.getUnit(), authHeader);
        return ResponseEntity.ok(Map.of("result", result));
    }
}
