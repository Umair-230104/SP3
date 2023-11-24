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
            //scan.nextLine();
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
            FileWriter writer = new FileWriter("movies.data");
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
            //scan.nextLine();
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
            FileWriter writer = new FileWriter("series.data");
            writer.write("Name, yearfrom, yearTo,Genre, seasonAndEpisodes, rating " + " \n");
            for (Series s : series) {
                String textTosave = s.getName() + "," + s.getGenre() + "," + s.getSeasonAndEpisodes() + "," + s.getRating();
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("noget gik galt ved skrivning til fil");
        }


    }

    public ArrayList<String> readUserData(String path) {
        ArrayList<String> users = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);
            //scan.nextLine();
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                users.add(s);
            }
        } catch (FileNotFoundException e) {
            TextUI.displayMessage("File not found");
        }
        return users;
    }


    public void saveUserData(ArrayList<User> users) {
        try {
            FileWriter writer = new FileWriter("ListUser.data");
            writer.write("Username, password " + " \n");
            for (User u : users) {
                String textTosave = u.getUserName() + "," + u.getPassWord();
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("noget gik galt ved skrivning til fil");
        }
    }






// GENRE
public ArrayList<String> readGenreData(String path) {
    ArrayList<String> genres = new ArrayList<>();
    File f = new File(path);
        try {
            Scanner scan = new Scanner(f);
            //scan.nextLine();
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                genres.add(s);
            }
        } catch (FileNotFoundException e) {
            TextUI.displayMessage("File not found");
        }
        return genres;
    }

    public void saveGenre(ArrayList<GenreList> genres) {
        try {
            FileWriter writer = new FileWriter("GenreList.data");
            writer.write("Genre: " + " \n");
            for (GenreList g : genres) {
                String textTosave = g.getGenreAll();
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("noget gik galt ved skrivning til fil");
        }
    }

}


