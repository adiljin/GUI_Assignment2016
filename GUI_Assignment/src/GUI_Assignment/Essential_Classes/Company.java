package GUI_Assignment.Essential_Classes;

import Ships.Ship;

import java.io.Serializable;

/**
 * Created by adil on 03/11/16.
 */
public class Company implements Serializable
{
    private String id;
    private String name;
    private String address;
    private String number;
    private String e_mail;
    private Ship ship;

    public Company(String id, String name, String address, String number, String e_mail, Ship ship)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
        this.e_mail = e_mail;
        this.ship = ship;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getNumber() {
        return number;
    }
    public String getE_mail() {
        return e_mail;
    }
    public Ship getShip() {
        return ship;
    }
}
