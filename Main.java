package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final private static Admin admin = new Admin("name", "password");
    private static List<User> UsersList = new ArrayList<>();

    public static void main(String[] args) {
        Enter();
    }
    public static void Enter() {
        System.out.println("Enter your role:");
        System.out.println("1.User \t 2.Admin");
        Scanner sc = new Scanner(System.in);
        int role = sc.nextInt();
        switch (role) {
            case 1 -> User();
            case 2 -> Admin();
            default -> {
                System.out.println("Please try again;");
                Enter();
            }
        }
        sc.close();
    }
    public static void User() {
        System.out.println("Chose one:");
        System.out.println("1.Sing in \t 2.Sing up \t 3.Back");
        Scanner scanner = new Scanner(System.in);
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
    public static void SingIn() {
        System.out.println("Enter you UserName:");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        UserExistence(userName);
        CorrectPass(userName);
    }
    public static void SingUp() {
        String name = UserNameCheck();
        String password = UserPasswordCheck();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter you Email Address:");
        String email = scanner.nextLine();
        System.out.println("Enter you Phone Number:");
        String phone = scanner.nextLine();
        User user = new User(name, password, email, phone);
        UsersList.add(user);
        System.out.println("Your info had successfully add to data base, Please Sing in.");
        SingIn();
    }
    public static void Admin() {
        System.out.println("Enter you UserName:");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.println("Enter you Password:");
        String password = scanner.nextLine();
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            System.out.println("Welcome " + admin.getUserName() + "!");
            System.out.println("Chose your action:");
            System.out.println("1.users \t 2.games \t 3.back");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    //UsersPage();
                    break;
                case 2:
                    //GamesPage();
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
    public static String UserNameCheck() {
        System.out.println("Enter you UserName:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        for (User user : UsersList) {
            if (name.equals(user.getUserName())) {
                System.out.println("UserName already exist, please try again.");
                return UserNameCheck();
            }
        }
        return name;
    }
    public static String UserPasswordCheck() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter you Password:(Should include at least 8 chars with Capital and Small letters and Numbers)");
        String password = scanner.nextLine();
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
            System.out.println("Password is not acceptable. Please try again.");
            return UserPasswordCheck();
        } else {
            return password;
        }
    }
    public static void UserExistence(String userName) {
        boolean existence = false;
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter you Password:");
        String password = scanner.nextLine();
        boolean done = false;
        for (User user : UsersList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                System.out.println("You have Entered, Welcome " + userName + "!");
                UserPage();
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
    public static void Profile(){
        System.out.println("This is your Profile");
        System.out.println("1.ShowInfo \t 2.");
    }
    public static void UserPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.profile \t 2.store \t 3.library \t 4.friends \t 5.back");
        int page = scanner.nextInt();
        switch (page) {
            case 1:
                Profile();
                break;
            case 2:
                //Store();
                break;
            case 3:
                //Library();
                break;
            case 4:
                //Friends();
                break;
            case 5:
                User();
                break;

        }
    }


}
