package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for all password generation algorithms.
 * Implementations define how password characters are selected.
 */
public interface PasswordAlgorithm {
    /**
     * Generates a password using the algorithm's specific rules.
     *
     * @param length the desired password length
     * @return a generated password string
     */
    String generate(int length);
}
