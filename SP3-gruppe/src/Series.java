import java.util.ArrayList;
import java.util.Scanner;

public class Series extends AMedia {

    //private int yearFrom;
    //private int yearTo;

    private ArrayList<String> seasonAndEpisodes;
    private ArrayList<String> savedMediaList = new ArrayList<>();
    Scanner scanner= new Scanner(System.in);



    public Series(String name, String releaseYear, ArrayList<String> genre, double rating, ArrayList<String> seasonAndEpisodes) {
        super(name, releaseYear, genre, rating);
        //this.yearFrom = yearFrom;
        this.seasonAndEpisodes = seasonAndEpisodes;
        //this.yearTo = yearTo;
    }


    public void chooseMedia() {
        //Scanner scanner = new Scanner(System.in);
        TextUI.displayMessage("Choose one option");
        TextUI.displayMessage("1. Play media ");
        TextUI.displayMessage("2. Save media ");
        TextUI.displayMessage("3. Delete from saved ");
        TextUI.displayMessage("4. Exit ");
        TextUI.getUserInput();

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                String s = playMedia();
                TextUI.displayMessage(s);
                break;
            case 2:
                saveMediaWatchLater();
                break;
            case 3:
                deleteMediaWatchToLater();
                break;
            case 4:
                ExitMediaWatchLater();
                break;
            default:
                TextUI.displayMessage("Invalid choice, try again");
        }
    }
    private void ExitMediaWatchLater() {
        //Scanner scanner = new Scanner((System.in));
        TextUI.displayMessage("Exit media");
        String mediaName = scanner.next();
    }

    private void saveMediaWatchLater() {
        //Scanner scanner = new Scanner(System.in);
        TextUI.displayMessage("Write the media name you want to save");
        String mediaName = scanner.next();
        savedMediaList.add(mediaName);
        TextUI.displayMessage(mediaName + " is saved in watch to later");
    }

    private void deleteMediaWatchToLater() {
        // Scanner scanner1 = new Scanner(System.in);
        TextUI.displayMessage("Write the media name, you want to delete");
        String mediaName = scanner.next();

        if (savedMediaList.contains(mediaName)) {
            savedMediaList.remove(mediaName);
            TextUI.displayMessage(mediaName + " is deleted from watch to later");
        } else {
            TextUI.displayMessage(mediaName + " is not founded in watch to later");
        }
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
                "\n" ;
    }

}

