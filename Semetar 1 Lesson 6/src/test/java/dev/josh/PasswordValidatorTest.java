package dev.josh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    // Tests valid passwords that meet all criteria
    @Test
    void testValidPassword() {
        assertTrue(validator.validate("Pass1@"));
        assertTrue(validator.validate("A1@bcde"));
    }

    // Tests password that is too short
    @Test
    void testTooShortPassword() {
        assertFalse(validator.validate("P1@a"));
    }

    // Tests password that is too long
    @Test
    void testTooLongPassword() {
        assertFalse(validator.validate("Password1@abc"));
    }

    // Tests password that is missing an uppercase letter
    @Test
    void testMissingUppercase() {
        assertFalse(validator.validate("pass1@"));
    }

    // Tests password that is missing a lowercase letter
    @Test
    void testMissingLowercase() {
        assertFalse(validator.validate("PASS1@"));
    }

    // Tests password that is missing a special character
    @Test
    void testMissingSpecialCharacter() {
        assertFalse(validator.validate("Pass12"));
    }

    // Tests password that is missing a digit
    @Test
    void testMissingDigit() {
        assertFalse(validator.validate("Pass@word"));
    }

    // Tests null password
    @Test
    void testNullPassword() {
        assertFalse(validator.validate(null));
    }

    // Tests empty password
    @Test
    void testEmptyPassword() {
        assertFalse(validator.validate(""));
    }
}