package ir.ac.kntu;

public class User {
    private String UserName;
    private String Password;
    private String Email;
    private String Phone;
    private int Wallet = 0;

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
