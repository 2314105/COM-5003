public class ForLoopWithIfElse {
    public static void main(String[] args) {
        /*
        Loop through numbers from 1 to 20
        checks if they are even and prints is even or is  odd
         */
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println(i + " is Even");
            } else {
                System.out.println(i + " is Odd");
            }
        }

    }
}
