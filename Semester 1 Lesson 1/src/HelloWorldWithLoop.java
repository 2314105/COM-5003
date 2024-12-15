public class HelloWorldWithLoop {
    public static void main(String[] args) {
        String message = "Hello World, From InelliJ with a loop!";
        System.out.println(message);

        int a = 10;
        int b = 5;

        for ( int i = 1 ; i <= 5;  i++){
            System.out.println("Iteration" + i + " : ");

            int sum = a + b;

            System.out.println("Sum of a and b is " + sum);
            System.out.println("This is iteration " + i + "");
        }
    }
}
