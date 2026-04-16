package com.qma.historyservice.controller;

import com.qma.historyservice.entity.HistoryEntity;
import com.qma.historyservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    // measurement-service POST karta hai — username body mein bhejega
    @PostMapping
    public ResponseEntity<String> saveHistory(@RequestBody Map<String, Object> body) {
        String username  = (String) body.get("username");
        String operation = (String) body.get("operation");
        double value     = ((Number) body.get("value")).doubleValue();
        String unit      = (String) body.get("unit");
        historyRepository.save(new HistoryEntity(username, operation, value, unit));
        return ResponseEntity.ok("Saved");
    }

    // GET /history — sirf is user ki history
    @GetMapping
    public ResponseEntity<List<HistoryEntity>> getAll(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        return ResponseEntity.ok(historyRepository.findByUsername(username));
    }

    // GET /history/{operation} — is user ki filtered history
    @GetMapping("/{operation}")
    public ResponseEntity<List<HistoryEntity>> getByOperation(
            @PathVariable String operation,
            @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        return ResponseEntity.ok(historyRepository.findByUsernameAndOperation(username, operation));
    }

    // DELETE /history — sirf is user ka sab delete
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        historyRepository.deleteByUsername(username);
        return ResponseEntity.noContent().build();   // 204 — Angular void expect karta hai
    }

    // DELETE /history/{id} — specific entry delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        historyRepository.deleteById(id);
        return ResponseEntity.noContent().build();   // 204
    }
}