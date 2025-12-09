package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite required by the final exam.
 * Method names and signatures left exactly as requested.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
        // Reset selected algorithm to ensure tests run independently
        service.setAlgorithm(null);
    }

    @Test
    public void checkInstanceNotNull() {
        // verify that 'service' is not null
        assertNotNull(service, "getInstance() should not return null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();

        // Verify that both 'service' and 'second' refer to the EXACT same object in memory.
        assertSame(service, second, "getInstance() must return the exact same singleton instance");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // make sure no algorithm is selected
        s.setAlgorithm(null);

        // verify correct exception behavior
        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(8);
        }, "generatePassword should throw IllegalStateException when no algorithm is set");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        assertNotNull(p);
        assertEquals(10, p.length(), "Password length must match requested length for basic algorithm");
        assertTrue(p.matches("^[0-9]{10}$"), "Basic algorithm must generate digits only (0-9)");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        assertNotNull(p);
        assertEquals(12, p.length(), "Password length must match requested length for enhanced algorithm");
        assertTrue(p.matches("^[A-Za-z0-9]{12}$"), "Enhanced algorithm must generate only A-Z, a-z, 0-9 characters");
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        assertNotNull(p);
        assertEquals(8, p.length(), "Password length must match requested length for letters algorithm");
        assertTrue(p.matches("^[A-Za-z]{8}$"), "Letters algorithm must generate letters only (A-Z, a-z)");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertTrue(p1.matches("^[0-9]{10}$"), "After setting basic, password should be digits only");

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertTrue(p2.matches("^[A-Za-z]{10}$"), "After setting letters, password should be letters only");

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertTrue(p3.matches("^[A-Za-z0-9]{10}$"), "After setting enhanced, password should be alphanumeric");
    }
}
