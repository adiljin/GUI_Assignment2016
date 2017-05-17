package users;

import java.io.Serializable;

/**
 * Created by adil on 22/11/16.
 */
public class User implements Serializable
{
    private String login, password;
    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
