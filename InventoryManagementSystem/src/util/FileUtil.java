package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import exception.FileFormatException;

public class FileUtil {
    
    public static List<String[]> readCSV(String filename) throws IOException, FileFormatException {
        List<String[]> records = new ArrayList<>();
        File file = new File(filename);
        
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return records;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                
                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new FileFormatException("Invalid CSV format at line " + lineNumber);
                }
                records.add(fields);
            }
        }
        
        return records;
    }
    
    public static void writeCSV(String filename, List<String[]> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String[] record : data) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
        }
    }
    
    public static void appendToCSV(String filename, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(data);
            writer.newLine();
        }
    }
}
