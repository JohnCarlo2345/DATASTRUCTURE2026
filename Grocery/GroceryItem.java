package Grocery;

public class GroceryItem {
    private String itemId;
    private String itemName;
    private String category;
    private double price;
    private int stock;

    // Constructor
    public GroceryItem(String itemId, String itemName, String category, double price, int stock) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters & Setters
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // Format for saving to file
    public String toFileString() {
        return itemId + "|" + itemName + "|" + category + "|" + price + "|" + stock;
    }

    // Display details
    @Override
    public String toString() {
        return "ID: " + itemId + 
               " | Name: " + itemName + 
               " | Category: " + category + 
               " | Price: ₱" + String.format("%.2f", price) + 
               " | Stock: " + stock;
    }
}
