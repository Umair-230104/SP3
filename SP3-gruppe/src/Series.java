import java.util.ArrayList;

public class Series extends AMedia {

    //private int yearFrom;
    //private int yearTo;
    private String seasonAndEpisodes;


    public Series(String name, String releaseYear, ArrayList<String> genre, String seasonAndEpisodes, double rating) {
        super(name, releaseYear, genre, rating);
        //this.yearFrom = yearFrom;
        //this.yearTo = yearTo;
    }


    @Override
    public String playMedia() {
        return super.playMedia();
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

/*
    public int getYearFrom() {
        return yearFrom;
    }
*/

 /*
    public int getYearTo() {
        return yearTo;
    }
*/

    public String getSeasonAndEpisodes() {
        return seasonAndEpisodes;
    }

    @Override
    public String toString() {
        return super.toString();

    }

}

