package replaceme.gascalc;

import android.util.Log;

/**
 * Created by Ashwin on 2/25/2015.
 */
public class Station
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
        String[] rawArray = raw.split(",");
        Log.d(MainActivity.TAG, raw);
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
        switch (name)
        {
            case "\"regular\"":
                regular = Double.parseDouble(value);
                break;
        }
    }

    public double getRegular()
    {
        return regular;
    }
}
