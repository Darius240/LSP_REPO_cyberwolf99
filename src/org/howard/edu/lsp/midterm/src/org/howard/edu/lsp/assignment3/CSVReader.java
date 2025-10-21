package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for reading a CSV file and producing Product objects.
 * Validates basic format and throws InvalidDataException when required.
 */
public class CSVReader {

    private final Path inputPath;

    /**
     * Construct a CSVReader.
     *
     * @param inputPath path to input CSV (relative or absolute)
     */
    public CSVReader(Path inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Read products from CSV.
     *
     * Expected CSV header (example): id,name,category,price,quantity
     * Adjust parsing if your A2 used different ordering/headers.
     *
     * @return list of parsed Product objects (may be empty)
     * @throws IOException           on IO errors
     * @throws InvalidDataException  when parsing/validation fails for non-empty files
     */
    public List<Product> read() throws IOException, InvalidDataException {
        if (!Files.exists(inputPath)) {
            throw new IOException("Input file not found: " + inputPath.toString());
        }

        List<Product> products = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(inputPath)) {
            String header = br.readLine();
            if (header == null) {
                // Empty file -> return empty list (assignment requires handling)
                return products;
            }

            String line;
            int lineNo = 1;
            while ((line = br.readLine()) != null) {
                lineNo++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }
                String[] parts = line.split(",", -1);
                if (parts.length < 5) {
                    throw new InvalidDataException("Line " + lineNo + " invalid: expected 5 columns, got " + parts.length);
                }
                String id = parts[0].trim();
                String name = parts[1].trim();
                String category = parts[2].trim();

                double price;
                int quantity;
                try {
                    price = Double.parseDouble(parts[3].trim());
                } catch (NumberFormatException nfe) {
                    throw new InvalidDataException("Line " + lineNo + " invalid price: " + parts[3]);
                }
                try {
                    quantity = Integer.parseInt(parts[4].trim());
                } catch (NumberFormatException nfe) {
                    throw new InvalidDataException("Line " + lineNo + " invalid quantity: " + parts[4]);
                }

                products.add(new Product(id, name, category, price, quantity));
            }
        }

        return products;
    }
}
