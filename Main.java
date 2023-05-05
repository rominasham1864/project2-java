
package ir.ac.kntu;

import java.util.*;

public class Main {
    final private static Admin admin = new Admin("r", "r");
    private static List<User> UsersList = new ArrayList<>();
    private static List<Game> GameList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    private static boolean Admin = false;
    private static Map<Game, Map<User, Double>> outermap = new HashMap<>();
    private static Map<User, Double> inermap = new HashMap<>();
    private static Map<User, ArrayList<User>> Friends = new HashMap<>();
    private static Map<User, ArrayList<User>> Resquests = new HashMap<>();

    public static void main(String[] args) {
        User user = new User("r", "Romi123", "4", "5");
        ArrayList<User> requests= new ArrayList<>();
        Resquests.put(user, requests);
        ArrayList<User> friends= new ArrayList<>();
        Friends.put(user, friends);
        Game game = new Game("me", "me", "me", 12, 0);
        UsersList.add(user);
        GameList.add(game);
        user.setLibrary(GameList);
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
        System.out.println("1.Sign in \t 2.Sign up \t 0.Back");
        int enter = scanner.nextInt();
        switch (enter) {
            case 1 -> SingIn();
            case 2 -> SingUp();
            case 0 -> Enter();
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
            System.out.println("1.users \t 2.games \t 0.back");
            switch (scanner.nextInt()) {
                case 1:
                    UsersPageAdmin();
                    break;
                case 2:
                    GamesPage();
                    break;
                case 0:
                    Enter();
                    break;
            }
        } else {
            System.out.println("Admin not found! Please try again.");
            Admin();
        }

    }

    public static void UsersPageAdmin() {
        System.out.println("Chose your action: \n 1.Users info \t 2.New user \t 3.Delete user \t 0.back");
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
            case 0:
                Enter();
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
        ArrayList<User> requests=new ArrayList<>();
        Friends.put(user, requests);
        System.out.println("Info had successfully add to data base.");
        if (Admin) {
            UsersPageAdmin();
        } else {
            User();
        }
    }

    public static User FindUser() {
        System.out.println("How would you like to fine user? \n 1.UserName \t 2.Email \t 3.PhoneNumber \t 0.back");
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
        System.out.println("User deleted successfully.");
        UsersPageAdmin();
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
            System.out.println("Wrong Password Please try again.Press 1 for retry and 0 for exit:");
            int cmd = scanner.nextInt();
            if (cmd == 1) {
                CorrectPass(userName);
            } else {
                Enter();
            }
        }
    }

    public static void Profile(User user) {
        System.out.println("This is your Profile:");
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
                UserPage(user);
                break;
        }
    }

    public static void ShowUserInfo(User user) {
        System.out.println("User Information: (0.back)");
        System.out.println("UserName: " + user.getUserName());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("PhoneNumber: " + user.getPhone());
        System.out.println("Wallet: " + user.getWallet() + "\n");
        if (scanner.nextInt() == 0) {
            UsersPageAdmin();
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
                Profile(user);
                break;

        }
    }

    public static void UserPage(User user) {
        System.out.println("You are in Home Page:");
        System.out.println("1.profile \t 2.store \t 3.library \t 4.friends \t 0.back");
        int page = scanner.nextInt();
        switch (page) {
            case 1:
                Profile(user);
                break;
            case 2:
                Store(user);
                break;
            case 3:
                Library(user);
                break;
            case 4:
                Friends(user);
                break;
            case 0:
                User();
                break;

        }
    }

    public static void Library(User user) {
        List<Game> gamesList;
        gamesList = user.getLibrary();
        System.out.println("Your Games are:");
        for (int i = 0; i < gamesList.size(); i++) {
            System.out.println(i + 1 + "." + gamesList.get(i).getName());
        }
        System.out.println("Chose one to show information:(0.back)");
        int cmd = scanner.nextInt();
        if (cmd == 0) {
            UserPage(user);
        } else {
            UserGameInfo(gamesList.get(cmd - 1), user);
        }
    }

    public static void UserGameInfo(Game game, User user) {
        System.out.println("Name: " + game.getName() + "\nDescription: " + game.getInfo() +
                "\nGenre: " + game.getGenre() + "\nPrice: " + game.getPrice() + "\nRate: " + game.getAvrage()
                + "\n1.Community \t 2.Rating \t 3.back");
        switch (scanner.nextInt()) {
            case 1:
                GetCommunity(game, user);
                break;
            case 2:
                GetGameRate(game, user, outermap);
                break;
            case 3:
                Library(user);
                break;
        }

    }

    public static void GetCommunity(Game game, User user) {
        List<String> community = game.getCommunity();
        System.out.println("Comments on this game:");
        for (int i = 0; i < community.size(); i++) {
            System.out.println(i + 1 + "." + community.get(i));
        }
        System.out.println("0.back \t 1.Add comment");
        switch (scanner.nextInt()) {
            case 0:
                ShowGameInfo(game, user);
                break;
            case 1:
                SetCommunity(game, user);
                break;
        }
    }

    public static void SetCommunity(Game game, User user) {
        List<String> community = game.getCommunity();
        System.out.print("Add your comment:\n");
        scanner.nextLine();
        String newComment = scanner.nextLine();
        community.add(newComment);
        game.setCommunity(community);
        System.out.println("Comment added!\n0.back");
        if (scanner.nextInt() == 0) {
            GetCommunity(game, user);
        }
    }

    public static void ChangeGameRate(Game game, User user, Map inermap) {
        System.out.println("Enter your new rate for 0 to 10");
        double oldRate = (double) inermap.get(user);
        double sum = game.getAvrage();
        sum -= oldRate;
        scanner.nextLine();
        sum += (double) scanner.nextInt();
        game.setAvrage(sum / game.getRateCount());
        System.out.println("Your rating have gone successfully!\n0.back");
        if (scanner.nextInt() == 0) {
            System.out.println("hora");
            // GetGameRate(game, user, map);
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
                                ChangeGameRate(game, user, inermap);
                            case 2:
                                GetGameRate(game, user, map);
                        }
                    }
                } else {
                    System.out.println("Enter your new rate for 0 to 10");
                    double newRate = scanner.nextDouble();
                    int count = game.getRateCount();
                    count++;
                    game.setRateCount(count);
                    double sum = game.getRateCount() * game.getAvrage();
                    sum += newRate;
                    game.setAvrage(sum / count);
                    inermap.put(user, newRate);
                    outermap.put(game, inermap);
                    System.out.println("Your rating have gone successfully!\n0.back");
                    if (scanner.nextInt() == 0) {
                        UserGameInfo(game, user);
                    }
                }
                break;
            case 2:
                UserGameInfo(game, user);
                break;
        }
    }

    public static void Store(User user) {
        System.out.println("Games available are:");
        for (int i = 0; i < GameList.size(); i++) {
            System.out.println(i + 1 + "." + GameList.get(i).getName() + ": " + GameList.get(i).getInfo());
        }
        System.out.println("0.back");
        System.out.println("Chose your game:");
        int cmd = scanner.nextInt();
        if (cmd == 0) {
            UserPage(user);
        } else {
            ShowGameInfo(GameList.get(cmd - 1), user);
        }
    }

    public static void ShowGameInfo(Game game, User user) {
        System.out.println("Name: " + game.getName() + "\nDescription: " + game.getInfo() +
                "\nGenre: " + game.getGenre() + "\nPrice: " + game.getPrice() + "\nRate: " + game.getAvrage());
        CheckLibrary(game, user);
    }

    private static void CheckLibrary(Game game, User user) {
        if (!user.getLibrary().contains(game)) {
            System.out.println("1.Buy \t 2.back");
            if (scanner.nextInt() == 1) {
                BuyGame(game, user);
            }
        } else {
            System.out.println("1.back");
            if (scanner.nextInt() == 1) {
                Store(user);
            }
        }
    }

    private static void BuyGame(Game game, User user) {
        List<Game> library = user.getLibrary();
        library.add(game);
        user.setLibrary(library);
        if (user.getWallet() - game.getPrice() >= 0) {
            user.setWallet(user.getWallet() - game.getPrice());
            System.out.println("Game added to library.\n1.back");
        } else {
            System.out.println("You don't have enough money:)\n1.bak");
        }
        if (scanner.nextInt() == 1) {
            Store(user);
        }
    }

    private static void Friends(User user) {
        System.out.println("1.Show friends \t 2.Search \t 3.Find friends \t 4.Your requests \t 0.back");
        switch (scanner.nextInt()) {
            case 1:
                ShowFriendsList(user);
                break;
            case 2:
                Search(user);
                break;
            case 3:
                User newfriend = FindFriend();
                if (newfriend == null) {
                    System.out.println("User not found!");
                    Friends(user);
                } else {
                    ArrayList<User> requests = new ArrayList<>();
                    Resquests.put(user, requests);
                    System.out.println("Would you like to send a friendship request to this user?\n1.Yes 2.No");
                    if (scanner.nextInt() == 1) {
                        Resquests.get(user).add(newfriend);
                        System.out.println("Request sent successfully! waite for their respond...");
                        UserPage(user);
                    }
                }
                break;
            case 4:
                ShowRequests(user);
                break;
            case 0:
                UserPage(user);
                break;

        }
    }

    private static void ShowRequests(User user) {
        if (Resquests.get(user).isEmpty()) {
            System.out.println("You have no requests! (0.back)");
            if (scanner.nextInt() == 0) {
                Friends(user);
            }
        } else {
            System.out.println("Your new Requests are:");
            for (int i = 0; i < Resquests.size(); i++) {
                System.out.println(i + 1 + "." + Resquests.get(user).get(i).getUserName());
            }
            System.out.println("Witch one you wish to accept: (0.back)");
            int cmd =scanner.nextInt();
            if(cmd==0){
                Friends(user);
            } else {
                User newFriend= Resquests.get(user).get(cmd-1);
                Friends.get(user).add(newFriend);
                Resquests.get(user).remove(cmd-1);
                System.out.println("Request accepted!");
                ShowRequests(user);
            }
        }
    }

    private static User FindFriend() {
        System.out.println("Search for Username:");
        String username = scanner.next();
        for (User user1 : UsersList) {
            if (user1.getUserName().equals(username)) {
                return user1;
            }
        }
        return null;
    }

    private static void Search(User user) {
        if (Friends.get(user).isEmpty()) {
            System.out.println("Currently you have no friend:)\n0.back");
            if (scanner.nextInt() == 0) {
                Friends(user);
            }
        } else {
            System.out.println("Chose one: (0.back)");
            String search = scanner.next();
            for (int i = 0; i < Friends.get(user).size(); i++) {
                if (Friends.get(user).get(i).getUserName().startsWith(search)) {
                    System.out.println(i + 1 + "." + Friends.get(user).get(i).getUserName());
                }
            }
            int cmd = scanner.nextInt();
            if (cmd == 0) {
                ShowFriendsList(user);
            } else {
                FriendLibrary(Friends.get(user).get(cmd), user);
            }
        }
    }

    private static void ShowFriendsList(User user) {
        if (Friends.get(user).isEmpty()) {
            System.out.println("Currently you have no friend:)\n0.back");
            if (scanner.nextInt() == 0) {
                Friends(user);
            }
        } else {
            System.out.println("Your friends list, chose one:(0.back)");
            for (int i = 0; i < Friends.get(user).size(); i++) {
                System.out.println(i + 1 + "." + Friends.get(user).get(i).getUserName());
            }
            int cmd = scanner.nextInt();
            if (cmd == 0) {
                Friends(user);
            } else {
                User friend = Friends.get(user).get(cmd - 1);
                FriendLibrary(friend, user);
            }
        }
    }


    private static void FriendLibrary(User friend, User user) {
        List<Game> gamesList;
        gamesList = friend.getLibrary();
        System.out.println("Your friends games are:(0.back)");
        for (int i = 0; i < gamesList.size(); i++) {
            System.out.println(i + 1 + "." + gamesList.get(i).getName());
        }
        if (scanner.nextInt() == 0) {
            Friends(user);
        }
    }
}