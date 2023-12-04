import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();
    private ArrayList<GenreList> genres = new ArrayList<>();
    private FileIO io = new FileIO();
    private TextUI ui = new TextUI();
    private ArrayList<User> users;
    User currentUser;
    static Scanner scanner = new Scanner(System.in);

    public void setUp() {
        saveAndLoadUserData();
        displayMenuOptions();
        chooseMenu();
    }

    //Save And Load User data
    public void saveAndLoadUserData() {
        // test user
        ArrayList<String> user = io.readUserData("ListUser.data");
        users = new ArrayList<>();
// ---------------------------------------------------------------------------------------------------------------------
        loadUser();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveUserToFile();
            TextUI.displayMessage("Program Is Exiting, User Data Saved.");
        }));
// ---------------------------------------------------------------------------------------------------------------------
    }

    //Menu Options
    public void displayMenuOptions() {
        TextUI.displayMessage("Welcome To The Streaming Platform, You Now Have Two Options");
        TextUI.displayMessage("1. Log In");
        TextUI.displayMessage("2. Sign Up");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch ((choice)) {
            case 1:
                logIn();
                break;
            case 2:
                signUp();
                break;
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
    }

    //Start Up Menu Options
    public void chooseMenu() {
        // test movies
        ArrayList<String> movie = io.readMovieData("movies.data");
        makeMovies(movie);

        // test series
        ArrayList<String> serie = io.readSeriesData("series.data");
        makeSeries(serie);

        // test searchMedia
        ArrayList<String> genre = io.readGenreData("GenreList.data");
        makeGenre(genre);

        TextUI.displayMessage("Choose One Option");
        TextUI.displayMessage("1. Search Media ");
        TextUI.displayMessage("2. Search Genre ");
        TextUI.displayMessage("3. See Your Watched List ");
        TextUI.displayMessage("4. See Your Saved List");
        TextUI.displayMessage("5. See Media By Rating");
        TextUI.displayMessage("6. See Media By Release Date After 2000 ");
        TextUI.displayMessage("7. Search Media By Release Date Before 2000 ");
        TextUI.displayMessage("8. Exit Program");
        TextUI.displayMessage("");
        TextUI.getUserInput();
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                searchMedia(movies, series);
                break;
            case 2:
                displayGenres();
                findGenre();
                searchGenre(genres);
                chooseMenu();
                break;
            case 3:
                displayWatchedMedia();
                chooseMenu();
                break;
            case 4:
                displaySavedMedia();
                chooseMenu();
                break;
            case 5:
                searchMediaByRating();
                chooseMenu();
                break;
            case 6:
                searchMediaByYearAfter();
                chooseMenu();
                break;
            case 7:
                searchMediaByYearBefore();
                chooseMenu();
                break;
            case 8:
                System.exit(0);
                break;
            default:
                TextUI.displayMessage("Invalid Choice, Try Again");
        }
    }

    //Choose Media Options
    public void chooseMedia(AMedia choosingMedia) {
        TextUI.displayMessage("Choose one option");
        TextUI.displayMessage("1. Play Media ");
        TextUI.displayMessage("2. Save Media ");
        TextUI.displayMessage("3. Delete From Saved ");
        TextUI.displayMessage("4. Exit ");
        TextUI.getUserInput();
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                String s = playMedia(choosingMedia);
                TextUI.displayMessage(s);
                chooseMenu();
                break;
            case 2:
                saveMediaWatchLater(choosingMedia);
                chooseMenu();
                break;
            case 3:
                deleteMediaWatchToLater(choosingMedia);
                chooseMenu();
                break;
            case 4:
                ExitMedia();
                break;
            default:
                TextUI.displayMessage("Invalid Choice, Try Again");
        }
    }

    public String playMedia(AMedia cm) {
        currentUser.addWatchedMedia(cm);
        return "Media Name: " + cm.getName()
                + "\nRelease:" + cm.getReleaseYear()
                + "\n" + cm.getName() + " Is Playing";
    }

    private void saveMediaWatchLater(AMedia cm) {
        TextUI.displayMessage("Write The Media Name You Want To Save");
        String mediaName = scanner.next();
        ArrayList<AMedia> savedMedia = currentUser.getSavedMedia();
        currentUser.addSavedMedia(cm);
        TextUI.displayMessage(mediaName + " Is Saved In Watch To Later");
    }

    private void deleteMediaWatchToLater(AMedia cm) {
        TextUI.displayMessage("Write The Media Name, You Want To Delete");
        String mediaName = scanner.next();
        ArrayList<AMedia> savedMedia = currentUser.getSavedMedia();
        if (savedMedia.contains(cm)) {
            savedMedia.remove(cm);
            TextUI.displayMessage(mediaName + " Is Not Founded In Watch To Later");
        } else {
            TextUI.displayMessage(mediaName + " Is Not Founded In Watch To Later");
        }
    }

    private void displayWatchedMedia() {
        ArrayList<AMedia> watchedMedia = currentUser.getWatchedMedia();
        System.out.println(watchedMedia);
    }

    private void displaySavedMedia() {
        ArrayList<AMedia> savedMedia = currentUser.getSavedMedia();
        System.out.println(savedMedia);
    }

    private void ExitMedia() {
        chooseMenu();

    }

    //Extra Features
    public void searchMediaByRating() {
        double minRating = 8.5;
        TextUI.displayMessage("Searching For Media With A Rating More Than " + minRating);
        for (Movie movie : movies) {
            if (movie.getRating() > minRating) {
                TextUI.displayMessage(movie.toString());
            }
        }
        for (Series series : series) {
            if (series.getRating() > minRating) {
                TextUI.displayMessage(series.toString());
            }
        }
    }

    public void searchMediaByYearAfter() {
        int minYear = 2000;
        TextUI.displayMessage("Searching For Media With A Year After " + minYear);
        for (Movie movie : movies) {
            if (Integer.parseInt(movie.getReleaseYear().trim().split("-")[0]) > minYear) {
                TextUI.displayMessage(movie.toString());
            }
        }
        for (Series series : series) {
            if (Integer.parseInt(series.getReleaseYear().trim().split("-")[0]) > minYear) {
                TextUI.displayMessage(series.toString());
            }
        }
    }

    public void searchMediaByYearBefore() {
        int minYear = 2000;
        TextUI.displayMessage("Searching For Media With A Year Before " + minYear);
        for (Movie movie : movies) {
            if (Integer.parseInt(movie.getReleaseYear().trim().split("-")[0]) < minYear) {
                TextUI.displayMessage(movie.toString());
            }
        }
        for (Series series : series) {
            if (Integer.parseInt(series.getReleaseYear().trim().split("-")[0]) < minYear) {
                TextUI.displayMessage(series.toString());
            }
        }
    }

    //User Login and Sign Up
    public void saveUserToFile() {
        io.saveUserData(users);
        TextUI.displayMessage("User Is Saved In File");
    }

    public void loadUser() {
        users = new ArrayList<>();
        ArrayList<String> userData = io.readUserData("ListUser.data");
        makeUser(userData);
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void signUp() {
        TextUI.displayMessage("Sign Up");
        TextUI.displayMessage("Enter your username: ");
        String username = TextUI.getUserInput();

        //check if username already exists
        if (isUsernameTaken(username)) {
            TextUI.displayMessage("Username already exists, please try again: ");
            return;
        }

        //create password
        String password;
        while (true) {
            TextUI.displayMessage("Password must contain at least one uppercase letter and two numbers. Create password: ");
            password = TextUI.getUserInput();

            if (isValidPassword(password)) {
                break;
            } else {
                TextUI.displayMessage("Invalid password. Please try again.");
            }
        }
        // create a new user and add it to the list
        currentUser = new User(username, password); // nyt
        // newUser blev aldrig gemt i "users" arraylist
        users.add(currentUser);
        TextUI.displayMessage("Sign up completed, you can now log in.");
        logIn();
    }

    private boolean isValidPassword(String password){
        //pattern for at least 1 uppercase and 2 numbers
        String uppercaseRegex= ".*[A-Z].*";
        String numbersRegex = ".*\\d.*";
        Pattern uppercasePattern=Pattern.compile(uppercaseRegex);
        Pattern numbersPattern= Pattern.compile(numbersRegex);
        //using pattern create matches for password
        Matcher uppercaseMatcher=uppercasePattern.matcher(password);
        Matcher numbersMatcher= numbersPattern.matcher(password);
        return  uppercaseMatcher.matches()&& numbersMatcher.matches();
    }

    public void logIn() {
        TextUI.displayMessage("Log In");
        TextUI.displayMessage("Enter Your Username: ");
        String username = TextUI.getUserInput();
        TextUI.displayMessage("Enter Your Password: ");
        String password = TextUI.getUserInput();
        if (authenticateUser(username, password)) {
            TextUI.displayMessage("Welcome " + username + "!");
        } else {
            TextUI.displayMessage("Invalid Username Or Password, Please Try Again!");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Find user
        currentUser = findUser(username, password);
        // Check if user exists and password is correct
        return currentUser != null && currentUser.getPassWord().equals(password) && currentUser.getUserName().equals(username);
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassWord().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void makeUser(ArrayList<String> userList) {
        if (userList.size() >= 0) {
            for (String s : userList) {
                String[] row = s.split(",");
                String userName = row[0];
                String passWord = row[1];
                User u = new User(userName, passWord);
                users.add(u);
            }
        }
    }

    //Movies
    private void displayMovies() {
        String s = "\nMovies:\n";
        for (Movie m : movies) {
            s = s.concat(m.toString() + "\n");
        }
        TextUI.displayMessage(s);
    }

    private void makeMovies(ArrayList<String> moviesList) {
        if (moviesList.size() > 0) {
            for (String s : moviesList) {
                String[] row = s.split(";");
                String name = row[0];
                String releaseYear = row[1];
                String genre = row[2];
                String[] movieGenre = genre.split(",");
                ArrayList<String> aGenre = new ArrayList<>();
                for (String s1 : movieGenre) {
                    aGenre.add(s1);
                }
                String r = row[3].replace(',', '.');
                double rating = Double.parseDouble(r.trim());
                Movie mm = new Movie(name, releaseYear, aGenre, rating);
                movies.add(mm);
            }
        }
    }

    //Series
    private void displaySeries() {
        String s = "\nSeries:\n";
        for (Series s1 : series) {
            s = s.concat(s1.toString() + "\n");
        }
        TextUI.displayMessage(s);
    }

    private void makeSeries(ArrayList<String> seriesList) {
        if (seriesList.size() > 0) {
            for (String s : seriesList) {
                String[] row = s.split(";");
                String name = row[0];
                String releaseYear = row[1];
                String genre = row[2];
                String[] seriesGenre = genre.split(", ");
                ArrayList<String> aGenre = new ArrayList<>();
                for (String s1 : seriesGenre) {
                    aGenre.add(s1);
                }
                String r = row[3].replace(',', '.');
                double rating = Double.parseDouble(r.trim());
                String seasonAndEpisodes = row[4];
                String[] seriesSeason = seasonAndEpisodes.split(",");
                ArrayList<String> aSeasonAndEpisodes = new ArrayList<>();
                for (String s2 : seriesSeason) {
                    aSeasonAndEpisodes.add(s2);
                }
                Series ss = new Series(name, releaseYear, aGenre, rating, aSeasonAndEpisodes);
                series.add(ss);
            }
        }
    }

    //Search Media
    public void searchMedia(ArrayList<? extends AMedia> movie, ArrayList<? extends AMedia> serie) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            TextUI.displayMessage("Please Enter The Name Of A Movie Or Series, If You Want To Exit, Please Type 'Exit': ");
            String search = scanner.nextLine();
            //end loop if user types exit
            if (search.equalsIgnoreCase("exit")) {
                break;
            }
            //find the media in lists
            AMedia foundMovies = searchList(movie, search);
            AMedia foundSeries = searchList(serie, search);
            //display results
            if (foundMovies != null) {
                TextUI.displayMessage(foundMovies.getName() + " Is Now Playing ");
                chooseMedia(foundMovies);
            } else {
                TextUI.displayMessage("Not Found");
            }
            if (foundSeries != null) {
                TextUI.displayMessage(foundSeries.getName() + " Is Now Playing ");
                chooseMedia(foundSeries);
            } else {
                TextUI.displayMessage("Not Found");
            }
        }
        scanner.close();
    }

    private static AMedia searchList(ArrayList<? extends AMedia> list, String searchTerm) {
        for (AMedia amedia : list) {
            if (amedia.getName().equalsIgnoreCase(searchTerm)) {
                TextUI.displayMessage("Found: " + amedia);
                return amedia;
            }
        }
        return null;
    }

    // Genre
    private void displayGenres() {
        TextUI.displayMessage("Genres:");
        for (GenreList genre : genres) {
            TextUI.displayMessage(genre.getGenreAll());
        }
    }

    private void makeGenre(ArrayList<String> genreList) {
        if (genreList.size() > 0) {
            for (String s : genreList) {
                String[] row = s.split(";");
                String genreAll = row[0].trim();
                GenreList gg = new GenreList(genreAll);
                genres.add(gg);
            }
        }
    }

    public void searchGenre(ArrayList<? extends GenreList> genres) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            chooseMenu();
            TextUI.displayMessage("Please Enter The Name Of A Genre, If You Want To Exit, Please Type 'Exit': ");
            String search = scanner.nextLine();
            if (search.equalsIgnoreCase("exit")) {
                break;
            }
            //find the media in lists
            boolean foundGenres = searchGenreList(genres, search);
            //display results
            if (foundGenres) {
                TextUI.displayMessage("Media Found");
            } else {
                TextUI.displayMessage("Not Found");
            }
        }
        scanner.close();
    }

    private static boolean searchGenreList(ArrayList<? extends GenreList> list, String searchTerm) {
        for (GenreList genreList : list) {
            if (genreList.getGenreAll().equals(searchTerm)) {
                TextUI.displayMessage("Found: " + genreList);
                return true;
            }
        }
        return false;
    }

    public void findGenre() {
        TextUI.displayMessage("Write The Genre Name, You Want To Search");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        for (Movie m : movies) {
            ArrayList<String> mm = m.getGenre();
            for (String genre : mm) {
                if (userInput.equalsIgnoreCase(genre.trim())) {
                    System.out.println(m);
                    chooseMedia(m);
                    break;
                }
            }
        }

        for (Series s : series) {
            ArrayList<String> ss = s.getGenre();
            for (String genre : ss) {
                if (userInput.equalsIgnoreCase(genre.trim())) {
                    System.out.println(s);
                    chooseMedia(s);
                    break;
                }
            }
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Series> getSeries() {
        return series;
    }

    @Override
    public String toString() {
        return toString();
    }
}