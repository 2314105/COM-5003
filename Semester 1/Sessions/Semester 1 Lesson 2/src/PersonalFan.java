public class PersonalFan {
    private boolean isOn; // Fan's on/off state
    private int speed;    // Fan's speed (0 to 5)

    // Constructor: Fan is off by default with speed 0
    public PersonalFan() {
        isOn = false;
        speed = 0;
    }

    // Method to turn the fan on and set default speed to 1
    public void turnOn() {
        isOn = true;
        speed = 1;
    }

    // Method to turn the fan off
    public void turnOff() {
        isOn = false;
        speed = 0;
    }

    // Set the fan's speed (between 1 and 5)
    public void setSpeed(int newSpeed) {
        if (newSpeed >= 1 && newSpeed <= 5) {
            speed = newSpeed;
            isOn = true;
        } else {
            System.out.println("Invalid speed. Please choose between 1 and 5.");
        }
    }

    // Get the current fan speed
    public int getSpeed() {
        return speed;
    }

    // Check if the fan is on
    public boolean isFanOn() {
        return isOn;
    }
}