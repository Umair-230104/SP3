import java.util.ArrayList;
import java.util.Scanner;

public abstract class AMedia implements Media {
    private String name;
    private String releaseYear;
    private ArrayList<String> genre;
    private double rating;


    public AMedia(String name, String releaseYear, ArrayList<String> genre, double rating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
    }


    @Override
    public String playMedia() {

        return " Media name: " + getName() + " Release year: " + getReleaseYear()  + "  is playing";
    }

    @Override
    public String stopMedia() {

        return getName() + " is stopped";
    }

    public String getName() {
        return name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "\n";
    }

}
