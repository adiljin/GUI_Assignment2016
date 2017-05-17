package users;

import java.io.Serializable;

/**
 * Created by adil on 23/11/16.
 */
public class Cust extends User implements Serializable
{
    public Cust(String login, String password) {
        super(login, password);
    }
}
