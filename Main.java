
package ir.ac.kntu;

import java.awt.font.GlyphMetrics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final private static Admin admin = new Admin("name", "password");
    private static List<User> UsersList = new ArrayList<>();
    private static List<Game> GameList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    private static boolean Admin = false;

    public static void main(String[] args) {
        Enter();
    }

    public static void Enter() {
        System.out.println("Enter your role:");
        System.out.println("1.User \t 2.Admin");
        int role = scanner.nextInt();
        switch (role) {
            case 1 -> User();
            case 2 -> Admin();
            default -> {
                System.out.println("Please try again;");
                Enter();
            }
        }
        scanner.close();
    }

    public static void User() {
        Admin = false;
        System.out.println("Chose one:");
        System.out.println("1.Sing in \t 2.Sing up \t 3.Back");
        int enter = scanner.nextInt();
        switch (enter) {
            case 1 -> SingIn();
            case 2 -> SingUp();
            case 3 -> Enter();
            default -> {
                System.out.println("Please try again;");
                User();
            }
        }

    }

    public static void Admin() {
        System.out.println("Enter you UserName:");
        String userName = scanner.next();
        System.out.println("Enter you Password:");
        String password = scanner.next();
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            Admin = true;
            System.out.println("Welcome " + admin.getUserName() + "!");
            System.out.println("Chose your action:");
            System.out.println("1.users \t 2.games \t 3.back");
            switch (scanner.nextInt()) {
                case 1:
                    UsersPageAdmin();
                    break;
                case 2:
                    GamesPage();
                    break;
                case 3:
                    Enter();
                    break;
            }
        } else {
            System.out.println("Admin not found! Please try again.");
            Admin();
        }

    }

    public static void UsersPageAdmin() {
        System.out.println("Chose your action: \n 1.Users info \t 2.New user \t 3.Delete user \t 4.back");
        switch (scanner.nextInt()) {
            case 1:
                ShowUserInfo(FindUser());
                break;
            case 2:
                SingUp();
                break;
            case 3:
                DeleteUser(FindUser());
                break;
            case 4:
                Admin();
                break;
        }
    }

    public static void SingIn() {
        System.out.println("Enter UserName:");
        String userName = scanner.next();
        UserExistence(userName);
        CorrectPass(userName);
    }

    public static void SingUp() {
        String name = UserNameCheck();
        String password = UserPasswordCheck();
        System.out.println("Enter Email Address:");
        String email = scanner.next();
        System.out.println("Enter Phone Number:");
        String phone = scanner.next();
        User user = new User(name, password, email, phone);
        UsersList.add(user);
        System.out.println("Info had successfully add to data base.");
        if (Admin) {
            UsersPageAdmin();
        } else {
            User();
        }
    }

    public static User FindUser() {
        System.out.println("How would you like to fine user? \n 1.UserName \t 2.Email \t 3.PhoneNumber");
        switch (scanner.nextInt()) {
            case 1:
                return FindUserByName();
            case 2:
                return FindUserByEmail();
            default:
                return FindUserByPhone();
        }
    }

    public static void DeleteUser(User user) {
        UsersList.remove(user);
    }

    public static User FindUserByEmail() {
        List<User> usersFound = new ArrayList<>();
        System.out.println("Enter email:");
        String email = scanner.next();
        for (User user : UsersList) {
            if (email.equals(user.getEmail())) {
                usersFound.add(user);
            }
        }
        if (usersFound.isEmpty()) {
            System.out.println("No users found! Please try again.");
            return FindUser();
        } else {
            System.out.println("Users found:");
            for (int i = 0; i < usersFound.size(); i++) {
                System.out.println(i + 1 + "." + usersFound.get(i).getEmail() + ": " + usersFound.get(i).getUserName());
            }
            System.out.println("Witch one is your target?(Enter the number)");
            int num = scanner.nextInt();
            return usersFound.get(num - 1);
        }
    }

    public static User FindUserByPhone() {
        List<User> usersFound = new ArrayList<>();
        System.out.println("Enter phoneNumber:");
        String phoneNumber = scanner.next();
        for (User user : UsersList) {
            if (phoneNumber.equals(user.getPhone())) {
                usersFound.add(user);
            }
        }
        if (usersFound.isEmpty()) {
            System.out.println("No users found! Please try again.");
            return FindUser();
        } else {
            System.out.println("Users found:");
            for (int i = 0; i < usersFound.size(); i++) {
                System.out.println(i + 1 + "." + usersFound.get(i).getPhone() + ": " + usersFound.get(i).getUserName());
            }
            System.out.println("Witch one is your target?(Enter the number)");
            int num = scanner.nextInt();
            return usersFound.get(num - 1);
        }
    }

    public static User FindUserByName() {
        List<User> usersFound = new ArrayList<>();
        System.out.println("Enter user name:");
        String userName = scanner.next();
        for (User user : UsersList) {
            if (userName.equals(user.getUserName())) {
                usersFound.add(user);
            }
        }
        if (usersFound.isEmpty()) {
            System.out.println("No users found! Please try again.");
            return FindUser();
        } else {
            System.out.println("Users found:");
            for (int i = 0; i < usersFound.size(); i++) {
                System.out.println(i + 1 + "." + usersFound.get(i).getUserName() + ": " + usersFound.get(i).getEmail());
            }
            System.out.println("Witch one is your target?(Enter the number)");
            int num = scanner.nextInt();
            return usersFound.get(num - 1);
        }
    }

    public static void GamesPage() {
        System.out.println("Chose your action:");
        System.out.println("1.new game \t 2.change game info \t 3.delete game \t 4.back");
        switch (scanner.nextInt()) {
            case 1:
                AddNewGame();
                break;
            case 2:
                ChangeGameInfo(FindGame());
                break;
            case 3:
                DeleteGame(FindGame());
                break;
            case 4:
                Admin();
                break;
        }
    }

    public static void DeleteGame(Game game) {
        GameList.remove(game);
        System.out.println("Game deleted successfully. \n1.back");
        if (scanner.nextInt() == 1) {
            GamesPage();
        }
    }

    private static Game FindGame() {
        List<Game> gamesFound = new ArrayList<>();
        System.out.println("Enter game name:");
        String gameName = scanner.next();
        for (Game game : GameList) {
            if (gameName.equals(game.getName())) {
                gamesFound.add(game);
            }
        }
        if (gamesFound.isEmpty()) {
            System.out.println("No games found! Please try again.");
            return FindGame();
        } else {
            System.out.println("Games found:");
            for (int i = 0; i < gamesFound.size(); i++) {
                System.out.println(i + 1 + "." + gamesFound.get(i).getName() + ": " + gamesFound.get(i).getInfo());
            }
            System.out.println("Witch one is your target?(Enter the number)");
            int num = scanner.nextInt();
            return gamesFound.get(num - 1);
        }
    }

    public static void ChangeGameInfo(Game game) {
        System.out.println("Witch Info you wish to change:\n 1.Game name" +
                " \t 2.Game description \t 3.Game genre \t 4.Game price \t 5.back");
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Enter new Game name:");
                String newName = scanner.next();
                game.setName(newName);
                ChangeGameInfo(game);
                break;
            case 2:
                System.out.println("Enter new Game description:");
                game.setDescription(scanner.next());
                ChangeGameInfo(game);
                break;
            case 3:
                System.out.println("Enter new Game genre:");
                game.setGenre(scanner.next());
                ChangeGameInfo(game);
                break;
            case 4:
                System.out.println("Enter new Game price:");
                game.setPrice(scanner.nextInt());
                ChangeGameInfo(game);
                break;
            case 5:
                GamesPage();
                break;
        }
    }

    public static void AddNewGame() {
        Game game = new Game();
        System.out.println("Enter new game name:");
        game.setName(scanner.next());
        System.out.println("Enter new game Info:");
        game.setDescription(scanner.next());
        System.out.println("Enter new game genre:");
        game.setGenre(scanner.next());
        System.out.println("Enter new game Price:");
        game.setPrice(scanner.nextInt());
        GameList.add(game);
        System.out.println("Game added successfully!\n 1.back");
        if (scanner.nextInt() == 1) {
            GamesPage();
        }
    }

    public static String UserNameCheck() {
        System.out.println("Enter UserName:");
        String name = scanner.next();
        for (User user : UsersList) {
            if (name.equals(user.getUserName())) {
                System.out.println("UserName already exist, please try again.");
                return UserNameCheck();
            }
        }
        return name;
    }

    public static String UserPasswordCheck() {
        System.out.println(
                "Enter Password:(Should include at least 8 chars with Capital and Small letters and Numbers)");
        String password = scanner.next();
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
            System.out.println("Password is not acceptable. Please try again.");
            return UserPasswordCheck();
        } else {
            return password;
        }
    }

    public static void UserExistence(String userName) {
        boolean existence = false;
        for (User user : UsersList) {
            if (user.getUserName().equals(userName)) {
                existence = true;
                break;
            }
        }
        if (!existence) {
            System.out.println("You do not have an account.");
            System.out.println("1.back");
            int request = scanner.nextInt();
            if (request == 1) {
                User();
            }
        }
    }

    public static void CorrectPass(String userName) {
        System.out.println("Enter you Password:");
        String password = scanner.next();
        boolean done = false;
        for (User user : UsersList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                System.out.println("You have Entered, Welcome " + userName + "!");
                UserPage(user);
                done = true;
                break;
            }
        }
        if (!done) {
            System.out.println("Wrong Password Please try again.Press 1 for retry and 2 for exit:");
            int cmd = scanner.nextInt();
            if (cmd == 2) {
                CorrectPass(userName);
            } else {
                SingIn();
            }
        }
    }

    public static void Profile(User user) {
        System.out.println("This is your Profile");
        ShowUserInfo(user);
        System.out.println("1.Change info \t 2.back");
        if(scanner.nextInt()==1){
            ChangeUserInfo(user);
        }else {
            UserPage(user);
        }
    }

    public static void ShowUserInfo(User user) {
        System.out.println("User Information");
        System.out.println("UserName: " + user.getUserName());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("PhoneNumber: " + user.getPhone());
        System.out.println("Wallet: " + user.getWallet() + "\n");
        System.out.println("1.ChangeInfo \t 2.back");
        switch (scanner.nextInt()) {
            case 1:
                ChangeUserInfo(user);
                break;
            case 2:
                if (Admin) {
                    UsersPageAdmin();
                }

                Profile(user);
                break;
        }
    }

    public static void ChangeUserInfo(User user) {
        System.out.println("Witch data you wish to change?");
        System.out.println("1.UserName \t 2.Password \t 3.Email \t 4.PhoneNumber \t 5.Wallet \t 6.back");
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Enter new UserName:");
                user.setUserName(scanner.next());
                ChangeUserInfo(user);
                break;
            case 2:
                System.out.println("Enter new Password:");
                user.setPassword(scanner.next());
                ChangeUserInfo(user);
                break;
            case 3:
                System.out.println("Enter new Email:");
                user.setEmail(scanner.next());
                ChangeUserInfo(user);
                break;
            case 4:
                System.out.println("Enter new PhoneNumber:");
                user.setPhone(scanner.next());
                ChangeUserInfo(user);
                break;
            case 5:
                System.out.println("Wallet charging:");
                user.setWallet(scanner.nextInt());
                ChangeUserInfo(user);
                break;
            case 6:
                ShowUserInfo(user);
                break;

        }
    }

    public static void UserPage(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.profile \t 2.store \t 3.library \t 4.friends \t 5.back");
        int page = scanner.nextInt();
        switch (page) {
            case 1:
                Profile(user);
                break;
            case 2:
                // Store();
                break;
            case 3:
                // Library();
                break;
            case 4:
                // Friends();
                break;
            case 5:
                User();
                break;

        }
    }
}
