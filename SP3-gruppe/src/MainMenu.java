import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();
    private FileIO io = new FileIO();
    private TextUI ui = new TextUI();


    public void setUp() {
        // For filme
        ArrayList<String> movies = io.readMovieData("src/movies.data");
        if (movies.size() > 0) {
            if (TextUI.displayMessage("Vil du starte denne film? Y/N").equalsIgnoreCase("Y")) {
                for (String s : movies) {

                    String[] row = s.split(",");              // s splittes to strings ==>  "Egon", "200"
                    String name = row[0];                           // ==> "Egon"
                    // int balance = Integer.parseInt(row[1].trim());  // Konverterer string til int "200" ==> 200

                    int releaseYear = Integer.parseInt(row[1].trim());
                    int rating = Integer.parseInt(row[3].trim()); // Hvad hvis der er flere genre?

                    registerMovie(name, releaseYear, genre, rating);
                    // placerer objektet i listen med kunder
                }
            } else {
                //runPlayerSetupDialog(); dette er fra matador hvad kan vi ligge ind her?
            }
        } else {
            //runPlayerSetupDialog(); dette er fra matador hvad kan vi ligge ind her
        }
        // For serie
        ArrayList<String> series = io.readSeriesData("src/movies.data");
        if (series.size() > 0) {
            if (ui.getUserInput().equalsIgnoreCase("Y")) {
                for (String s : series) {

                    String[] row = s.split(",");              // s splittes to strings ==>  "Egon", "200"
                    String name = row[0];                           // ==> "Egon"
                    // int balance = Integer.parseInt(row[1].trim());  // Konverterer string til int "200" ==> 200

                    int releaseYear = Integer.parseInt(row[1].trim());
                    int yearFrom = Integer.parseInt(row[2].trim());
                    int yearTo = Integer.parseInt(row[3].trim());
                    String genre = row[4];
                    String SeasonAndEpisodes = row[5];
                    int rating = Integer.parseInt(row[6].trim());

                    registerSeries(name, releaseYear, yearFrom, yearTo, genre, SeasonAndEpisodes, rating);
                    // placerer objektet i listen med kunder
                }
            } else {
                //runPlayerSetupDialog(); dette er fra matador hvad kan vi ligge ind her?
            }
        } else {
            //runPlayerSetupDialog(); dette er fra matador hvad kan vi ligge ind her
        }

    }

    // For filme
    private void registerMovie(String name, int releaseYear, String genre, int rating) {
        Movie m = new Movie(name, releaseYear, genre, rating); //bruger de indlæste værdier til at konstruere et movie objekt (instansiering)
        movies.add(m);
    }

    // For serie
    private void registerSeries(String name, int releaseYear, int yearFrom, int yearTo, String genre, String SeasonAndEpisodes, int rating) {
        Series s = new Series(name, releaseYear, yearFrom, yearTo, genre, SeasonAndEpisodes, rating); //bruger de indlæste værdier til at konstruere et movie objekt (instansiering)
        series.add(s);
    }
    public static void searchMedia(ArrayList<Movie>movies, ArrayList<Series> series){
        Scanner scanner=new Scanner(System.in);

        while(true){
            System.out.println("Please enter the name of a movie or series, if you want to exit, please type 'exit': ");
            String search=scanner.nextLine();

            //end loop if user types exit
            if(search.equalsIgnoreCase("exit")){
                break;
            }

            //find the media in lists
            boolean foundMovies= searchList(movies,searchTerm);
            boolean foundSeries=searchList(series,searchTerm);

            //display results
            if(foundMovies || foundSeries){
                System.out.println("Media found");
            } else {
                System.out.println("Not found");
            }
        }
        scanner.close();
    }
    private static boolean searchList(ArrayList<? extends Media> list, String searchTerm){
        for(Media media:list){
            if(AMedia.getName().equalsIgnoreCase(searchTerm)){
                System.out.println("Found: "+ media);
                return true;
            }
        }
        return false;
    }


    public void searchMedia() {
    }

    public void searchGenre() {
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
