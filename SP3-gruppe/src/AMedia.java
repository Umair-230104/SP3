public abstract class AMedia implements Media {
    private String name;
    private int releaseYear;
    private String genre;
    private int rating;

    public AMedia(String name, int releaseYear, String genre, int rating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
    }


    @Override
    public void playMedia() {

    }

    @Override
    public void stopMedia() {

    }

    public String getName() {
        return name;
    }


    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "\n";
    }

}
