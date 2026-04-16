package com.qma.measurementservice.service;

import com.qma.measurementservice.dto.HistoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Base64;
import org.springframework.util.StringUtils;

@Service
public class HistoryClient {

    private static final Logger log = LoggerFactory.getLogger(HistoryClient.class);
    private final WebClient webClient;

    public HistoryClient(@Value("${history.service.url}") String historyServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(historyServiceUrl).build();
    }

    // JWT token se username nikalta hai
    private String extractUsername(String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String payload = new String(Base64.getUrlDecoder().decode(token.split("\\.")[1]));
            // {"sub":"username",...} se sub nikalo
            for (String part : payload.replace("{", "").replace("}", "").split(",")) {
                String[] kv = part.trim().split(":");
                if (kv.length == 2 && kv[0].replace("\"", "").equals("sub")) {
                    return kv[1].replace("\"", "").trim();
                }
            }
        } catch (Exception e) {
            log.warn("Could not extract username from token: {}", e.getMessage());
        }
        return "unknown";
    }

    public void saveHistory(String operation, double value, String unit, String authHeader) {
        String username = extractUsername(authHeader);   // ← USERNAME NIKALO
        HistoryRequest req = new HistoryRequest(username, operation, value, unit);
        webClient.post()
                .uri("/history")
                .header("Authorization", authHeader)
                .bodyValue(req)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(e -> log.error("Failed to save history: {}", e.getMessage()))
                .subscribe();
    }
}