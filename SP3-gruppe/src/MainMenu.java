import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();
    private FileIO io = new FileIO();
    private TextUI ui = new TextUI();
    private ArrayList<User> users; // skal sættes ind i en funktion for at læse data


    public void setUp() {
        // For film
        ArrayList<String> movie = io.readMovieData("movies.data");
        //System.out.println(movie.size());
        //System.out.println(movie.get(0));

        // test makeMoives
        //System.out.println(this.movies.size());
        //makeMovies(movie);
        //System.out.println(movies);


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

        StartMenu startMenu = new StartMenu(users);



        // Display the menu options

        startMenu.displayMenuOptions();
        //startMenu.signUp();
        //startMenu.logIn();




    }

    private void makeMovies(ArrayList<String> moviesList) { // lav det samme for series
        if (moviesList.size() > 0) {

            for (String s : moviesList) {

                String[] row = s.split(";");
                String name = row[0];

                String releaseYear = row[1];

                String genre = row[2];
                String[] movieGenre = genre.split(", ");
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

    // Fejl i denne
    private void makeSeries(ArrayList<String> seriesList) { // lav det samme for series
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
            System.out.println("Please enter the name of a movie or series, if you want to exit, please type 'exit': ");
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
