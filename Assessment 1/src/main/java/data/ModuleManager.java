package data;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules;

    // Constructor to initialize the list of modules
    public ModuleManager() {
        modules = new ArrayList<>();
        // Add sample modules (can later be replaced with dynamic data or from a file/database)
        modules.add(new Module("COM5001", 10));
        modules.add(new Module("COM5002", 20));
        modules.add(new Module("COM5003", 20));
        modules.add(new Module("COM5004", 20));
        modules.add(new Module("COM5005", 10));
        modules.add(new Module("COM5006", 20));
        modules.add(new Module("COM5007", 20));
        modules.add(new Module("COM6001", 20));
        modules.add(new Module("COM6002", 40));
        modules.add(new Module("COM6003", 20));
        modules.add(new Module("COM6004", 10));
        modules.add(new Module("COM6005", 10));
        modules.add(new Module("COM6006", 10));
        modules.add(new Module("COM6007", 10));
    }

    // Method to get all modules
    public List<Module> getModules() {
        return modules;
    }

    // Method to get a module by its code
    public Module getModuleByCode(String code) {
        for (Module module : modules) {
            if (module.getCode().equals(code)) {
                return module;
            }
        }
        return null; // Return null if module is not found
    }

    // Method to add a new module
    public void addModule(Module module) {
        modules.add(module);
    }

    // Method to remove a module by code
    public boolean removeModule(String code) {
        return modules.removeIf(module -> module.getCode().equals(code));
    }
}