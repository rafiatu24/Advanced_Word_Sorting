package com.labs.advancedwebsorting.entity;

import java.util.List;

public class SortRequest {
    private String algorithm; // e.g., "heap", "quick", "merge"
    private List<Integer> data; // The dataset to be sorted

    // Getters and Setters
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SortRequest{" +
                "algorithm='" + algorithm + '\'' +
                ", data=" + data +
                '}';
    }
}
