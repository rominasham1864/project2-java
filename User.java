package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private String UserName;
    private String Password;
    private String Email;
    private String Phone;
    private int Wallet = 0;
    private List<Game> library= new ArrayList<Game>();
    private List<Game> rating = new ArrayList<>();

    public List<Game> getRating() {
        return rating;
    }

    public void setRating(List<Game> rating) {
        this.rating = rating;
    }

    public void setLibrary(List<Game> library) {
        this.library = library;
    }

    public List<Game> getLibrary() {
        return library;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getWallet() {
        return Wallet;
    }

    public void setWallet(int wallet) {
        Wallet = wallet;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public User(String userName, String password, String email, String phone) {
        UserName = userName;
        Password = password;
        Email = email;
        Phone = phone;
    }
}
