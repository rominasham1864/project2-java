package ir.ac.kntu;

import java.util.*;

public class test {
    public static Scanner scanner = new Scanner(System.in);
    private static List<User> UsersList = new ArrayList<>();
    private static Map<User, ArrayList<User>> Resquests = new HashMap<>();
    public static void main(String[] args) {
        User user1=new User("r", "r" ,"r", "r");
        User user2=new User("t", "t" ,"t", "t");
        User user3=new User("m", "m" ,"m", "m");
        UsersList.add(user3);
        UsersList.add(user2);
        UsersList.add(user1);

    }
}
