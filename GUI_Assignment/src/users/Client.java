package users;

import java.io.Serializable;

/**
 * Created by adil on 23/11/16.
 */
public class Client extends User implements Serializable
{
    public Client(String login, String password) {
        super(login, password);
    }
}
