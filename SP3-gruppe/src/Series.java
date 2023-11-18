public class Series extends AMedia {

    private int yearFrom;
    private int yearTo;
    private String SeasonAndEpisodes;


    public Series(String name, int releaseYear, int yearFrom, int yearTo, String genre, String SeasonAndEpisodes, int rating) {
        super(name, releaseYear, genre, rating);
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
    }


    @Override
    public void playMedia() {
        super.playMedia();
    }

    @Override
    public void stopMedia() {
        super.stopMedia();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getReleaseYear() {
        return super.getReleaseYear();
    }

    @Override
    public String getGenre() {
        return super.getGenre();
    }

    @Override
    public int getRating() {
        return super.getRating();
    }


    public int getYearFrom() {
        return yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public String getSeasonAndEpisodes() {
        return SeasonAndEpisodes;
    }

    @Override
    public String toString() {
        return super.toString();

    }


}

