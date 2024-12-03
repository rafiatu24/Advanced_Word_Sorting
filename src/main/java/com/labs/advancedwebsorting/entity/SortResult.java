package com.labs.advancedwebsorting.model;

import java.util.List;

public class SortResult {
    private String algorithm; // The algorithm used
    private List<Integer> sortedData; // The sorted dataset
    private long executionTime; // Time taken for sorting in milliseconds

    // Getters and Setters
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public List<Integer> getSortedData() {
        return sortedData;
    }

    public void setSortedData(List<Integer> sortedData) {
        this.sortedData = sortedData;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return "SortResult{" +
                "algorithm='" + algorithm + '\'' +
                ", sortedData=" + sortedData +
                ", executionTime=" + executionTime +
                '}';
    }
}
