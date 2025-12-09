package org.howard.edu.lsp.finale.question1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Central service for password generation using selectable algorithms.
 * Implements the Singleton and Strategy design patterns.
 *
 * Required public API:
 *   - {@link #getInstance()}
 *   - {@link #setAlgorithm(String)}
 *   - {@link #generatePassword(int)}
 *
 * Only one instance may exist; algorithms can be swapped at runtime.
 */

/*
PART C - Design Pattern Documentation (Required)

1. Patterns Used:
   - Singleton Pattern: Ensures a single shared instance of PasswordGeneratorService.
   - Strategy Pattern: Each password generation method is a strategy implementing PasswordAlgorithm.
   - Registry Pattern: Algorithms are stored in a map, enabling future expansion without client changes.

2. Why They Fit:
   - Singleton satisfies requirement for a single shared access point.
   - Strategy supports swapping algorithms at runtime, keeping them modular.
   - Registry enables new algorithms to be added without modifying existing client code.

*/

public class PasswordGeneratorService {

    /** Singleton instance */
    private static final PasswordGeneratorService INSTANCE = new PasswordGeneratorService();

    /** Registry of algorithm name → implementation */
    private final Map<String, PasswordAlgorithm> algorithms =
            Collections.synchronizedMap(new HashMap<>());

    /** Currently selected strategy; null when not set */
    private PasswordAlgorithm currentAlgorithm = null;

    /** Private constructor registers the default algorithms. */
    private PasswordGeneratorService() {
        algorithms.put("basic", new BasicAlgorithm());
        algorithms.put("enhanced", new EnhancedAlgorithm());
        algorithms.put("letters", new LettersAlgorithm());
    }

    /**
     * Returns the single shared instance of this service.
     *
     * @return the singleton instance of {@code PasswordGeneratorService}
     */
    public static PasswordGeneratorService getInstance() {
        return INSTANCE;
    }

    /**
     * Selects the password-generation algorithm by name.
     * Passing {@code null} clears the current selection.
     *
     * @param name the algorithm name ("basic", "enhanced", "letters"),
     *             or null to reset
     * @throws IllegalArgumentException if the name is unsupported
     */
    public void setAlgorithm(String name) {
        if (name == null) {
            this.currentAlgorithm = null;
            return;
        }
        PasswordAlgorithm alg = algorithms.get(name);
        if (alg == null) {
            throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }
        this.currentAlgorithm = alg;
    }

    /**
     * Generates a password using the currently selected algorithm.
     *
     * @param length the desired password length
     * @return a generated password string
     * @throws IllegalStateException if no algorithm has been selected
     */
    public String generatePassword(int length) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No password algorithm selected");
        }
        return currentAlgorithm.generate(length);
    }

    /**
     * Registers a new algorithm for future use.
     * Clients may add algorithms without modifying this class.
     *
     * @param name      the name under which the algorithm will be accessible
     * @param algorithm an implementation of {@link PasswordAlgorithm}
     * @throws IllegalArgumentException if name or algorithm are null
     */
    public void registerAlgorithm(String name, PasswordAlgorithm algorithm) {
        if (name == null || algorithm == null) {
            throw new IllegalArgumentException("Name and algorithm must not be null.");
        }
        algorithms.put(name, algorithm);
    }
}
