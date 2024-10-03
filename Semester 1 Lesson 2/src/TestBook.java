public class TestBook {
    public static void main(String[] args) {
        // Create Book objects
        Book book1 = new Book("Clockers", "R Price", "978-0395537619", 22.89, 10);
        Book book2 = new Book("Fan", "D Rhodes", "978-1909807808", 9.92, 5);

        // Display Program Title
        System.out.println("\nBOOK CLASS EXAMPLE");

        // Display initial state
        System.out.println("\nBook 1 Title and Price: " + book1.getTitle() + " " + book1.getPrice());
        System.out.println("Book 2 Title and Quantity: " + book2.getTitle() + " " + book2.getQuantity());

        // Change price for book 1
        book1.setPrice(23);
        System.out.println("\nChanging price of Book 1");
        System.out.println("Book 1 Title and Price: " + book1.getTitle() + " " + book1.getPrice());

        // Change quantity for book 2
        book2.setQuantity(2);
        System.out.println("\nChanging quantity of Book 2");
        System.out.println("Book 2 Title and Quantity: " + book2.getTitle() + " " + book2.getQuantity());
    }
}