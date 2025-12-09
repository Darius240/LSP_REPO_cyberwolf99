package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password-generation algorithm using {@link java.util.Random}.
 * Produces digits only (0–9).
 */
public class BasicAlgorithm implements PasswordAlgorithm {

    private final Random rand = new Random();
    private static final String DIGITS = "0123456789";

    @Override
    public String generate(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = rand.nextInt(DIGITS.length());
            sb.append(DIGITS.charAt(idx));
        }
        return sb.toString();
    }
}
