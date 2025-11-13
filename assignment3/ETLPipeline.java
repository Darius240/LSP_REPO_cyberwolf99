package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Public entry point for the Assignment 3 ETL pipeline.
 *
 * Usage: run with working directory project root so relative paths match
 * the assignment (data/products.csv -> data/transformed_products.csv).
 *
 * The program mirrors the original A2 behavior:
 * - Same input path: data/products.csv
 * - Same output path: data/transformed_products.csv
 * - Same error handling semantics for missing/empty/invalid input.
 */
public class ETLPipeline {

    public static final Path INPUT_PATH = Paths.get("data", "products.csv");
    public static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    /**
     * Main method runs the ETL steps.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        CSVReader reader = new CSVReader(INPUT_PATH);
        try {
            List<Product> products = reader.read();

            // If input file empty -> writer should produce an empty output (with header)
            Transformer transformer = new ProductTransformer();
            List<Product> transformed = transformer.transform(products);

            CSVWriter writer = new CSVWriter(OUTPUT_PATH);
            writer.write(transformed);

            System.out.println("ETL complete. Read " + products.size() + " product(s), wrote " + transformed.size() + " product(s) to " + OUTPUT_PATH);

        } catch (InvalidDataException ide) {
            System.err.println("Data error: " + ide.getMessage());
            // follow same exit semantics as A2 (e.g., exit non-zero if required)
            System.exit(2);
        } catch (IOException ioe) {
            System.err.println("IO error: " + ioe.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(3);
        }
    }
}
