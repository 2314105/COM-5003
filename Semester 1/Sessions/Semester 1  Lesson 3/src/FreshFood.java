import java.time.LocalDateTime;

// Subclass FreshFood extending FoodItem
public class FreshFood extends FoodItem {
    private LocalDateTime bbe; // Best Before End
    private boolean eatRaw;
    private String storage; // How to store it?

    // Constructor for FreshFood
    public FreshFood(int itemId, String itemName, LocalDateTime bbe, boolean eatRaw, String storage) {
        super(itemId, itemName);
        this.bbe = bbe;
        this.eatRaw = eatRaw;
        this.storage = storage;
    }

    // Getter for bbe
    public LocalDateTime getBBE() {
        return bbe;
    }

    // Getter for eatRaw
    public boolean getEatRaw() {
        return eatRaw;
    }

    // Getter for storage
    public String getStorage() {
        return storage;
    }
}