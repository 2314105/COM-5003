class Bike extends Vehicle {

    public Bike(String brand, String colour, int year, int speed) {
        super(brand, colour, year, speed, 2);  // Bikes have 2 tyres
    }

    @Override
    public void fuel() {
        System.out.println("The bike runs on petrol or is electric.");
    }

    public void doWheelie() {
        System.out.println("The bike does a wheelie!");
    }
}