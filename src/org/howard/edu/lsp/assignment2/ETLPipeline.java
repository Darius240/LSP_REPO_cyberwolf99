package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * CSCI 363/540 - Assignment 2
 * ETL Pipeline for CSV files
 * 
 * Reads products.csv from data/, applies transformations, and writes transformed_products.csv to data/.
 */
public class ETLPipeline {

    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";

    public static void main(String[] args) {
        List<String[]> rows = extract(INPUT_FILE);
        if (rows == null) {
            // Missing input file → already handled inside extract()
            return;
        }

        List<String[]> transformed = transform(rows);
        load(transformed, OUTPUT_FILE);

        // Print run summary
        int read = Math.max(0, rows.size() - 1); // exclude header
        int written = Math.max(0, transformed.size() - 1);
        System.out.println("Run Summary:");
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + (read - written));
        System.out.println("Output written to: " + OUTPUT_FILE);
    }

    /**
     * Extract: Read CSV file into list of string arrays
     */
    private static List<String[]> extract(String inputPath) {
        List<String[]> rows = new ArrayList<>();

        File file = new File(inputPath);
        if (!file.exists()) {
            System.err.println("Error: Input file '" + inputPath + "' not found.");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Simple split (fields guaranteed no commas/quotes)
                String[] parts = line.split(",");
                rows.add(parts);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return null;
        }

        return rows;
    }

    /**
     * Transform: Apply rules to data rows (skip header)
     */
    private static List<String[]> transform(List<String[]> rows) {
        List<String[]> transformed = new ArrayList<>();
        if (rows.isEmpty()) {
            return transformed;
        }

        // Add header first
        transformed.add(new String[] {"ProductID","Name","Price","Category","PriceRange"});

        // Process each row (skip header at index 0)
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            if (row.length < 4) {
                // Skip malformed row
                continue;
            }

            try {
                int id = Integer.parseInt(row[0].trim());
                String name = row[1].trim().toUpperCase();
                double originalPrice = Double.parseDouble(row[2].trim());
                String category = row[3].trim();

                double price = originalPrice;

                // (2) Discount if Electronics
                if (category.equalsIgnoreCase("Electronics")) {
                    price = price * 0.9;
                }

                // Round to two decimals (half-up)
                price = round(price, 2);

                // (3) Recategorization if >500 and originally Electronics
                if (price > 500.0 && category.equalsIgnoreCase("Electronics")) {
                    category = "Premium Electronics";
                }

                // (4) PriceRange
                String priceRange;
                if (price <= 10.00) {
                    priceRange = "Low";
                } else if (price <= 100.00) {
                    priceRange = "Medium";
                } else if (price <= 500.00) {
                    priceRange = "High";
                } else {
                    priceRange = "Premium";
                }

                transformed.add(new String[] {
                    String.valueOf(id),
                    name,
                    String.format("%.2f", price),
                    category,
                    priceRange
                });

            } catch (NumberFormatException e) {
                // Skip malformed row
                continue;
            }
        }

        return transformed;
    }

    /**
     * Load: Write transformed rows to output CSV file
     */
    private static void load(List<String[]> rows, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String[] row : rows) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }

    /**
     * Helper: round with HALF_UP
     */
    private static double round(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
