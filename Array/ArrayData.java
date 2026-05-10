package Array;

public class ArrayData {
    private String id;
    private String description;
    private int numberValue;
    private double decimalValue;

    // Constructor
    public ArrayData(String id, String description, int numberValue, double decimalValue) {
        this.id = id;
        this.description = description;
        this.numberValue = numberValue;
        this.decimalValue = decimalValue;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getNumberValue() { return numberValue; }
    public void setNumberValue(int numberValue) { this.numberValue = numberValue; }

    public double getDecimalValue() { return decimalValue; }
    public void setDecimalValue(double decimalValue) { this.decimalValue = decimalValue; }

    // Format for file
    public String toFileString() {
        return id + "|" + description + "|" + numberValue + "|" + decimalValue;
    }

    // Display
    @Override
    public String toString() {
        return "ID: " + id +
               " | Desc: " + description +
               " | Int: " + numberValue +
               " | Double: " + String.format("%.2f", decimalValue);
    }
}

