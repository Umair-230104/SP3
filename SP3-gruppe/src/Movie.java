import java.util.ArrayList;
import java.util.Scanner;

public class Movie extends AMedia {

    public Movie(String name, String releaseYear, ArrayList<String> genre, double rating) {
        super(name, releaseYear, genre, rating);
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
                playMedia();
                break;

            case 2:
                //  saveMedia();
                break;

            case 3:
                // deleteMedia
                break;
            case 4:
                TextUI.getUserInput();
                break;

            default:
                TextUI.displayMessage("Invalid choice, try again");

        }
    }




        @Override
        public String getName () {
            return super.getName();
        }

        @Override
        public String getReleaseYear () {
            return super.getReleaseYear();
        }

        @Override
        public double getRating () {
            return super.getRating();
        }

        @Override
        public String toString () {
            return super.toString(); // Der bliver retuneret det der står i Amedia's toString også kan tilføje mere på hvis det er nødvendigt
        }
    }
