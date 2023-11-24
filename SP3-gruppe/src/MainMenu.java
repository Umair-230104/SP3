import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();

    // Genre
    private ArrayList<GenreList> genres = new ArrayList<>();

    private FileIO io = new FileIO();
    private TextUI ui = new TextUI();
    private ArrayList<User> users;

    User currentUser;

    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> savedMediaList = new ArrayList<>();

    public void setUp() {
        // test user
        ArrayList<String> user = io.readUserData("ListUser.data");
        users = new ArrayList<>();// dette fik det til at virke
        displayMenuOptions();

        chooseMenu();
    }


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


        TextUI.displayMessage("Choose one option");
        TextUI.displayMessage("1. Search Media ");
        TextUI.displayMessage("2. Search Genre ");

        TextUI.getUserInput();

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                searchMedia(movies, series);
                break;
            case 2:
                //System.out.println(genres);
                displayGenres();
                findGenre();
                searchGenre(genres);
                break;
            default:
                TextUI.displayMessage("Invalid choice, try again");
        }
    }


// ---------------------------------------------------------------------------------------------------------------------
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
                //String s = playMedia();
                //TextUI.displayMessage(s);
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

    /*
    public String playMedia() {

        return " Media name: " + getName() + " Release year: " + getReleaseYear()  + "  is playing";
    }

     */

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
// ---------------------------------------------------------------------------------------------------------------------


    // User login og sign up
    public void saveUserTofile() {
        io.saveUserData(users);
        TextUI.displayMessage("User is saved in file");
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void loadUser() {
        ArrayList<String> usersData = io.readUserData("ListUser.data");
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
        currentUser = new User(username, password);

        // newUser blev aldrig gemt i "users" arraylist
        users.add(currentUser);

        TextUI.displayMessage("Sign up completed, you can now log in.");
        logIn();
    }


    //login for existing users
    public void logIn() {
        TextUI.displayMessage("Log in");
        TextUI.displayMessage("Enter your username: ");
        String username = TextUI.getUserInput();

        TextUI.displayMessage("Enter your password: ");
        String password = TextUI.getUserInput();

        //find user
        currentUser = findUser(username, password);

        // check if user exist and password is correct
        if (currentUser != null && currentUser.getPassWord().equals(password) && currentUser.getUserName().equals(username)) {
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

    private User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassWord().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void makeUser(ArrayList<String> userList) {
        if (userList.size() > 0) {

            for (String s : userList) {

                String[] row = s.split(",");
                String userName = row[0];
                String passWord = row[1];

                User u = new User(userName, passWord);
                users.add(u);
            }
        }
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


    // Filme
    private void displayMovies() {
        String s = "\nMovies:\n";

        for (Movie m : movies) {
            //  s += p.toString();
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

    // Serier
    private void displaySeries() {
        String s = "\nSeries:\n";

        for (Series s1 : series) {
            //  s += p.toString();
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

    // Søge Media
    public static void searchMedia(ArrayList<? extends AMedia> movie, ArrayList<? extends AMedia> serie) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TextUI.displayMessage("Please enter the name of a movie or series, if you want to exit, please type 'exit': ");
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
                TextUI.displayMessage(foundMovies.getName() + " is now playing ");

            } else {
                TextUI.displayMessage("Not found");
            }


            if (foundSeries != null) {
                TextUI.displayMessage(foundSeries.getName() + " is now playing ");

            } else {
                TextUI.displayMessage("Not found");
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
                String genreAll = row[0].trim(); // Trim to remove leading/trailing whitespaces

                GenreList gg = new GenreList(genreAll);
                genres.add(gg);
            }
        }
    }

    public static void searchGenre(ArrayList<? extends GenreList> genres) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TextUI.displayMessage("Please enter the name of a genre, if you want to exit, please type 'exit': ");
            String search = scanner.nextLine();

            if (search.equalsIgnoreCase("exit")) {
                break;
            }

            //find the media in lists
            boolean foundGenres = searchGenreList(genres, search);

            //display results
            if (foundGenres) {
                TextUI.displayMessage("Media found");


            } else {
                TextUI.displayMessage("Not found");
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
        TextUI.displayMessage("Write the genre name, you want to search");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        for (Movie m : movies) {
            ArrayList<String> mm = m.getGenre();
            for (String genre : mm) {
                if (userInput.equalsIgnoreCase(genre.trim())) {
                    System.out.println(m);

                    break;
                }
            }
        }

        for (Series s : series) {
            ArrayList<String> ss = s.getGenre();
            for (String genre : ss) {
                if (userInput.equalsIgnoreCase(genre.trim())) {
                    System.out.println(s);

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