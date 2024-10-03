public class Fan {
    private boolean isOn; // Fan's on/off state
    private int speed; // Fan's speed (1 to 5)

    // Constructor: Fan is off by default with speed 0
    public Fan() {
        isOn = false;
        speed = 0;
    }

    // Turn the fan on and set the default speed to 1
    public void turnOn() {
        isOn = true;
        speed = 1; // Default speed
    }

    // Turn the fan off and set the speed to 0
    public void turnOff() {
        isOn = false;
        speed = 0; // Speed is 0 when the fan is off
    }

    // Set the speed of the fan
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    // Check if the fan is on
    public boolean getFanIsOn() {
        return isOn;
    }
}