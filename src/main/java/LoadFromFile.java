package main.java;

import java.io.*;
import java.util.*;

public class LoadFromFile {
    
    
    public static int[] readNumbersFromFile(String filePath) throws IOException {
        List<Integer> numbersList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String numStr : line.split("\\s+")) { 
                    try {
                        numbersList.add(Integer.parseInt(numStr));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid number: " + numStr);
                    }
                }
            }
        }
        
        return numbersList.stream().mapToInt(Integer::intValue).toArray();
    }
}
