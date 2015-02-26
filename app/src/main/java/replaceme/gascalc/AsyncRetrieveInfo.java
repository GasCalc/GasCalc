package replaceme.gascalc;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tjcha_000 on 2/25/2015.
 */
public class AsyncRetrieveInfo extends AsyncTask<String, Integer, String>
{
    @Override
    protected String doInBackground(String... params)
    {
        String raw = "";
        try
        {
            raw = makeRequestHttpClient(params[0]);
        } catch (IOException e)
        {
            Log.d(MainActivity.TAG, e.toString());
        }
        return raw;
    }

    private String makeRequestHttpClient(String url) throws IOException
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        InputStream is = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);

        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
        is.close();

        return sb.toString();
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
    }

    @Override
    protected void onPostExecute(String result)
    {
    }
}
