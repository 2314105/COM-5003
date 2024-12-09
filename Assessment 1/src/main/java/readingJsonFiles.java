import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class Module {
    public String moduleCode;
    public int credits;
}

class Course {
    public String name;
    public String level;
    public List<Module> modules;
}

class Courses {
    public List<Course> courses;
}

public class readingJsonFiles {
    public static void main(String[] args) throws IOException {
        // Initialize Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Load the JSON file from the resources folder
        InputStream inputStream = readingJsonFiles.class.getClassLoader().getResourceAsStream("courses.json");

        if (inputStream == null) {
            System.out.println("File not found in resources folder.");
            return;
        }

        // Read the JSON file into a Courses object
        Courses courses = objectMapper.readValue(inputStream, Courses.class);

        // Search for course by name and level
        searchCourseByLevel(courses.courses, "Computer Science", "Level 6");
    }

    // Search function
    public static void searchCourseByLevel(List<Course> courses, String courseName, String level) {
        for (Course course : courses) {
            if (course.name.equalsIgnoreCase(courseName) && course.level.equalsIgnoreCase(level)) {
                System.out.println("Course: " + course.name + " | Level: " + course.level);
                System.out.println("Modules:");
                for (Module module : course.modules) {
                    System.out.println("  Module Code: " + module.moduleCode + " | Credits: " + module.credits);
                }
                return;
            }
        }
        System.out.println("Course or Level not found.");
    }
}