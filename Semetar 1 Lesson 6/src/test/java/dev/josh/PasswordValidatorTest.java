package dev.josh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void testValidPassword() {
        assertTrue(validator.validate("Pass1@"));
        assertTrue(validator.validate("A1@bcde"));
    }

    @Test
    void testTooShortPassword() {
        assertFalse(validator.validate("P1@a"));
    }

    @Test
    void testTooLongPassword() {
        assertFalse(validator.validate("Password1@abc"));
    }

    @Test
    void testMissingUppercase() {
        assertFalse(validator.validate("pass1@"));
    }

    @Test
    void testMissingLowercase() {
        assertFalse(validator.validate("PASS1@"));
    }

    @Test
    void testMissingSpecialCharacter() {
        assertFalse(validator.validate("Pass12"));
    }

    @Test
    void testMissingDigit() {
        assertFalse(validator.validate("Pass@word"));
    }

    @Test
    void testNullPassword() {
        assertFalse(validator.validate(null));
    }

    @Test
    void testEmptyPassword() {
        assertFalse(validator.validate(""));
    }
}