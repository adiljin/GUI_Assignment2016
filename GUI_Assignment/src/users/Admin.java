package users;

import java.io.Serializable;

/**
 * Created by adil on 23/11/16.
 */
public class Admin extends User implements Serializable
{
    public Admin(String login, String password)
    {
        super(login, password);
    }
}
