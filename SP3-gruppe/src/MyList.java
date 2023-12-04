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
}