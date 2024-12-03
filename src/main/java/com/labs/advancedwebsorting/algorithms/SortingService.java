package com.labs.advancedwebsorting.algorithms;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public abstract class SortingService {

    // Heap Sort implementation
    public List<Integer> heapSort(List<Integer> data) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(data); // Min-Heap
        List<Integer> sorted = new ArrayList<>();
        while (!heap.isEmpty()) {
            sorted.add(heap.poll());
        }
        return sorted;
    }

    // Quick Sort implementation
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

    // Merge Sort implementation
    public List<Integer> mergeSort(List<Integer> data) {
        if (data.size() <= 1) return data; // Base case: list is already sorted

        int mid = data.size() / 2; // Find the midpoint
        List<Integer> left = mergeSort(data.subList(0, mid)); // Recursively sort left half
        List<Integer> right = mergeSort(data.subList(mid, data.size())); // Recursively sort right half

        return merge(left, right); // Merge the two halves
    }

    // Helper function to merge two sorted lists
    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        // Merge two sorted lists into one
        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        // Add remaining elements from left and right lists
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }


    public abstract List<Integer> sort(List<Integer> data);{
    }


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

// Helper function to perform counting sort by digit place
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

}

        
    


