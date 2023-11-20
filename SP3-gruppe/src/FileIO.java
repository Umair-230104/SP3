import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    public ArrayList<String> readMovieData(String path) {
        ArrayList<String> movie = new ArrayList<>();
        File f = new File(path);

        try {
            Scanner scan = new Scanner(f);
            scan.nextLine();
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                movie.add(s);
            }
        } catch (FileNotFoundException e) {
            TextUI.displayMessage("File not found");
        }
        return movie;
    }

    public void saveMovieData(ArrayList<Movie> movies) {
        try {
            FileWriter writer = new FileWriter("src/movies.data");
            writer.write("Name, year, genre, rating " + " \n");
            for (Movie m : movies) {
                String textTosave = m.getName() + "," + m.getReleaseYear() + "," + m.getGenre() + "," + m.getRating();
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("noget gik galt ved skrivning til fil");
        }
    }

    public ArrayList<String> readSeriesData(String path) {
        ArrayList<String> series = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                series.add(s);
            }
        } catch (FileNotFoundException e) {
            TextUI.displayMessage("File not found");
        }
        return series;
    }

    public void saveSeriesData(ArrayList<Series> series) {
        try {
            FileWriter writer = new FileWriter("src/series.data");
            writer.write("Name, yearfrom, yearTo,Genre, seasonAndEpisodes, rating,}" + "" + " n\"");
            for (Series s : series) {
                String textTosave = s.getName() + "," + s.getYearFrom() + "," + s.getYearTo() + "," + s.getGenre()  + "," +s.getSeasonAndEpisodes() + "," + s.getRating();
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("noget gik galt ved skrivning til fil");
        }
    }
}

