import java.util.ArrayList;
import java.util.List;

public class MyList {

    private List<String> watchedList;
    private List<String> savedList;

    public MyList() {
        watchedList = new ArrayList<>();
        savedList = new ArrayList<>();
    }

    public void watchedMedia(String title) {
        watchedList.add(title);
        TextUI.displayMessage("Added to Watched List: " + title);
    }

    public void savedMedia(String title) {
        savedList.add(title);
        TextUI.displayMessage("Added to Saved List: " + title);
    }

    public void displayWatchedMedia() {
        TextUI.displayMessage("Watched List:");
        for (String title : watchedList) {
            TextUI.displayMessage("- " + title);
        }
    }

    public void displaySavedMedia() {
        TextUI.displayMessage("Saved List:");
        for (String title : savedList) {
            TextUI.displayMessage("- " + title);
        }
    }

    public static void main(String[] args) {
        MyList myList = new MyList();

        // Adding media to watched and saved lists
        myList.watchedMedia("Film 1");
        myList.savedMedia("Film 2");
        myList.watchedMedia("Serie 1");
        myList.savedMedia("Serie 2");


        // Displaying lists
        myList.displayWatchedMedia();
        myList.displaySavedMedia();
    }
}

