package com.labs.advancedwebsorting.algorithms;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class QuickSort extends SortingService {
    @Override
    public List<Integer> sort(List<Integer> data) {
        if (data.size() <= 1) return data;

        int pivot = data.get(data.size() / 2);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();

        for (int num : data) {
            if (num < pivot) left.add(num);
            else if (num > pivot) right.add(num);
            else equal.add(num);
        }

        List<Integer> result = new ArrayList<>();
        result.addAll(sort(left));
        result.addAll(equal);
        result.addAll(sort(right));
        return result;
    }

    @Override
    public List<Integer> bucketSort(List<Integer> data) {
        return List.of();
    }

    @Override
    public List<Integer> radixSort(List<Integer> data) {
        return List.of();
    }

    public void sortWithUserInput() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> data = new ArrayList<>();

        System.out.println("Enter the number of elements to sort: ");
        int n = scanner.nextInt();

        System.out.println("Enter " + n + " integers (separated by spaces):");
        for (int i = 0; i < n; i++) {
            data.add(scanner.nextInt());
        }

        List<Integer> sortedData = sort(data);
        System.out.println("Sorted Data: " + sortedData);
    }
}
