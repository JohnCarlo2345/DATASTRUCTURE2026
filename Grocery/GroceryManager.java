package Grocery;

import java.io.*;
import java.util.*;

public class GroceryManager {
    private List<GroceryItem> itemList;
    private Stack<GroceryItem> actionStack;
    private final String FILE_NAME = "grocery_data.txt";

    public GroceryManager() {
        itemList = new ArrayList<>();
        actionStack = new Stack<>();
        loadFromFile(); // Auto-load on start
    }

    // 🔹 ADD ITEM
    public void addItem(GroceryItem item) {
        itemList.add(item);
        actionStack.push(item); // Push to stack
        saveToFile();
        System.out.println("✅ Item added successfully!");
    }

    // 🔹 EDIT ITEM
    public void editItem(String id, String newName, String newCat, double newPrice, int newStock) {
        for (GroceryItem i : itemList) {
            if (i.getItemId().equalsIgnoreCase(id)) {
                // Save old data to stack
                actionStack.push(new GroceryItem(i.getItemId(), i.getItemName(), i.getCategory(), i.getPrice(), i.getStock()));
                
                // Update
                i.setItemName(newName);
                i.setCategory(newCat);
                i.setPrice(newPrice);
                i.setStock(newStock);
                saveToFile();
                System.out.println("✅ Item updated!");
                return;
            }
        }
        System.out.println("❌ Item NOT found!");
    }

    // 🔹 DELETE ITEM
    public void deleteItem(String id) {
        Iterator<GroceryItem> it = itemList.iterator();
        while (it.hasNext()) {
            GroceryItem i = it.next();
            if (i.getItemId().equalsIgnoreCase(id)) {
                actionStack.push(i); // Push deleted to stack
                it.remove();
                saveToFile();
                System.out.println("✅ Item deleted!");
                return;
            }
        }
        System.out.println("❌ Item NOT found!");
    }

    // 🔹 LIST ALL
    public void listAllItems() {
        if (itemList.isEmpty()) {
            System.out.println("📂 No items in inventory.");
            return;
        }
        System.out.println("\n===== GROCERY INVENTORY =====");
        for (GroceryItem i : itemList) {
            System.out.println(i);
        }
    }

    // 🔹 SEARCH BY ID / NAME / CATEGORY
    public void searchItem(String keyword) {
        boolean found = false;
        System.out.println("\n===== SEARCH RESULTS =====");
        for (GroceryItem i : itemList) {
            if (i.getItemId().equalsIgnoreCase(keyword) ||
                i.getItemName().toLowerCase().contains(keyword.toLowerCase()) ||
                i.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(i);
                found = true;
            }
        }
        if (!found) System.out.println("❌ No match found.");
    }

    // 🔹 VIEW STACK HISTORY
    public void viewStackHistory() {
        if (actionStack.isEmpty()) {
            System.out.println("📜 Stack is empty.");
            return;
        }
        System.out.println("\n===== LAST ACTIONS STACK =====");
        for (GroceryItem i : actionStack) {
            System.out.println(i);
        }
    }

    // 🔹 SAVE TO FILE
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (GroceryItem i : itemList) {
                bw.write(i.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving file: " + e.getMessage());
        }
    }

    // 🔹 LOAD FROM FILE
    private void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] part = line.split("\\|");
                if (part.length == 5) {
                    GroceryItem item = new GroceryItem(
                        part[0], part[1], part[2],
                        Double.parseDouble(part[3]),
                        Integer.parseInt(part[4])
                    );
                    itemList.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading file: " + e.getMessage());
        }
    }
}

