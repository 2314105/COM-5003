public class Animal {
    String name;
    int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    // Generic makeSound method
    public void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}