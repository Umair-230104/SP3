public class Movie extends AMedia {

    public Movie(String name, int releaseYear, String genre, int rating) {
        super(name, releaseYear, genre, rating);
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

    @Override
    public String toString() {
        return super.toString(); // Der bliver retuneret det der står i Amedia's toString også kan tilføje mere på hvis det er nødvendigt
    }
}
