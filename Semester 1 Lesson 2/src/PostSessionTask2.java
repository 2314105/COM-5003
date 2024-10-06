public class PostSessionTask2 {

    // Static inner Employee class
    public static class Employee {
        // Attributes (fields)
        private int id;
        private String name;
        private String position;
        private double salary;

        // Constructor
        public Employee(int id, String name, String position, double salary) {
            this.id = id;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public double getSalary() {
            return salary;
        }

        // Setters
        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        // Method to display employee details
        public void displayEmployeeDetails() {
            System.out.println("Employee ID: " + id);
            System.out.println("Employee Name: " + name);
            System.out.println("Position: " + position);
            System.out.println("Salary: " + salary);
        }
    }

    // Main method
    public static void main(String[] args) {
        // Creating an employee object
        PostSessionTask2.Employee emp1 = new PostSessionTask2.Employee(1, "John Doe", "Software Engineer", 55000);

        // Display employee details
        emp1.displayEmployeeDetails();
    }
}