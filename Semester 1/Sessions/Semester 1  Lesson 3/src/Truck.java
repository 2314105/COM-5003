class Truck extends Vehicle {

    public Truck(String brand, String colour, int year, int speed) {
        super(brand, colour, year, speed, 6);  // Trucks usually have 6 tyres
    }

    @Override
    public void fuel() {
        System.out.println("The truck runs on diesel.");
    }

    public void loadCargo() {
        System.out.println("Loading cargo onto the truck.");
    }
}