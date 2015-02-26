package replaceme.gascalc;

/**
 * Created by Ashwin on 2/25/2015.
 */
public class Station {
    private double regular;
    private double plus;
    private double premium;
    private double diesel;
    private String brand;
    private String imgURL;
    private String address;
    private int pupdate;
    private double distance;

    public Station (String raw) {
        String[] rawArray = raw.split(",");
        for (String s : rawArray) {
            parseData(s);
        }
    }

    private void parseData (String raw) {
        int colonIndex = raw.indexOf(":");
        String name = raw.substring(0, colonIndex);
        String value = raw.substring(colonIndex + 1);
        switch (name) {
            case "\"regular\"":
                regular = Double.parseDouble(value);
                break;
        }
    }

    public double getRegular() {
        return regular;
    }
}
