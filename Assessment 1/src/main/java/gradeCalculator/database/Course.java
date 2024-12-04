package gradeCalculator.database;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private List<Module> modules;

    public Course(String courseName) {
        this.courseName = courseName;
        this.modules = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public Module findModuleByName(String moduleName) {
        return modules.stream()
                .filter(module -> module.getModuleName().equalsIgnoreCase(moduleName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Course: " + courseName + "\nModules:\n");
        for (Module module : modules) {
            sb.append("  - ").append(module).append("\n");
        }
        return sb.toString();
    }
}