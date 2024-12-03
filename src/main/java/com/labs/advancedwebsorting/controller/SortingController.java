package com.labs.advancedwebsorting.controller;

import com.labs.advancedwebsorting.algorithms.SortingService;
import com.labs.advancedwebsorting.entity.SortRequest;
//import com.labs.advancedwebsorting.entity.SortResult;
//import com.labs.advancedwebsorting.service.SortingServic;
import com.labs.advancedwebsorting.model.SortResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sort")
public class SortingController {

    private final SortingService sortingService;

    // Constructor Injection of SortingService
    @Autowired
    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    /**
     * Endpoint for sorting data using the specified algorithm.
     *
     * @param request The sorting request containing the algorithm type and data to be sorted.
     * @return A SortResult object containing the sorted data and metadata.
     */
    @PostMapping("/sorting")
    public SortResult sort(@RequestBody SortRequest request) {
        long startTime = System.currentTimeMillis();
        List<Integer> sortedData;


        // Determine the algorithm to use based on the request
        switch (request.getAlgorithm().toLowerCase()) {
            case "heap":
                sortedData = sortingService.heapSort(request.getData());
                break;
            case "quick":
                sortedData = sortingService.quickSort(request.getData());
                break;
            case "merge":
                sortedData = sortingService.mergeSort(request.getData());
                break;
                 case "bucket":
            sortedData = sortingService.bucketSort(request.getData());
            break;
        case "radix":
            sortedData = sortingService.radixSort(request.getData());
            break;
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + request.getAlgorithm());
        }

        long executionTime = System.currentTimeMillis() - startTime;

        // Build the response
        SortResult result = new SortResult();
        result.setAlgorithm(request.getAlgorithm());
        result.setSortedData(sortedData);
        result.setExecutionTime(executionTime);

        return result;
    }
}
