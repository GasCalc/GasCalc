package replaceme.gascalc;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity // implements View.OnClickListener
{
    public static final String TAG = "debug_tag";
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ArrayList<Station> stations = new ArrayList<Station>();
    private boolean useMap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        GasInfo gas = new GasInfo(10025);
        gas.makeRequest();
        setupStations(gas.getRaw());

        if (!useMap)
        {
            listMode();
        } else
        {
            mapMode();
        }

    }

    private void listMode()
    {
        setContentView(R.layout.station_list);
//        findViewById(R.id.list_layout).setOnClickListener(this);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> view, View v, int n, long i)
                                        {
                                            Log.d(MainActivity.TAG, ((TextView) v).getText().toString());
                                            TextView info = (TextView) findViewById(R.id.info_view);
                                            info.setText(((TextView) v).getText());
                                            setContentView(R.layout.station_info);
                                        }
                                    });
        listView.setAdapter(new ArrayAdapter<Station>(this, R.layout.station_view, stations));
    }

    private void mapMode()
    {
        setContentView(R.layout.station_map);
        setUpMapIfNeeded();
    }

    private void setupStations(String raw)
    {
        raw = raw.substring(raw.indexOf("[") + 2, raw.indexOf("]"));
        String[] stationsArray = raw.split("\\{");

        for (String s : stationsArray)
        {
            stations.add(new Station(s));
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
//        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
            {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap()
    {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

//    @Override
//    public void onClick(View v)
//    {
//        Log.d(MainActivity.TAG, ((TextView) v).getText().toString());
//        TextView info = (TextView) findViewById(R.id.info_view);
//        info.setText(((TextView) v).getText());
//        setContentView(R.layout.station_info);
//    }
}
