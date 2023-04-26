package ir.ac.kntu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class test {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User user = new User("m", "m", "m", "m");
        Map<Game, Map<User, Double>> outermap = new HashMap<Game, Map<User, Double>>();
        Map<User, Double> inermap = new HashMap<User, Double>();
        Game game = new Game("me", "me", "me", 12, 0);
        inermap.put(user, 12.0);
        outermap.put(game, inermap);
        GetGameRate(game, user, outermap);

    }

    public static void SetGameRate(Game game, User user, Map inermap) {
        System.out.println("Enter your new rate for 0 to 10");
        double oldRate = (double) inermap.get(user);
        double sum = game.getAvrage();
        sum -= oldRate;
        sum += scanner.nextDouble();
        game.setAvrage(sum / game.getRateCount());
        System.out.println("Your rating have gone successfully!\n0.back");
        if (scanner.nextInt() == 0) {
            System.out.println("hora");
            //GetGameRate(game, user, map);
        }
    }

    public static void GetGameRate(Game game, User user, Map map) {
        System.out.println(game.getName() + "'s rate is: " + game.getAvrage() + ".\n 0.back \t 1.Rate game");
        switch (scanner.nextInt()) {
            case 1:
                if (map.containsKey(game)) {
                    Map<User, Double> inermap = (Map<User, Double>) map.get(game);
                    if (inermap.containsKey(user)) {
                        System.out.println("You've already rate this game. Do you wanna rate again?\n1.Yes \t 2.No");
                        switch (scanner.nextInt()) {
                            case 1:
                                SetGameRate(game, user, inermap);
                            case 2:
                                GetGameRate(game, user, map);
                        }
                    } else {
                        SetGameRate(game, user, inermap);
                    }
                }
        }
    }
}
