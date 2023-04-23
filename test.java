package ir.ac.kntu;

import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        User user = new User("", "", "", "");
        Game game = new Game("me", "me", "me", 12, 12);
        List<Game> gamesList;
        gamesList = user.getLibrary();
        gamesList.add(game);
        System.out.println("Your Games are:");
        for (int i = 0; i < gamesList.size(); i++) {
            System.out.println(i + 1 + "." + gamesList.get(i).getName());
        }
        System.out.println("Chose one to show information:(0.back)");
        int cmd = scanner.nextInt();
        if (cmd == 0) {
            //UserPage(user);
            System.out.println("d");
        } else {
            //GameLibrary(gamesList.get(scanner.nextInt() - 1), user);
            System.out.println("me");
        }
    }
}
