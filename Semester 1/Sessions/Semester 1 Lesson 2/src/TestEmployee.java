public class TestEmployee {
    public static void main(String[] args) {
        // Create an Employee object
        Employee employee1 = new Employee("John Doe", 101, "Software Engineer", 75000.00);

        // Display employee details
        employee1.displayEmployeeDetails();

        // Update employee details using setters
        employee1.setPosition("Senior Software Engineer");
        employee1.setSalary(80000.00);

        // Display updated employee details
        System.out.println("\nUpdated Employee Details:");
        employee1.displayEmployeeDetails();
    }
}