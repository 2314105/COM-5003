public class Dog extends Animal {

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Dog barks.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Dog's Name: " + name + ", Age: " + age);
    }
}