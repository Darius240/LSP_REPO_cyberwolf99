package org.howard.edu.lsp.assignment3;

/**
 * Exception thrown when the CSV input contains invalid data.
 */
public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
