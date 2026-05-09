package MovieRegistration;

import java.io.*;
import java.util.*;

public class MovieManager {
    private List<Movie> movieList;
    private Stack<Movie> actionStack; // Recommended by teacher
    private final String FILE_NAME = "movie_data.txt";

    public MovieManager() {
        movieList = new ArrayList<>();
        actionStack = new Stack<>();
        loadFromFile(); // Auto load saved data
    }

    // 🔹 ADD MOVIE
    public void addMovie(Movie movie) {
        movieList.add(movie);
        actionStack.push(movie); // Push to stack
        saveToFile();
        System.out.println("✅ Movie registered successfully!");
    }

    // 🔹 EDIT MOVIE
    public void editMovie(String id, String newTitle, String newGenre, String newDirector, int newYear, double newRating) {
        for (Movie m : movieList) {
            if (m.getMovieId().equalsIgnoreCase(id)) {
                // Save old data to stack
                actionStack.push(new Movie(
                    m.getMovieId(), m.getTitle(), m.getGenre(),
                    m.getDirector(), m.getReleaseYear(), m.getRating()
                ));

                // Update new info
                m.setTitle(newTitle);
                m.setGenre(newGenre);
                m.setDirector(newDirector);
                m.setReleaseYear(newYear);
                m.setRating(newRating);
                saveToFile();
                System.out.println("✅ Movie details updated!");
                return;
            }
        }
        System.out.println("❌ Movie NOT found!");
    }

    // 🔹 DELETE MOVIE
    public void deleteMovie(String id) {
        Iterator<Movie> it = movieList.iterator();
        while (it.hasNext()) {
            Movie m = it.next();
            if (m.getMovieId().equalsIgnoreCase(id)) {
                actionStack.push(m); // Push deleted to stack
                it.remove();
                saveToFile();
                System.out.println("✅ Movie removed from record!");
                return;
            }
        }
        System.out.println("❌ Movie NOT found!");
    }

    // 🔹 LIST ALL MOVIES
    public void listAllMovies() {
        if (movieList.isEmpty()) {
            System.out.println("📂 No movies registered yet.");
            return;
        }
        System.out.println("\n===== MOVIE RECORDS =====");
        for (Movie m : movieList) {
            System.out.println(m);
        }
    }

    // 🔹 SEARCH MOVIE (ID / TITLE / GENRE / DIRECTOR)
    public void searchMovie(String keyword) {
        boolean found = false;
        System.out.println("\n===== SEARCH RESULTS =====");
        for (Movie m : movieList) {
            if (m.getMovieId().equalsIgnoreCase(keyword) ||
                m.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                m.getGenre().toLowerCase().contains(keyword.toLowerCase()) ||
                m.getDirector().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(m);
                found = true;
            }
        }
        if (!found) System.out.println("❌ No matching movie found.");
    }

    // 🔹 VIEW STACK HISTORY
    public void viewStackHistory() {
        if (actionStack.isEmpty()) {
            System.out.println("📜 No actions recorded in stack.");
            return;
        }
        System.out.println("\n===== LAST ACTIONS STACK =====");
        for (Movie m : actionStack) {
            System.out.println(m);
        }
    }

    // 🔹 SAVE TO FILE
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Movie m : movieList) {
                bw.write(m.toFileString());
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
                if (part.length == 6) {
                    Movie movie = new Movie(
                        part[0], part[1], part[2], part[3],
                        Integer.parseInt(part[4]),
                        Double.parseDouble(part[5])
                    );
                    movieList.add(movie);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading file: " + e.getMessage());
        }
    }
}

