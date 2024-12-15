import java.time.LocalDateTime;

// Subclass FrozenFood extending FoodItem
public class FrozenFood extends FoodItem {
    private LocalDateTime useBefore; // Use Before date
    private boolean eatRaw;
    private boolean mustDefrost;
    private int storage; // Storage temperature

    // Constructor for FrozenFood
    public FrozenFood(int itemId, String itemName, LocalDateTime useBefore, boolean eatRaw, boolean mustDefrost, int storage) {
        super(itemId, itemName);
        this.useBefore = useBefore;
        this.eatRaw = eatRaw;
        this.mustDefrost = mustDefrost;
        this.storage = storage;
    }

    // Getter for useBefore
    public LocalDateTime getUseBefore() {
        return useBefore;
    }

    // Getter for eatRaw
    public boolean getEatRaw() {
        return eatRaw;
    }

    // Getter for mustDefrost
    public boolean getMustDefrost() {
        return mustDefrost;
    }

    // Getter for storage (could represent temperature in degrees)
    public int getStorage() {
        return storage;
    }
}