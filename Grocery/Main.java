package Grocery;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GroceryManager manager = new GroceryManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== GROCERY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add New Item");
            System.out.println("2. Edit Item");
            System.out.println("3. Delete Item");
            System.out.println("4. List All Items");
            System.out.println("5. Search Item");
            System.out.println("6. View Action Stack History");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Item ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Stock: ");
                    int stock = sc.nextInt();
                    manager.addItem(new GroceryItem(id, name, cat, price, stock));
                    break;

                case 2:
                    System.out.print("Enter Item ID to Edit: ");
                    String eid = sc.nextLine();
                    System.out.print("New Name: ");
                    String nName = sc.nextLine();
                    System.out.print("New Category: ");
                    String nCat = sc.nextLine();
                    System.out.print("New Price: ");
                    double nPrice = sc.nextDouble();
                    System.out.print("New Stock: ");
                    int nStock = sc.nextInt();
                    manager.editItem(eid, nName, nCat, nPrice, nStock);
                    break;

                case 3:
                    System.out.print("Enter Item ID to Delete: ");
                    String did = sc.nextLine();
                    manager.deleteItem(did);
                    break;

                case 4:
                    manager.listAllItems();
                    break;

                case 5:
                    System.out.print("Search by ID / Name / Category: ");
                    String key = sc.nextLine();
                    manager.searchItem(key);
                    break;

                case 6:
                    manager.viewStackHistory();
                    break;

                case 7:
                    System.out.println("👋 Exiting... All data saved!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}

