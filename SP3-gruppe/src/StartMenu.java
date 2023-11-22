import java.io.File;
import java.util.ArrayList;

public class StartMenu {
    private ArrayList<User> users;

    private FileIO fileIO = new FileIO();


    public StartMenu(ArrayList<User> users) {
        this.users = users;
    }

    // Tilføjet
    public void saveUserTofile() {
        fileIO.saveUserData(users);
        TextUI.displayMessage("User is saved in file");
    }


    //creating a new user
    public void signUp() {

        TextUI.displayMessage("Sign Up");
        TextUI.displayMessage("Enter your username: ");
        String username = TextUI.getUserInput();

        //check if username already exists
        if (isUsernameTaken(username)) {
            TextUI.displayMessage("Username already exists, please try again: ");
            return;
        }

        TextUI.displayMessage("Create password: ");
        String password = TextUI.getUserInput();

        // create a new user and add it to the list

        User newUser = new User(username, password);

        // newUser blev aldrig gemt i "users" arraylist
        users.add(newUser);

        TextUI.displayMessage("Sign up completed, you can now log in.");
        saveUserTofile();

    }

    //login for existing users
    public void logIn() {

        TextUI.displayMessage("Log in");
        TextUI.displayMessage("Enter your username: ");
        String username = TextUI.getUserInput();

        TextUI.displayMessage("Enter your password: ");
        String password = TextUI.getUserInput();

        //find user
        User user = findUser(username, password);

        // check if user exist and password is correct
        if (user != null && user.getPassWord().equals(password)) {
            TextUI.displayMessage("Welcome " + username + "!");
        } else {
            TextUI.displayMessage("Invalid, please try again!");
        }
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Skal vi have en til for at finde kode?
    private User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassWord().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void displayMenuOptions() {
        TextUI.displayMessage("Welcome to the Streaming platform, you now have two options");
        TextUI.displayMessage("1. Log in");
        TextUI.displayMessage("2. Sign up");
        TextUI.displayMessage("Enter your choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());

        switch ((choice)) {
            case 1:
                logIn();
                break;

            case 2:
                signUp();
                break;

            default:
                TextUI.displayMessage("Invalid, please try again");
        }
    }
}
