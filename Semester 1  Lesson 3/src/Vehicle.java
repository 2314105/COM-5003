public class Vehicle {
    String brand;
    String colour;
    int year;
    int speed;
    int tyres;

    public Vehicle(String brand, String colour, int year, int speed, int tyres) {
        this.brand = brand;
        this.colour = colour;
        this.year = year;
        this.speed = speed;
        this.tyres = tyres;
    }

    public void displayInfo() {
        System.out.println("\nBrand: " + brand + "\nColour: " + colour + "\nYear: " + year + "\nSpeed: " + speed + "\nTyres: " + tyres);
    }

    public void fuel() {
        System.out.println("The vehicle takes fuel");
    }

}
