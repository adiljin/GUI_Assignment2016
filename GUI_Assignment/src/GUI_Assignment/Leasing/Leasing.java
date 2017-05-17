package GUI_Assignment.Leasing;

/**
 * Created by adil on 15/11/16.
 */
public class Leasing
{
    public static int getLeasing(String type, int years, int price)
    {
        int result = 0;
        double percent;
        if(type.equals("contract"))
        {
            percent = (price * years) / 0.9;
            price = (int)percent;
            result = price;
        }
        else if(type.equals("operating"))
        {
            percent = (price * years) / 0.7;
            price = (int)percent;
            result = price;
        }
        return result;
    }
}
