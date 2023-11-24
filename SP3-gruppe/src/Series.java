import java.util.ArrayList;
import java.util.Scanner;

public class Series extends AMedia {
    private ArrayList<String> seasonAndEpisodes;
    private ArrayList<String> savedMediaList = new ArrayList<>();


    public Series(String name, String releaseYear, ArrayList<String> genre, double rating, ArrayList<String> seasonAndEpisodes) {
        super(name, releaseYear, genre, rating);
        this.seasonAndEpisodes = seasonAndEpisodes;

    }


    @Override
    public String stopMedia() {
        return super.stopMedia();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getReleaseYear() {
        return super.getReleaseYear();
    }

    @Override
    public ArrayList<String> getGenre() {
        return super.getGenre();
    }

    @Override
    public double getRating() {
        return super.getRating();
    }

    public ArrayList<String> getSeasonAndEpisodes() {
        return seasonAndEpisodes;
    }

    @Override
    public String toString() {
        return
                "Name: " + getName() +
                        "\n Release Year: " + getReleaseYear() +
                        "\n Genre: " + getGenre() +
                        "\n Seasons and episodes: " + getSeasonAndEpisodes() +
                        "\n Rating: " + getRating() +
                        "\n";
    }

}

