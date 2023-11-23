import java.util.ArrayList;
import java.util.Scanner;


public class Movie extends AMedia {
    private ArrayList<String> savedMediaList = new ArrayList<>();



    public Movie(String name, String releaseYear, ArrayList<String> genre, double rating) {
        super(name, releaseYear, genre, rating);
    }



    public void chooseMedia() {
        Scanner scanner = new Scanner(System.in);
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
                TextUI.getUserInput();
                break;
            default:
                TextUI.displayMessage("Invalid choice, try again");
        }
    }

    private void saveMediaWatchLater() {
        Scanner scanner = new Scanner(System.in);
        TextUI.displayMessage("Write the media name you want to save");
        String mediaName = scanner.next();
        savedMediaList.add(mediaName);
        TextUI.displayMessage(mediaName + " is saved in watch to later");
    }

    private void deleteMediaWatchToLater() {
        Scanner scanner1 = new Scanner(System.in);
        TextUI.displayMessage("Write the media name, you want to delete");
        String mediaName = scanner1.next();

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
                "\n" ;
    }
}

