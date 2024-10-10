public class Main {
    public static void main(String[] args) {
        // Create an array of Animal objects
        Animal[] animals = new Animal[3];

        // Store Dog, Cat, and Bird objects in the array
        animals[0] = new Dog("Buddy", 5);
        animals[1] = new Cat("Whiskers", 3);
        animals[2] = new Bird("Tweety", 2);

        // Iterate through the array and call the makeSound() method for each object
        for (Animal animal : animals) {
            animal.makeSound();  // Polymorphism in action
        }
        // Create instances of Car, Bike, and Truck
        Car car = new Car("Toyota", "Red", 2020, 180);
        Bike bike = new Bike("Yamaha", "Blue", 2021, 120);
        Truck truck = new Truck("Volvo", "White", 2019, 100);

        // Call their methods
        System.out.println("Car:");
        car.displayInfo();
        car.fuel();
        car.playMusic();

        System.out.println("\nBike:");
        bike.displayInfo();
        bike.fuel();
        bike.doWheelie();

        System.out.println("\nTruck:");
        truck.displayInfo();
        truck.fuel();
        truck.loadCargo();
        //test
    }
}