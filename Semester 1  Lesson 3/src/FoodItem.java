import java.time.LocalDateTime;

public class FoodItem {
    private int itemId;
    private String itemName;

    // Constructor
    public FoodItem(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    // Getter for itemId
    public int getItemId() {
        return itemId;
    }

    // Getter for itemName
    public String getItemName() {
        return itemName;
    }
}