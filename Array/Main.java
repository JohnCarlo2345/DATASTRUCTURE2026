package Array;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayManager manager = new ArrayManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== ARRAY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add New Data");
            System.out.println("2. Edit Data");
            System.out.println("3. Delete Data");
            System.out.println("4. List All Data");
            System.out.println("5. Search Data");
            System.out.println("6. Sort by Integer Value (Asc)");
            System.out.println("7. Sort by Decimal Value (Desc)");
            System.out.println("8. Show Total Sum of Values");
            System.out.println("9. View Action Stack");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Integer Value: ");
                    int num = sc.nextInt();
                    System.out.print("Decimal Value: ");
                    double dec = sc.nextDouble();
                    manager.addData(new ArrayData(id, desc, num, dec));
                    break;

                case 2:
                    System.out.print("Enter ID to Edit: ");
                    String eid = sc.nextLine();
                    System.out.print("New Description: ");
                    String ndesc = sc.nextLine();
                    System.out.print("New Integer: ");
                    int nnum = sc.nextInt();
                    System.out.print("New Decimal: ");
                    double ndec = sc.nextDouble();
                    manager.editData(eid, ndesc, nnum, ndec);
                    break;

                case 3:
                    System.out.print("Enter ID to Delete: ");
                    String did = sc.nextLine();
                    manager.deleteData(did);
                    break;

                case 4:
                    manager.listAll();
                    break;

                case 5:
                    System.out.print("Search ID / Description: ");
                    String key = sc.nextLine();
                    manager.searchData(key);
                    break;

                case 6:
                    manager.sortByNumber();
                    break;

                case 7:
                    manager.sortByDecimal();
                    break;

                case 8:
                    manager.showTotalSum();
                    break;

                case 9:
                    manager.viewStack();
                    break;

                case 10:
                    System.out.println("👋 Exiting... Saved all data!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }

        } while (choice != 10);
        sc.close();
    }
}

