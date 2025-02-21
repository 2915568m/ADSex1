package main.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> files = new ArrayList<>();
        files.add("bad.txt");
        files.add("dutch.txt");
        files.add("int10.txt");
        files.add("int20k.txt");
        files.add("int50.txt");
        files.add("int100.txt");
        files.add("int500k.txt");
        files.add("int1000.txt");
        files.add("intBig.txt");

        ArrayList<int[]> arraysOfNumbers = new ArrayList<>();
        String projectDir = System.getProperty("user.dir");

        // Load files
        for (String fileName : files) {
            String filePath = projectDir + File.separator + "src/main/numbers" + File.separator + fileName;
            int[] numbers = LoadFromFile.readNumbersFromFile(filePath);
            
            if (numbers.length == 0) {
                System.out.println("Warning: " + fileName + " is empty.");
            }

            arraysOfNumbers.add(numbers);
        }

        // Sorting algorithms to test
        String[] algorithmNames = {
            "Selection Sort", "Insertion Sort", "Shell Sort",
            "Merge Sort", "Bottom-Up Merge Sort", "Hybrid Merge Sort",
            "Quick Sort", "Quick Sort (Median of Three)", "Quick Sort (Dutch Flag)", "Quick Sort (Hybrid)"
        };

        for (int run = 1; run <= 10; run++) {
            System.out.println("Run " + run + ":");

            for (String algorithm : algorithmNames) {
                long totalTime = 0;

                for (int[] originalArray : arraysOfNumbers) {
                    int[] array = Arrays.copyOf(originalArray, originalArray.length);

                    // Debugging: Print size of input array
                    if (run == 1) {
                        System.out.println("  Running " + algorithm + " on array of size " + array.length);
                    }

                    long startTime = System.nanoTime();
                    switch (algorithm) {
                        case "Selection Sort" -> SortingAlgorithms.selectionSort(array);
                        case "Insertion Sort" -> SortingAlgorithms.insertionSort(array);
                        case "Shell Sort" -> SortingAlgorithms.shellSort(array);
                        case "Merge Sort" -> SortingAlgorithms.mergeSort(array);
                        case "Bottom-Up Merge Sort" -> SortingAlgorithms.bottomUpMergeSort(array);
                        case "Hybrid Merge Sort" -> SortingAlgorithms.hybridMergeSort(array);
                        case "Quick Sort" -> SortingAlgorithms.quickSort(array, 0, array.length - 1);
                        case "Quick Sort (Median of Three)" -> SortingAlgorithms.quickSortMedianOfThree(array, 0, array.length - 1);
                        case "Quick Sort (Dutch Flag)" -> SortingAlgorithms.quickSortDutchFlag(array, 0, array.length - 1);
                        case "Quick Sort (Hybrid)" -> SortingAlgorithms.quickSortHybrid(array, 0, array.length - 1);
                    }
                    long endTime = System.nanoTime();
                    totalTime += (endTime - startTime);
                }

                System.out.println("  " + algorithm + " took " + (totalTime / 1_000_000.0) + " milliseconds.");
            }
            System.out.println();
        }
    }
}
