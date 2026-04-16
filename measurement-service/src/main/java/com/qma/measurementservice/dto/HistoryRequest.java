package com.qma.measurementservice.dto;

public class HistoryRequest {
    private String username;   // ← NAYA
    private String operation;
    private double value;
    private String unit;

    public HistoryRequest() {}
    public HistoryRequest(String username, String operation, double value, String unit) {
        this.username  = username;
        this.operation = operation;
        this.value     = value;
        this.unit      = unit;
    }

    public String getUsername()  { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    public double getValue()     { return value; }
    public void setValue(double value) { this.value = value; }
    public String getUnit()      { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}