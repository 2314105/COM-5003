public class Bird extends Animal {

    public Bird(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Bird chirps.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Bird's Name: " + name + ", Age: " + age);
    }
}