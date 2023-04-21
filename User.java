public class User extends Person {
    private String Email;
    private String Phone;

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

    public User(String name, String password, String email, String phone) {
        super(name, password);

    }
}
