package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced algorithm that uses {@link java.security.SecureRandom}.
 * Generates passwords containing uppercase letters, lowercase letters, and digits.
 */
public class EnhancedAlgorithm implements PasswordAlgorithm {

    private final SecureRandom secureRandom = new SecureRandom();
    private static final String ALLOWED =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    @Override
    public String generate(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = secureRandom.nextInt(ALLOWED.length());
            sb.append(ALLOWED.charAt(idx));
        }
        return sb.toString();
    }
}
