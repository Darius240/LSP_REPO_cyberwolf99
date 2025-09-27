package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Writes a list of Products to a CSV file at the provided path.
 */
public class CSVWriter {
    private final Path outputPath;

    public CSVWriter(Path outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Write products to CSV. Creates parent directories if needed.
     *
     * @param products list of products to write (may be empty)
     * @throws IOException on IO error
     */
    public void write(List<Product> products) throws IOException {
        if (Files.notExists(outputPath.getParent())) {
            Files.createDirectories(outputPath.getParent());
        }

        try (BufferedWriter bw = Files.newBufferedWriter(outputPath)) {
            // Header: match the format your Assignment 2 produced.
            bw.write("id,name,category,price,quantity");
            bw.newLine();
            for (Product p : products) {
                bw.write(p.toCsvLine());
                bw.newLine();
            }
            bw.flush();
        }
    }
}
