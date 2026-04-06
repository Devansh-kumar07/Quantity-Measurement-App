package com.quantitymeasurementapp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    public String getApplicationName() {
        return applicationName;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }
}
