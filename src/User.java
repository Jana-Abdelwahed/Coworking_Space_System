import java.io.*;
import java.util.ArrayList;

public abstract class User {
    private String name;
    private String password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    User() {

    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
