public class Book {
    // Fields
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;

    // Constructor
    public Book(String title, String author, String isbn, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    // Read accessors (getters)
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return isbn; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Set accessors (setters)
    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}