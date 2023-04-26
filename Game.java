package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String Name;
    private String Description;
    private String Genre;
    private int Price;
    private double Avrage=0;
    private int rateCount=0;

    public void setAvrage(double avrage) {
        Avrage = avrage;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    private List<String> Community= new ArrayList<>();

    public String getDescription() {
        return Description;
    }

    public List<String> getCommunity() {
        return Community;
    }

    public void setCommunity(List<String> community) {
        Community = community;
    }

    public Game(){
    }
    public Game(String name, String description, String genre, int price, int avrage) {
        Name = name;
        Description = description;
        Genre = genre;
        Price = price;
        Avrage = avrage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getInfo() {
        return Description;
    }

    public void setDescription  (String description) {
        Description = description;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public double getAvrage() {
        return Avrage;
    }
}
