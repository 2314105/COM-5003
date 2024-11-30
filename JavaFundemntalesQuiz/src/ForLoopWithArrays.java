public class ForLoopWithArrays {
    public static void main(String[] args) {
        /*
        Loop through an array of integers,
        multiply each element by 2, and print the results.
         */
        int[] arr = {5, 10, 15, 20, 25};

        for (int i = 0; i < arr.length; i++) {
            int result = arr[i] * 2;
            System.out.println(result);
        }
    }
}