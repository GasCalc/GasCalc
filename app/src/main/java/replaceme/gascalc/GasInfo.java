package replaceme.gascalc;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * Created by tjcha_000 on 2/25/2015.
 */
public class GasInfo
{
    private int zip = 0;
    private String url = "http://www.mshd.net/api/gasprices/";
    private String raw = "";

    public GasInfo(int zip)
    {
        this.zip = zip;
    }

    public void makeRequest()
    {
        url = "http://www.mshd.net/api/gasprices/" + zip;
        AsyncTask async = new AsyncRetrieveInfo().execute("" + zip);
        try
        {
            raw = async.get().toString();
        } catch (InterruptedException e)
        {
            Log.d(MainActivity.TAG, e.toString());
        } catch (ExecutionException e)
        {
            Log.d(MainActivity.TAG, e.toString());
        }
    }

    //TODO: get rid of me eventually
    public String getRaw()
    {
        return raw;
    }
}
