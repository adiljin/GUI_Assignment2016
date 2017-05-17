package Ships;

import java.io.Serializable;

/**
 * Created by adil on 11/11/16.
 */
public class Ship implements Serializable

{
    private String id;
    private String name;
    private String address;

    private int weightMin;
    private int weightMax;
    private int pricePerYear;
    private int price;
    private int years;
    private String typeLease;

    public Ship(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;


        this.weightMax=0;
        this.weightMin=0;
        this.price=0;
        this.pricePerYear=0;
        this.years = 0;
        this.typeLease = "";
    }

    public void setShipID(String id) {
        this.id = id;
    }
    public void setShipName(String name) {
        this.name = name;
    }
    public void setShipAddress(String address) {
        this.address = address;
    }
    public void setWeightMin(int weightMin) {
        this.weightMin = weightMin;
    }
    public void setWeightMax(int weightMax) {
        this.weightMax = weightMax;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getShipID() {
        return id;
    }
    public String getShipName() {
        return name;
    }
    public String getShipAddress() {
        return address;
    }
    public int getWeightMin() {
        return weightMin;
    }
    public int getWeightMax() {
        return weightMax;
    }
    public int getPrice() {
        return price;
    }
    public int getPricePerYear() {
        return pricePerYear;
    }
    public int getYears() {
        return years;
    }
    public String getTypeLease() {
        return typeLease;
    }
}
