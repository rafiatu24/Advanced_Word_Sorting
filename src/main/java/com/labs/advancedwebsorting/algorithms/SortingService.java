package com.labs.advancedwebsorting.algorithms;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public abstract class SortingAlgorithm {

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


    public abstract List<Integer> sort(List<Integer> data);
}

