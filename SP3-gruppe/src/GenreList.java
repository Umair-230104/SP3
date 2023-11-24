import java.util.ArrayList;

public class GenreList {
    public String genreAll;

    public GenreList(String genreAll) {
       this.genreAll = genreAll;
    }

    public String getGenreAll() {
       return genreAll;
    }

    @Override
    public String toString() {
        return "GenreList{" +
                "genreAll='" + getGenreAll() + '\'' +
                '}';
    }

}

