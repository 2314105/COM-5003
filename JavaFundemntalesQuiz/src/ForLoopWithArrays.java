public class ForLoopWithArrays {
    public static void main(String[] args) {
        // Initialize the array of integers
        int[] arr = {5, 10, 15, 20, 25};

        // Loop through the array
        for (int i = 0; i < arr.length; i++) {
            // Multiply each element by 2
            int result = arr[i] * 2;

            // Print the result
            System.out.println(result);
        }
    }
}