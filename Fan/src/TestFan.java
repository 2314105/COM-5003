public class TestFan {
    public static void main(String[] args) {
        // Create a Fan object
        Fan myFan = new Fan();

        // Confirm fan object created
        System.out.println("\nFan object created");

        // Find out if it is on or off
        System.out.println("Is my Fan on? " + myFan.getFanIsOn());

        // Switch on the fan
        System.out.println("\nSwitching Fan on");
        myFan.turnOn();
        System.out.println("Is my Fan on? " + myFan.getFanIsOn());

        // Switch off the fan
        System.out.println("\nSwitching Fan off");
        myFan.turnOff();
        System.out.println("Is my Fan on? " + myFan.getFanIsOn());
    } // end of the main method
} // end of the class