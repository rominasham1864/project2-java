package ir.ac.kntu;
public class Person {
    private String UserName;
    private String Password;
    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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

    public Person(String userName, String password) {
        UserName = userName;
        Password = password;
    }
}
