package com.example.shubhamjain.mapsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private static String OWM_TILE_URL = "http://tile.openweathermap.org/map/%s/%d/%d/%d.png";
    private Spinner spinner;
    private String tileType = "clouds";
    private TileOverlay mTileOverlay;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_maps,container,false);
        MapView mapFragment=(MapView)v.findViewById(R.id.map);
        mapFragment.onCreate(savedInstanceState);

        mapFragment.onResume();
        mapFragment.getMapAsync(this);
        spinner=(Spinner)v.findViewById(R.id.tileType);
        String[] tileName = new String[]{"Clouds", "Temperature", "Precipitations", "Snow", "Rain", "Wind", "Sea level press."};
        ArrayAdapter<String> adp=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,tileName);
        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        tileType = "clouds";
                        break;
                    case 1:
                        tileType = "temp";
                        break;
                    case 2:
                        tileType = "precipitation";
                        break;
                    case 3:
                        tileType = "snow";
                        break;
                    case 4:
                        tileType = "rain";
                        break;
                    case 5:
                        tileType = "wind";
                        break;
                    case 6:
                        tileType = "pressure";
                        break;

                }
                if(mMap!=null){
                    mTileOverlay.remove();
                    setUpMap(mMap);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }
    private void setUpMap(GoogleMap mMap){
        mTileOverlay=mMap.addTileOverlay(new TileOverlayOptions().tileProvider(new UrlTileProvider(256,256) {
            @Override
            public URL getTileUrl(int i, int i1, int i2) {
                String fUrl = String.format(OWM_TILE_URL,tileType,i2,i,i1);
                URL url = null;
                try {
                    url = new URL(fUrl);
                }
                catch(MalformedURLException mfe) {
                    mfe.printStackTrace();
                }

                return url;
            }

        }));

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        setUpMap(mMap);
    }

}
