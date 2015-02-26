package replaceme.gascalc;

import android.util.Log;

/**
 * Created by Ashwin on 2/25/2015.
 */
public class Station implements Comparable
{
    private double regular;
    private double plus;
    private double premium;
    private double diesel;
    private String brand;
    private String imgURL;
    private String address;
    private int pupdate;
    private double distance;

    public Station(String raw)
    {
        raw = raw.substring(raw.indexOf("\\{") + 2, raw.indexOf("}"));
        String[] rawArray = raw.split(",\"");

        for (String s : rawArray)
        {
            parseData(s);
        }
    }

    public Station(double regular, double plus, double premium, double diesel, String brand, String imgURL, String address, int pupdate, double distance)
    {
        this.regular = regular;
        this.plus = plus;
        this.premium = premium;
        this.diesel = diesel;
        this.brand = brand;
        this.imgURL = imgURL;
        this.address = address;
        this.pupdate = pupdate;
        this.distance = distance;
    }

    private void parseData(String raw)
    {
        int colonIndex = raw.indexOf(":");
        String name = raw.substring(0, colonIndex);
        String value = raw.substring(colonIndex + 1);

        if (value.equals("false"))
        {
            value = "0";
        }

        if (value.contains("\""))
        {
            value = value.substring(1, value.length() - 1);
        }

        switch (name)
        {
            case "regular\"":
                regular = Double.parseDouble(value);
                break;
            case "plus\"":
                plus = Double.parseDouble(value);
                break;
            case "premium\"":
                premium = Double.parseDouble(value);
                break;
            case "diesel\"":
                diesel = Double.parseDouble(value);
                break;
            case "brand\"":
                brand = value;
                break;
            case "img\"":
                imgURL = value;
                break;
            case "address\"":
                address = value;
                break;
            case "pupdate\"":
                pupdate = Integer.parseInt(value);
                break;
            case "distance\"":
                distance = Double.parseDouble(value);
                break;
        }
    }

    //TODO: fill me
    @Override
    public String toString()
    {
        return " regular: " + regular + " plus: " + plus + " premium: " + premium + " diesel: " + diesel + " brand: " + brand + " address: " + address + " pupdate: " + pupdate + " distance: " + distance;
    }

    @Override
    public int compareTo(Object another)
    {
        if (another == null)
        {
            return 1;
        }
        return (int) (distance - ((Station) another).distance);
    }
}
