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


    public void setUp() {
        // For film

        // test moives
        ArrayList<String> movie = io.readMovieData("movies.data");
        makeMovies(movie);


        // test series
        ArrayList<String> serie = io.readSeriesData("series.data");
        makeSeries(serie);


        //searchMedia(movies, series);

        // test user
        ArrayList<String> user = io.readUserData("ListUser.data");
        users = new ArrayList<>();// dette fik det til at virke

        //displayMenuOptions();

        // test genre
        ArrayList<String> genre = io.readGenreData("GenreList.data");
        makeGenre(genre);
        // System.out.println(genres);
        //displayGenres();
        findGenre();


        //searchGenre(genres);
    }


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
        User newUser = new User(username, password);

        // newUser blev aldrig gemt i "users" arraylist
        users.add(newUser);

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
        User user = findUser(username, password);

        // check if user exist and password is correct
        if (user != null && user.getPassWord().equals(password) && user.getUserName().equals(username)) {
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

                // debug
                System.out.println("Series: " + name + " Genres: " + aGenre);
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

                // debug
                System.out.println("Series: " + name + " Genres: " + aGenre);
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
            boolean foundMovies = searchList(movie, search);
            boolean foundSeries = searchList(serie, search);

            //display results
            if (foundMovies || foundSeries) {
                TextUI.displayMessage("Media found");
            } else {
                TextUI.displayMessage("Not found");
            }


        }
        scanner.close();
    }

    private static boolean searchList(ArrayList<? extends AMedia> list, String searchTerm) {
        for (AMedia amedia : list) {
            if (amedia.getName().equalsIgnoreCase(searchTerm)) {
                TextUI.displayMessage("Found: " + amedia);
                return true;
            }
        }
        return false;
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
