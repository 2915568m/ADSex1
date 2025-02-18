package main.java;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String projectDir = System.getProperty("user.dir");

        System.out.print("enter file name (inside numbers folder)");
        String filename = scanner.nextLine().trim();

        String filePath = projectDir + File.separator + "src/main/numbers" + File.separator + filename;


        int[] arr = LoadFromFile.readNumbersFromFile(filePath);
        System.out.println("Unsorted Array: " + Arrays.toString(arr));
        


        System.out.print(""); //display name of al
        double time1 = System.currentTimeMillis();
        List<Integer> sortedArr = SortingAlgorithms.selectionSort(arr);
        double time2 = System.currentTimeMillis();
        double timeTaken = time2-time1;
        System.out.print(timeTaken + "milliseconds\n");
        System.out.print(sortedArr);


    }
}
    

