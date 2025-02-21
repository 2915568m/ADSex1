package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingAlgorithms
{
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private static final double ALGORITHM_DNF = 10000;

    public static boolean cut_off(double current_time)
    {
        return current_time < ALGORITHM_DNF;
    }

    private static class TimeoutException extends RuntimeException {}

    public static List<Integer> insertionSort(int[] arr)
    {
        long start = System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                return Arrays.asList(0);
            }
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key)
            {
                if (cut_off(System.currentTimeMillis() - start))
                {
                    return Arrays.asList(0);
                }
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arrayToList(arr);
    }

    public static List<Integer> selectionSort(int[] arr)
    {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                return Arrays.asList(0);
            }
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                if (cut_off(System.currentTimeMillis() - start))
                {
                    return Arrays.asList(0);
                }
                if (arr[j] < arr[minIndex])
                {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
        return arrayToList(arr);
    }

    public static List<Integer> shellSort(int[] arr)
    {
        long start = System.currentTimeMillis();
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                return Arrays.asList(0);
            }
            for (int i = gap; i < n; i++)
            {
                if (cut_off(System.currentTimeMillis() - start))
                {
                    return Arrays.asList(0);
                }
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                {
                    if (cut_off(System.currentTimeMillis() - start))
                    {
                        return Arrays.asList(0);
                    }
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
        return arrayToList(arr);
    }

    public static List<Integer> bottomUpMergeSort(int[] arr)
    {
        long start = System.currentTimeMillis();
        int n = arr.length;
        int[] aux = new int[n];
        for (int width = 1; width < n; width = 2 * width)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                return Arrays.asList(0);
            }
            for (int i = 0; i < n; i += 2 * width)
            {
                if (cut_off(System.currentTimeMillis() - start))
                {
                    return Arrays.asList(0);
                }
                int left = i;
                int mid = Math.min(i + width, n);
                int right = Math.min(i + 2 * width, n);
                mergeBottomUp(arr, aux, left, mid, right, start);
            }
            int[] temp = arr;
            arr = aux;
            aux = temp;
        }
        return arrayToList(arr);
    }

    public static List<Integer> mergeSort(int[] arr)
    {
        long start = System.currentTimeMillis();
        try {
            mergeSortHelper(arr, start);
        } catch (TimeoutException e) {
            return Arrays.asList(0);
        }
        return arrayToList(arr);
    }

    private static void mergeSortHelper(int[] arr, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        if (arr.length < 2)
        {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSortHelper(left, start);
        mergeSortHelper(right, start);
        merge(arr, left, right, start);
    }

    public static List<Integer> quickSort(int[] arr, int low, int high)
    {
        long start = (low == 0 && high == arr.length - 1) ? System.currentTimeMillis() : 0;
        try {
            quickSortHelper(arr, low, high, start);
        } catch (TimeoutException e) {
            return Arrays.asList(0);
        }
        return arrayToList(arr);
    }

    private static void quickSortHelper(int[] arr, int low, int high, long start)
    {
        if (start == 0) { start = System.currentTimeMillis(); }
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        if (low < high)
        {
            int pi = partition(arr, low, high, start);
            quickSortHelper(arr, low, pi - 1, start);
            quickSortHelper(arr, pi + 1, high, start);
        }
    }

    public static List<Integer> hybridMergeSort(int[] arr)
    {
        long start = System.currentTimeMillis();
        try {
            hybridMergeSortHelper(arr, 0, arr.length - 1, start);
        } catch (TimeoutException e) {
            return Arrays.asList(0);
        }
        return arrayToList(arr);
    }

    private static void hybridMergeSortHelper(int[] arr, int low, int high, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        if (high - low + 1 <= INSERTION_SORT_THRESHOLD)
        {
            insertionSortRange(arr, low, high, start);
            return;
        }
        int mid = low + (high - low) / 2;
        hybridMergeSortHelper(arr, low, mid, start);
        hybridMergeSortHelper(arr, mid + 1, high, start);
        mergeInPlace(arr, low, mid, high, start);
    }

    public static List<Integer> quickSortMedianOfThree(int[] arr, int low, int high)
    {
        long start = (low == 0 && high == arr.length - 1) ? System.currentTimeMillis() : 0;
        try {
            quickSortMedianOfThreeHelper(arr, low, high, start);
        } catch (TimeoutException e) {
            return Arrays.asList(0);
        }
        return arrayToList(arr);
    }

    private static void quickSortMedianOfThreeHelper(int[] arr, int low, int high, long start)
    {
        if (start == 0) { start = System.currentTimeMillis(); }
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        if (low < high)
        {
            int pi = partitionMedian(arr, low, high, start);
            quickSortMedianOfThreeHelper(arr, low, pi - 1, start);
            quickSortMedianOfThreeHelper(arr, pi + 1, high, start);
        }
    }

    public static List<Integer> quickSortDutchFlag(int[] arr, int low, int high)
    {
        long start = (low == 0 && high == arr.length - 1) ? System.currentTimeMillis() : 0;
        try {
            quickSortDutchFlagHelper(arr, low, high, start);
        } catch (TimeoutException e) {
            return Arrays.asList(0);
        }
        return arrayToList(arr);
    }

    private static void quickSortDutchFlagHelper(int[] arr, int low, int high, long start)
    {
        if (start == 0) { start = System.currentTimeMillis(); }
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        if (low < high)
        {
            int[] pivotRange = partitionDutchFlag(arr, low, high, start);
            quickSortDutchFlagHelper(arr, low, pivotRange[0] - 1, start);
            quickSortDutchFlagHelper(arr, pivotRange[1] + 1, high, start);
        }
    }

    public static List<Integer> quickSortHybrid(int[] arr, int low, int high)
    {
        long start = (low == 0 && high == arr.length - 1) ? System.currentTimeMillis() : 0;
        try {
            quickSortHybridHelper(arr, low, high, start);
        } catch (TimeoutException e) {
            return Arrays.asList(0);
        }
        return arrayToList(arr);
    }

    private static void quickSortHybridHelper(int[] arr, int low, int high, long start)
    {
        if (start == 0) { start = System.currentTimeMillis(); }
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        if (high - low + 1 <= INSERTION_SORT_THRESHOLD)
        {
            insertionSortRange(arr, low, high, start);
            return;
        }
        if (low < high)
        {
            int pi = partition(arr, low, high, start);
            quickSortHybridHelper(arr, low, pi - 1, start);
            quickSortHybridHelper(arr, pi + 1, high, start);
        }
    }

    private static int partition(int[] arr, int low, int high, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            if (arr[j] < pivot)
            {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static int partitionMedian(int[] arr, int low, int high, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        int mid = low + (high - low) / 2;
        if (arr[low] > arr[mid])
        {
            swap(arr, low, mid);
        }
        if (arr[low] > arr[high])
        {
            swap(arr, low, high);
        }
        if (arr[mid] > arr[high])
        {
            swap(arr, mid, high);
        }
        swap(arr, mid, high - 1);
        int pivot = arr[high - 1];
        int i = low;
        int j = high - 1;
        while (true)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            while (arr[++i] < pivot)
            {
                if (cut_off(System.currentTimeMillis() - start))
                {
                    throw new TimeoutException();
                }
            }
            while (arr[--j] > pivot)
            {
                if (cut_off(System.currentTimeMillis() - start))
                {
                    throw new TimeoutException();
                }
            }
            if (i >= j)
            {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, i, high - 1);
        return i;
    }

    private static int[] partitionDutchFlag(int[] arr, int low, int high, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        int pivot = arr[high];
        int lt = low;
        int i = low;
        int gt = high;
        while (i <= gt)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            if (arr[i] < pivot)
            {
                swap(arr, lt, i);
                lt++;
                i++;
            }
            else if (arr[i] > pivot)
            {
                swap(arr, i, gt);
                gt--;
            }
            else
            {
                i++;
            }
        }
        return new int[] { lt, gt };
    }

    private static void merge(int[] arr, int[] left, int[] right, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            arr[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            arr[k++] = left[i++];
        }
        while (j < right.length)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            arr[k++] = right[j++];
        }
    }

    private static void mergeInPlace(int[] arr, int low, int mid, int high, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        int[] left = Arrays.copyOfRange(arr, low, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, high + 1);
        int i = 0, j = 0, k = low;
        while (i < left.length && j < right.length)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            if (left[i] <= right[j])
            {
                arr[k++] = left[i++];
            }
            else
            {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            arr[k++] = left[i++];
        }
        while (j < right.length)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            arr[k++] = right[j++];
        }
    }

    private static void insertionSortRange(int[] arr, int low, int high, long start)
    {
        for (int i = low + 1; i <= high; i++)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                return;
            }
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key)
            {
                if (cut_off(System.currentTimeMillis() - start))
                {
                    return;
                }
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void mergeBottomUp(int[] arr, int[] aux, int left, int mid, int right, long start)
    {
        if (cut_off(System.currentTimeMillis() - start))
        {
            throw new TimeoutException();
        }
        int i = left, j = mid;
        for (int k = left; k < right; k++)
        {
            if (cut_off(System.currentTimeMillis() - start))
            {
                throw new TimeoutException();
            }
            if (i < mid && (j >= right || arr[i] <= arr[j]))
            {
                aux[k] = arr[i++];
            }
            else
            {
                aux[k] = arr[j++];
            }
        }
    }

    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static List<Integer> arrayToList(int[] arr)
    {
        List<Integer> list = new ArrayList<>();
        for (int num : arr)
        {
            list.add(num);
        }
        return list;
    }
}
