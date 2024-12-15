import java.util.Scanner;

public class SentenceOperations {
    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input a sentence
        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        // Display the original sentence
        System.out.println("\nOriginal Sentence: " + sentence);

        // 1. Convert the sentence to all uppercase letters
        String upperCaseSentence = sentence.toUpperCase();
        System.out.println("\nUppercase Sentence: " + upperCaseSentence);

        // 2. Count the number of words in the sentence
        String[] words = sentence.split(" ");
        int wordCount = words.length;
        System.out.println("\nNumber of Words: " + wordCount);

        // 3. Reverse the entire sentence
        String reversedSentence = new StringBuilder(sentence).reverse().toString();
        System.out.println("\nReversed Sentence: " + reversedSentence);

        // 4. Check if the sentence contains the word "Java" (case-insensitive)
        boolean containsJava = sentence.toLowerCase().contains("java");
        System.out.println("\nContains 'Java' (case-insensitive): " + containsJava);

        // 5. Replace all spaces in the sentence with underscores
        String modifiedSentence = sentence.replace(" ", "_");
        System.out.println("\nModified Sentence (Spaces replaced with underscores): " + modifiedSentence);

        // Close the scanner
        scanner.close();
    }
}