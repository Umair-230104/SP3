import java.util.ArrayList;
import java.util.List;

public class MyList {

    private List<String> watchedMediaList;
    private List<String> savedList;

    public MyList() {
        watchedMediaList = new ArrayList<>();
        savedList = new ArrayList<>();
    }


    public void watchedMedia(String title) {
        watchedMediaList.add(title);
        TextUI.displayMessage("Added to Watched List: " + title);
    }

    public void savedMedia(String title) {
        savedList.add(title);
        TextUI.displayMessage("Added to Saved List: " + title);
    }

    public void displayWatchedMedia() {
        TextUI.displayMessage("Watched List:");
        for (String title : watchedMediaList) {
            TextUI.displayMessage("- " + title);
        }
    }

    public void displaySavedMedia() {
        TextUI.displayMessage("Saved List:");
        for (String title : savedList) {
            TextUI.displayMessage("- " + title);
        }
    }

    public List<String> watchedMoviesAndSeries(String title) {
        // Der hvor vi kalder denne metode, skal den sætte en enkelt titel ind ad gangen.
        // Derefter gemmes den titel i en liste som denne metode returnere.
        ArrayList<String> allMediaWatched = new ArrayList<>();

        allMediaWatched.add(title);
        TextUI.displayMessage("Media has been added to watched list");

        return allMediaWatched;
    }

    public List<String> savedMoviesAndSeries(String title) {
        // Der hvor vi kalder denne metode, skal den sætte en enkelt titel ind ad gangen.
        // Derefter gemmes den titel i en liste til at se senere.
        ArrayList<String> allSavedMedia = new ArrayList<>();

        allSavedMedia.add(title);
        TextUI.displayMessage("Media has been added to saved list");

        return allSavedMedia;
    }

  /*  public static void main(String[] args) {
        MyList myList = new MyList();

        // Adding media to watched and saved lists
        myList.watchedMedia("Film 1");
        myList.savedMedia("Film 2");
        myList.watchedMedia("Serie 1");
        myList.savedMedia("Serie 2");


        // Displaying lists
        myList.displayWatchedMedia();
        myList.displaySavedMedia();
    } */
}