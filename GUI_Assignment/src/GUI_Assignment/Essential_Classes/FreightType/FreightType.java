package GUI_Assignment.Essential_Classes.FreightType;

/**
 * Created by adil on 29/11/16.
 */
public class FreightType
{
    private String name;
    private double percent;
    public FreightType(String name, double percent)
    {
        this.name = name;
        this.percent = percent;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }
    public String getName() {
        return name;
    }
    public double getPercent() {
        return percent;
    }
}
