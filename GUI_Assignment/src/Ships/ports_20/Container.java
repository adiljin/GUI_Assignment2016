package Ships.ports_20;

import Ships.Ship;

/**
 * Created by adil on 10/11/16.
 */
public class Container extends Ship
{
    private int Min = 7000;
    private int Max = 10000;

    private int weightMin;
    private int weightMax;
    private int pricePerYear;
    private int price;
    private int years;
    private String typeLease;

    public Container(String id, String name, String address, int price, int years, String typeLease)
    {
        super(id,name,address);
        this.weightMin = Min;
        this.weightMax = Max;
        this.price = price;
        this.pricePerYear = 6000;
        this.years = years;
        this.typeLease = typeLease;
    }

    public void setWeightMin(int weightMin) {
        this.weightMin = weightMin;
    }
    public void setWeightMax(int weightMax) {
        this.weightMax = weightMax;
    }

    public int getWeightMin() {
        return weightMin;
    }
    public int getWeightMax() {
        return weightMax;
    }

    public int getPricePerYear() {
        return pricePerYear;
    }

    public int getPrice() {
        return price;
    }

    public int getYears() {
        return years;
    }

    public String getTypeLease() {
        return typeLease;
    }
}
