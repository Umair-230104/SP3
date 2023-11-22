import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();
    private FileIO io = new FileIO();
    private TextUI ui = new TextUI();
    private ArrayList<User> users;
    private ArrayList<Movie> movieGenre = new ArrayList<>();
    private ArrayList<Series> seriesGenre = new ArrayList<>();


    public void setUp() {

        // For film
       //ArrayList<String> movie = io.readMovieData("movies.data");

        //System.out.println(movie.size());
        //System.out.println(movie.get(0));

        // test makeMoives
       // System.out.println(this.movies.size());
       // makeMovies(movie);
       // System.out.println(movies);


        // test makeSeries
        ArrayList<String> serie = io.readSeriesData("series.data");
        //System.out.println(this.series.size());
        //makeSeries(serie);
        //System.out.println(series);

        //searchMedia(movies, series);

        ArrayList<String> user = io.readUserData("ListUser.data");

        //StartMenu startMenu = new StartMenu(user);
        //System.out.println(users);


        users = new ArrayList<>();// dette fik det til at virke

       // StartMenu startMenu = new StartMenu(users);


        // Display the menu options
        displayMenuOptions();
        //startMenu.signUp();
        //startMenu.logIn();


    }

    public void saveUserTofile() {
        io.saveUserData(users);
        TextUI.displayMessage("User is saved in file");
    }

    public void addUser (User u){
        users.add(u);
    }

    public void loadUser (){
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



    private void displayMovies() {
        String s ="\nMovies:\n";

        for (Movie m : movies) {
            //  s += p.toString();
            s = s.concat(m.toString()+"\n");
        }

        TextUI.displayMessage(s);
    }

    private void makeMovies(ArrayList<String> moviesList) { // lav det samme for series
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

                //registerMovies(name, releaseYear, aGenre, rating);

            }
        }
    }


    /*
    private void registerMovies(String name, String releaseYear, ArrayList<String> genre, double rating) {
        Movie mm = new Movie(name, releaseYear, casgenre, rating);
        movies.add(mm);
    }

     */

    private void makeSeries(ArrayList<String> seriesList) {
        if (seriesList.size() > 0) {

            for (String s : seriesList) {

                String[] row = s.split(";");
                String name = row[0];

                // int releaseYear = Integer.parseInt(row[1].trim());

                String releaseYear = row[1];

                // De her int skal ændres fordi på plads 2 er der to tal med "-" og det er ikke to tal på to pladser i arraylist
                //int yearFrom = Integer.parseInt(row[2].trim());
                //int yearTo = Integer.parseInt(row[3].trim());

                String genre = row[2];
                String[] movieGenre = genre.split(", ");
                ArrayList<String> aGenre = new ArrayList<>();
                for (String s1 : movieGenre) {
                    aGenre.add(s1);
                }


                String r = row[3].replace(',', '.');
                double rating = Double.parseDouble(r.trim());

                String seasonAndEpisodes = row[4];

                Series ss = new Series(name, releaseYear, aGenre, seasonAndEpisodes, rating);
                series.add(ss);
            }
        }
    }

    private void makeUser(ArrayList<String> userList) { // lav det samme for series
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

    public static void searchGenre(ArrayList<? extends AMedia> movie, ArrayList<? extends AMedia> serie) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter the name of a Genre, if you want to exit, please type 'exit': ");
            String search = scanner.nextLine();

            //end loop if user types exit
            if (search.equalsIgnoreCase("exit")) {
                break;
            }

            //find the media in lists
            boolean foundMovies = searchList(movie, search);
            boolean foundSeries = searchList(serie, search);

            //display results
            if (foundMovies && foundSeries) {
                TextUI.displayMessage("Genre found! ");
            } else {
                TextUI.displayMessage("Not found, please try again. ");
            }
        }
        scanner.close();
    }



    /*
    private static boolean searchGenre(ArrayList<? extends AMedia> list, String searchGenre) {
        for(AMedia media: list){
            if(AMedia.getGenre().equalsIgnoreCase(searchGenre)){
                TextUI.displayMessage("Found: " + media);
                return true;
            }
        }
        return false;
    }

     */



    /*
    private static boolean searchGenre(ArrayList<? extends AMedia> list, String searchGenre) {
        for (AMedia media : list) {
            for (String genre : media.getGenres()) { // antager, at der er en getGenres() metode i din AMedia-klasse
                if (genre.toLowerCase().contains(searchGenre.toLowerCase())) {
                    TextUI.displayMessage("Found: " + media);
                    return true;
                }
            }
        }
        return false;
    }

     */



    public void searchGenre() {
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Series> getSeries() {
        return series;
    }

    /*
    textUI.displayMessage(" Thanks for watching! ");
    endGame();
}
    private void endGame() {
        FileIO.saveGameData(users);
    }
    */

    @Override
    public String toString() {
        return toString();
    }

}
