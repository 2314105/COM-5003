package model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    private final Validator validator = new Validator();

    @Test
    void testValidatePassMarks() {
        List<Module> modules = List.of(
                new Module("5COM5015", 30, 45),
                new Module("5COM5020", 40, 50)
        );
        assertTrue(validator.validatePassMarks(modules));

        modules = List.of(
                new Module("5COM5015", 30, 35), // Failing mark
                new Module("5COM5020", 40, 50)
        );
        assertFalse(validator.validatePassMarks(modules));
    }

    @Test
    void testValidateCategoricalMarks() {
        List<Module> modules = List.of(
                new Module("5COM5015", 30, 45),
                new Module("5COM5020", 40, 50)
        );
        assertTrue(validator.validateCategoricalMarks(modules));

        modules = List.of(
                new Module("5COM5015", 30, 45.5) // Non-integer mark
        );
        assertFalse(validator.validateCategoricalMarks(modules));
    }

    @Test
    void testValidateCreditsPerLevel() {
        List<Module> modules = List.of(
                new Module("5COM5015", 30, 45),
                new Module("5COM5020", 40, 50),
                new Module("5COM5030", 50, 60)
        );
        assertTrue(validator.validateCreditsPerLevel(modules));

        modules = List.of(
                new Module("5COM5015", 30, 45),
                new Module("5COM5020", 40, 50) // Total credits = 70
        );
        assertFalse(validator.validateCreditsPerLevel(modules));
    }

    @Test
    void testValidateModuleCodes() {
        List<Module> modules = List.of(
                new Module("5COM5015", 30, 45),
                new Module("5COM5020", 40, 50)
        );
        assertTrue(validator.validateModuleCodes(modules, 5));

        modules = List.of(
                new Module("6COM6015", 30, 45) // Module code starts with 6
        );
        assertFalse(validator.validateModuleCodes(modules, 5));
    }
}
