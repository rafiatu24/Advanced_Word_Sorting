package com.labs.advancedwebsorting.algorithms;

import com.labs.advancedwebsorting.controller.SortingController;
import com.labs.advancedwebsorting.entity.SortRequest;
import com.labs.advancedwebsorting.model.SortResult;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trial  {
 public List<Integer> bucketSort(List<Integer> data) {
       if (data.isEmpty()) return data;

       // Determine the maximum value to set bucket size
       int max = data.stream().max(Integer::compareTo).orElse(0);
       int min = data.stream().min(Integer::compareTo).orElse(0);
       int bucketCount = Math.max((max - min) / data.size() + 1, 1);

       // Create empty buckets
       List<List<Integer>> buckets = new ArrayList<>();
       for (int i = 0; i < bucketCount; i++) {
           buckets.add(new ArrayList<>());
       }

       // Distribute the input elements into the buckets
       for (int num : data) {
           int bucketIndex = (num - min) / bucketCount;
           buckets.get(bucketIndex).add(num);
       }

       // Sort individual buckets and merge them
       List<Integer> sorted = new ArrayList<>();
       for (List<Integer> bucket : buckets) {
           sorted.addAll(quickSort(bucket)); // Use quickSort for sorting buckets
       }

       return sorted;
   }
public List<Integer> radixSort(List<Integer> data) {
    if (data.isEmpty()) return data;

    // Find the maximum number to determine the number of digits
    int max = data.stream().max(Integer::compareTo).orElse(0);
    int digitPlace = 1; // Initialize digit place (1's place, 10's place, etc.)

    // Perform counting sort for each digit place
    List<Integer> sortedData = new ArrayList<>(data);
    while (max / digitPlace > 0) {
        sortedData = countingSortByDigit(sortedData, digitPlace);
        digitPlace *= 10; // Move to the next digit place
    }

    return sortedData;
}

public List<Integer> quickSort(List<Integer> data) {
        if (data.size() <= 1) return data; // Base case: list is already sorted
        int pivot = data.get(data.size() / 2); // Choose the middle element as pivot

        // Partition the list into less, equal, and greater based on the pivot
        List<Integer> less = data.stream().filter(x -> x < pivot).collect(Collectors.toList());
        List<Integer> equal = data.stream().filter(x -> x == pivot).collect(Collectors.toList());
        List<Integer> greater = data.stream().filter(x -> x > pivot).collect(Collectors.toList());

        // Recursively sort the 'less' and 'greater' lists, and combine them
        return Stream.concat(
                Stream.concat(quickSort(less).stream(), equal.stream()),
                quickSort(greater).stream()
        ).collect(Collectors.toList());
    }

    private List<Integer> countingSortByDigit(List<Integer> data, int digitPlace) {
    int[] count = new int[10]; // Initialize count array for digits 0-9
    List<Integer> output = new ArrayList<>(data.size());

    // Fill the output list with placeholders
    for (int i = 0; i < data.size(); i++) output.add(0);

    // Count the occurrences of each digit in the current digit place
    for (int num : data) {
        int digit = (num / digitPlace) % 10;
        count[digit]++;
    }

    // Update count array to store the cumulative count
    for (int i = 1; i < count.length; i++) {
        count[i] += count[i - 1];
    }

    // Build the output list
    for (int i = data.size() - 1; i >= 0; i--) {
        int num = data.get(i);
        int digit = (num / digitPlace) % 10;
        output.set(count[digit] - 1, num);
        count[digit]--;
    }

    return output;
}
public static void main(String[] args) {
     Trial trial = new Trial();

        SortRequest request = new SortRequest();
        List<Integer> data = List.of(4,5,6,3,2,7,8,4,8,9);
        request.setAlgorithm("radix");
        request.setData(data);
        SortResult result = new SortResult();
        result.setSortedData(trial.radixSort(data));

    System.out.println(result);
    }


}
