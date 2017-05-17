package GUI_Assignment.Essential_Classes;

import Ships.Ship;
import GUI_Assignment.Routes_Management.Route;

import java.io.Serializable;

/**
 * Created by adil on 10/11/16.
 */
public class Freight implements Serializable
{
    private Customer customer;
    private String id;
    private String type;
    private String weight;
    private Ship ship;
    private int price;
    private int route;
    private String routeType;
    private Route chosenRoute;

    public Freight(Customer customer, String id, String type, String weight, Ship ship, int price, int route, String routeType, Route chosenRoute)
    {
        this.customer = customer;
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.ship = ship;
        this.price = price;
        this.route = route;
        this.routeType = routeType;
        this.chosenRoute = chosenRoute;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public Customer getCustomer() {
        return customer;
    }
    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getWeight() {
        return weight;
    }
    public Ship getShip() {
        return ship;
    }
    public int getPrice() {
        return price;
    }
    public int getRoute() {
        return route;
    }
    public String getRouteType() {
        return routeType;
    }
    public Route getChosenRoute() {
        return chosenRoute;
    }
}
