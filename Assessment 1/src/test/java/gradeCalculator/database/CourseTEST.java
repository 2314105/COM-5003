package gradeCalculator.database;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testCourseCreation() {
        Course course = new Course("Computer Science");
        assertEquals("Computer Science", course.getCourseName());
        assertTrue(course.getModules().isEmpty());
    }

    @Test
    void testAddModule() {
        Course course = new Course("Computer Science");
        Module module = new Module("COM5003", 20);

        course.addModule(module);
        List<Module> modules = course.getModules();

        assertEquals(1, modules.size());
        assertEquals(module, modules.get(0));
    }

    @Test
    void testFindModuleByName() {
        Course course = new Course("Computer Science");
        Module module1 = new Module("COM5003", 20);
        Module module2 = new Module("COM5023", 10);

        course.addModule(module1);
        course.addModule(module2);

        Module foundModule = course.findModuleByName("COM5003");
        assertNotNull(foundModule);
        assertEquals(module1, foundModule);

        Module notFoundModule = course.findModuleByName("NON_EXISTENT");
        assertNull(notFoundModule);
    }

    @Test
    void testToString() {
        Course course = new Course("Computer Science");
        course.addModule(new Module("COM5003", 20));
        course.addModule(new Module("COM5023", 10));

        String expected = "Course: Computer Science\nModules:\n" +
                "  - COM5003 (20 credits)\n" +
                "  - COM5023 (10 credits)\n";

        assertEquals(expected, course.toString());
    }
}