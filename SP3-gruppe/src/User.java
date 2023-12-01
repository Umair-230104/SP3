import java.util.ArrayList;

public class User {
    private MyList ml;
    private String userName;
    private String passWord;

    private ArrayList<AMedia> watchedMedia = new ArrayList<>();
    private ArrayList <AMedia> savedMedia = new ArrayList<>();



    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.ml = new MyList();
    }

    public void addWatchedMedia(AMedia m){
        watchedMedia.add(m);
    }


    public void addSavedMedia(AMedia m){
        savedMedia.add(m);
    }


    public ArrayList<AMedia> getWatchedMedia() {
        return watchedMedia;
    }

    public ArrayList<AMedia> getSavedMedia() {
        return savedMedia;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


}
