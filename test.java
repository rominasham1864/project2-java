package ir.ac.kntu;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        System.out.println("Enter you Password:(Should include at least 8 chars with Capital and Small letters and Numbers)");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.next();
        System.out.println(password);
    }
}
