import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
    private static Admin admin = new Admin("name", "password");
    private static List<User> UsersList = new ArrayList<>();

    public static void main(String[] args) {
        Enter();
    }

    public static void User() {
        System.out.println("Chose one:");
        System.out.println("1.Sing in \t 2.Sing up \t 3.Back");
        Scanner scanner = new Scanner(System.in);
        int enter = scanner.nextInt();
        switch (enter) {
            case 1:
                SingIn();
                break;
            case 2:
                SingUp();
                break;
            case 3:
                Enter();
                break;
            default:
                System.out.println("Please try again;");
                User();
                break;
        }

    }
    public static String UserNameCheck(){
        System.out.println("Enter you UserName:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        for(int i=0; i<UsersList.size(); i++){
            if (name.equals(UsersList.get(i).getUserName())){
                System.out.println("UserName already exist, please try again.");
                return UserNameCheck();
            }
        }
            return name;
    }
    public static String UserPasswordCheck(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter you Password:(Should include at least 8 chars with Capital and Small letters and Numbers)");
        String password = scanner.nextLine();
        if(!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")){
            System.out.println("Password is not acceptable. Please try again.");
            return UserPasswordCheck();
        }
        else {
            return password;
        }
    }
    public static void SingUp() {
        String name=UserNameCheck();
        String password=UserPasswordCheck();
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

    public static void SingIn() {
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

        }
    }

    public static void Admin() {
        System.out.println("Enter you UserName:");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.println("Enter you Password:");
        String password = scanner.nextLine();
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            System.out.println("Wellcome " + admin.getUserName() + "!");
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

    public static void Enter() {
        System.out.println("Enter your role:");
        System.out.println("1.User \t 2.Admin");
        Scanner sc = new Scanner(System.in);
        int role = sc.nextInt();
        switch (role) {
            case 1:
                User();
                break;
            case 2:
                Admin();
                break;
            default:
                System.out.println("Please try again;");
                Enter();
        }
        sc.close();
    }
}
