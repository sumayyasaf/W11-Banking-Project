import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private Account userAccount;

    public User(String username, String password, String firstName, Account userAccount) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.userAccount = userAccount;


    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", userAccount=" + userAccount +
                '}';
    }
}


