class Car extends Vehicle {

    public Car(String brand, String colour, int year, int speed) {
        super(brand, colour, year, speed, 4);  // Cars have 4 tyres
    }

    @Override
    public void fuel() {
        System.out.println("The car runs on petrol or diesel.");
    }

    public void playMusic() {
        System.out.println("Playing music in the car.");
    }
}