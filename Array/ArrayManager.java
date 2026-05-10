package Array;

import java.io.*;
import java.util.*;

public class ArrayManager {
    // Using ArrayList (dynamic array) as required
    private List<ArrayData> dataArray;
    private Stack<ArrayData> actionStack;
    private final String FILE_NAME = "array_data.txt";

    public ArrayManager() {
        dataArray = new ArrayList<>();
        actionStack = new Stack<>();
        loadFromFile();
    }

    // 🔹 ADD DATA
    public void addData(ArrayData data) {
        dataArray.add(data);
        actionStack.push(data);
        saveToFile();
        System.out.println("✅ Data added to array!");
    }

    // 🔹 EDIT DATA
    public void editData(String id, String newDesc, int newInt, double newDouble) {
        for (int i = 0; i < dataArray.size(); i++) {
            ArrayData d = dataArray.get(i);
            if (d.getId().equalsIgnoreCase(id)) {
                // Save old state to stack
                actionStack.push(new ArrayData(
                    d.getId(), d.getDescription(),
                    d.getNumberValue(), d.getDecimalValue()
                ));

                // Update
                d.setDescription(newDesc);
                d.setNumberValue(newInt);
                d.setDecimalValue(newDouble);
                saveToFile();
                System.out.println("✅ Data updated!");
                return;
            }
        }
        System.out.println("❌ ID NOT FOUND!");
    }

    // 🔹 DELETE DATA
    public void deleteData(String id) {
        for (int i = 0; i < dataArray.size(); i++) {
            if (dataArray.get(i).getId().equalsIgnoreCase(id)) {
                actionStack.push(dataArray.get(i));
                dataArray.remove(i);
                saveToFile();
                System.out.println("✅ Data deleted from array!");
                return;
            }
        }
        System.out.println("❌ ID NOT FOUND!");
    }

    // 🔹 LIST ALL
    public void listAll() {
        if (dataArray.isEmpty()) {
            System.out.println("📂 Array is empty.");
            return;
        }
        System.out.println("\n===== ARRAY DATA LIST =====");
        for (ArrayData d : dataArray) {
            System.out.println(d);
        }
        System.out.println("Total items: " + dataArray.size());
    }

    // 🔹 SEARCH DATA
    public void searchData(String keyword) {
        boolean found = false;
        System.out.println("\n===== SEARCH RESULT =====");
        for (ArrayData d : dataArray) {
            if (d.getId().equalsIgnoreCase(keyword) ||
                d.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(d);
                found = true;
            }
        }
        if (!found) System.out.println("❌ No match found.");
    }

    // 🔹 SORT ARRAY (by Number Value ASC)
    public void sortByNumber() {
        Collections.sort(dataArray, (a,b) -> a.getNumberValue() - b.getNumberValue());
        saveToFile();
        System.out.println("✅ Array sorted by Number Value (Ascending)!");
    }

    // 🔹 SORT ARRAY (by Decimal Value DESC)
    public void sortByDecimal() {
        Collections.sort(dataArray, (a,b) -> Double.compare(b.getDecimalValue(), a.getDecimalValue()));
        saveToFile();
        System.out.println("✅ Array sorted by Decimal Value (Descending)!");
    }

    // 🔹 GET TOTAL SUM
    public void showTotalSum() {
        int sumInt = 0;
        double sumDouble = 0.0;
        for (ArrayData d : dataArray) {
            sumInt += d.getNumberValue();
            sumDouble += d.getDecimalValue();
        }
        System.out.println("🔢 Total Integer Sum: " + sumInt);
        System.out.println("🔢 Total Decimal Sum: " + String.format("%.2f", sumDouble));
    }

    // 🔹 VIEW STACK
    public void viewStack() {
        if (actionStack.isEmpty()) {
            System.out.println("📜 Stack empty.");
            return;
        }
        System.out.println("\n===== ACTION STACK =====");
        for (ArrayData d : actionStack) {
            System.out.println(d);
        }
    }

    // 🔹 SAVE TO FILE
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (ArrayData d : dataArray) {
                bw.write(d.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Save error: " + e.getMessage());
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
                if (part.length == 4) {
                    ArrayData d = new ArrayData(
                        part[0], part[1],
                        Integer.parseInt(part[2]),
                        Double.parseDouble(part[3])
                    );
                    dataArray.add(d);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Load error: " + e.getMessage());
        }
    }
}

