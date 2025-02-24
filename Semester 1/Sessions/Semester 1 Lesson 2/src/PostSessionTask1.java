import java.util.Scanner;

public class PostSessionTask1 {

    public static void main(String[] args) {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user to input a sentence
        System.out.println("Please enter a sentence: ");
        String sentence = scanner.nextLine();

        // Convert the sentence to uppercase
        String upperCaseSentence = sentence.toUpperCase();
        System.out.println("Sentence in uppercase: " + upperCaseSentence);

        // Count the number of words in the sentence
        String[] words = sentence.split("\\s+"); // Splitting by whitespace
        int wordCount = words.length;
        System.out.println("Number of words: " + wordCount);

        // Reverse the sentence
        String reversedSentence = new StringBuilder(sentence).reverse().toString();
        System.out.println("Reversed sentence: " + reversedSentence);

        // Check if the sentence contains the word "hello" (case-insensitive)
        boolean containsJava = sentence.toLowerCase().contains("hello");
        if (containsJava) {
            System.out.println("The sentence contains the word 'Hello'.");
        } else {
            System.out.println("The sentence does not contain the word 'Hello'.");
        }

        // Replace all spaces with underscores
        String sentenceWithUnderscores = sentence.replace(" ", "_");
        System.out.println("Sentence with underscores: " + sentenceWithUnderscores);
    }
}