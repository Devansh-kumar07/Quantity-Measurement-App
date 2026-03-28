package com.quantitymeasurementapp.controller;

import com.quantitymeasurementapp.dto.QuantityDTO;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // POST /api/v1/quantities/compare
    // Body: { "thisQuantity": {"value":1.0,"unit":"FEET"}, "thatQuantity": {"value":12.0,"unit":"INCH"} }
    @PostMapping("/compare")
    public ResponseEntity<Map<String, Object>> compareQuantities(@RequestBody Map<String, QuantityDTO> body) {
        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        boolean result = service.compare(a, b);                                   
        return ResponseEntity.ok(Map.of("result", result));
    }

    // POST /api/v1/quantities/convert?targetUnit=INCH
    // Body: {"value":1.0,"unit":"FEET"}
    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convertQuantity(
            @RequestBody QuantityDTO input,
            @RequestParam String targetUnit) {
        QuantityDTO result = service.convert(input, targetUnit);
        return ResponseEntity.ok(result);
    }

    // POST /api/v1/quantities/add
    // Body: { "thisQuantity": {"value":1.0,"unit":"FEET"}, "thatQuantity": {"value":12.0,"unit":"INCH"} }
    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> addQuantities(@RequestBody Map<String, QuantityDTO> body) {
        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        QuantityDTO result = service.add(a, b);
        return ResponseEntity.ok(result);
    }

    // POST /api/v1/quantities/subtract
    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtractQuantities(@RequestBody Map<String, QuantityDTO> body) {
        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        QuantityDTO result = service.subtract(a, b);
        return ResponseEntity.ok(result);
    }

    // POST /api/v1/quantities/divide
    @PostMapping("/divide")
    public ResponseEntity<Map<String, Object>> divideQuantities(@RequestBody Map<String, QuantityDTO> body) {
        QuantityDTO a = body.get("thisQuantity");
        QuantityDTO b = body.get("thatQuantity");
        double result = service.divide(a, b);
        return ResponseEntity.ok(Map.of("result", result));
    }

    // GET /api/v1/quantities/history
    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementEntity>> getHistory() {
        return ResponseEntity.ok(service.getHistory());
    }

    // GET /api/v1/quantities/history/add
    @GetMapping("/history/{operation}")
    public ResponseEntity<List<QuantityMeasurementEntity>> getHistoryByOperation(
            @PathVariable String operation) {
        return ResponseEntity.ok(service.getHistoryByOperation(operation));
    }
}
