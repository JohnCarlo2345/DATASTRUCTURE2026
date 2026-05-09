package MovieRegistration;

public class Movie {
    private String movieId;
    private String title;
    private String genre;
    private String director;
    private int releaseYear;
    private double rating; // 1.0 - 10.0

    // Constructor
    public Movie(String movieId, String title, String genre, String director, int releaseYear, double rating) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Getters & Setters
    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    // Format to save in file
    public String toFileString() {
        return movieId + "|" + title + "|" + genre + "|" + director + "|" + releaseYear + "|" + rating;
    }

    // Display details
    @Override
    public String toString() {
        return "ID: " + movieId +
               " | Title: " + title +
               " | Genre: " + genre +
               " | Director: " + director +
               " | Year: " + releaseYear +
               " | Rating: ⭐ " + String.format("%.1f", rating);
    }
}

