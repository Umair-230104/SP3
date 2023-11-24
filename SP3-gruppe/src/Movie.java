import java.util.ArrayList;
import java.util.Scanner;


public class Movie extends AMedia {
    private ArrayList<String> savedMediaList = new ArrayList<>();


    Scanner scanner = new Scanner(System.in);


    public Movie(String name, String releaseYear, ArrayList<String> genre, double rating) {
        super(name, releaseYear, genre, rating);
    }

//getUser





    @Override
    public String stopMedia() {
        return super.stopMedia();
    }

    @Override
    public ArrayList<String> getGenre() {
        return super.getGenre();
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
    public double getRating() {
        return super.getRating();
    }

    @Override
    public String toString() {
        return
                "Name: " + getName() +
                        "\n Release Year: " + getReleaseYear() +
                        "\n Genre: " + getGenre() +
                        "\n Rating: " + getRating() +
                        "\n";
    }

}

