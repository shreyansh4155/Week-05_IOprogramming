package com.ioprogramming.basicproblems.studentcsvwriter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class StudentCSVReader {

    public static void readCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String> records = reader.readAll();

            // Check if the CSV file is empty or contains only headers
            if (records == null || records.isEmpty()) {
                System.out.println("CSV file is empty or contains no data.");
                return; // Exit early if no data
            }

            // Print header if available (assuming first row is header)
            String header = records.get(0);
            System.out.println("Student Details:");
            System.out.println("------------------------------");
            printRecord(header); // Print the header row

            // Iterate from the second row (index 1) to skip the header (if present)
            for (int i = 1; i < records.size(); i++) {
                String record = records.get(i);
                printRecord(record);
                System.out.println("------------------------------");
            }

        } catch (IOException | CsvException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // Helper function to print a record nicely formatted
    private static void printRecord(String record) {
        // Handle cases where a record might have fewer columns than expected
        int length = Math.min(record.length, 4); // Assuming max 4 columns (ID, Name, Age, Marks)
        System.out.println("ID: " + (length > 0? record: "N/A"));
        System.out.println("Name: " + (length > 1? record: "N/A"));
        System.out.println("Age: " + (length > 2? record: "N/A"));
        System.out.println("Marks: " + (length > 3? record: "N/A"));
    }


    public static void main(String args) {
        String filePath = "E:/Capg/Week5_IO_Programming/Day1/src/main/resources/Book1.csv"; // Update with actual path
        readCSV(filePath);
    }
}