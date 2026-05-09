package MovieRegistration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MOVIE REGISTRATION SYSTEM =====");
            System.out.println("1. Register New Movie");
            System.out.println("2. Edit Movie Details");
            System.out.println("3. Delete Movie Record");
            System.out.println("4. List All Movies");
            System.out.println("5. Search Movie");
            System.out.println("6. View Action Stack History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Movie ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Enter Director: ");
                    String dir = sc.nextLine();
                    System.out.print("Enter Release Year: ");
                    int year = sc.nextInt();
                    System.out.print("Enter Rating (1.0 - 10.0): ");
                    double rate = sc.nextDouble();
                    manager.addMovie(new Movie(id, title, genre, dir, year, rate));
                    break;

                case 2:
                    System.out.print("Enter Movie ID to Edit: ");
                    String eid = sc.nextLine();
                    System.out.print("New Title: ");
                    String nTitle = sc.nextLine();
                    System.out.print("New Genre: ");
                    String nGenre = sc.nextLine();
                    System.out.print("New Director: ");
                    String nDir = sc.nextLine();
                    System.out.print("New Release Year: ");
                    int nYear = sc.nextInt();
                    System.out.print("New Rating: ");
                    double nRate = sc.nextDouble();
                    manager.editMovie(eid, nTitle, nGenre, nDir, nYear, nRate);
                    break;

                case 3:
                    System.out.print("Enter Movie ID to Delete: ");
                    String did = sc.nextLine();
                    manager.deleteMovie(did);
                    break;

                case 4:
                    manager.listAllMovies();
                    break;

                case 5:
                    System.out.print("Search by ID / Title / Genre / Director: ");
                    String key = sc.nextLine();
                    manager.searchMovie(key);
                    break;

                case 6:
                    manager.viewStackHistory();
                    break;

                case 7:
                    System.out.println("👋 Exiting... All records saved!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}

