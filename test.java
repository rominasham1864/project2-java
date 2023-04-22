package ir.ac.kntu;

import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        User user=new User("" ,"", "", "");
        Game game=new Game("me", "me", "me", 12, 12);
        List<Game> library = user.getLibrary();
        library.add(game);
        user.setLibrary(library);
    }
}
