package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Password-generation algorithm that produces letters only (A–Z and a–z)
 * using {@link java.util.Random}.
 */
public class LettersAlgorithm implements PasswordAlgorithm {

    private final Random rand = new Random();
    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String generate(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = rand.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(idx));
        }
        return sb.toString();
    }
}
