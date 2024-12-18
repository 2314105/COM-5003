package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectLevel6CalculatorTest {

    private DirectLevel6Calculator calculator;

    @BeforeEach
    void setUp() {
        AverageCalculator averageCalculator = new AverageCalculator();
        MarkClassifier markClassifier = new MarkClassifier();
        calculator = new DirectLevel6Calculator(averageCalculator, markClassifier);
    }

    @Test
    void testStudentDClassification() {
        // Student D modules
        List<Module> modules = List.of(
                new Module("JOU6006", 60, 58),
                new Module("JOU6063", 30, 58),
                new Module("JOU6093", 30, 68)
        );

        String expectedOutput = """
                Method C - Average 1 L6: 60.50 (2.1)
                Method D - Profile Mark Classification: 2.2
                Resulting Classification: 2.1
                """;

        String actualOutput = calculator.calculateFinalClassification(modules);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testStudentEClassification() {
        // Student E modules
        List<Module> modules = List.of(
                new Module("SHN6033", 30, 68),
                new Module("SHN6053", 30, 65),
                new Module("SHN6073", 30, 62),
                new Module("SHN6143", 30, 42)
        );

        String expectedOutput = """
                Method C - Average 1 L6: 59.25 (2.2)
                Method D - Profile Mark Classification: 2.1
                Resulting Classification: 2.1
                """;

        String actualOutput = calculator.calculateFinalClassification(modules);

        assertEquals(expectedOutput, actualOutput);
    }
}
