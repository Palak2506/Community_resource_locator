package com.abhik.community_resource_locator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.ola.mapsdk.interfaces.OlaMapCallback;
import com.ola.mapsdk.model.OlaLatLng;
import com.ola.mapsdk.model.OlaMarkerOptions;
import com.ola.mapsdk.view.Marker;
import com.ola.mapsdk.view.OlaMap;
import com.ola.mapsdk.view.OlaMapView;
import com.ola.mapsdk.camera.MapControlSettings;  // Import MapControlSettings

public class AmbulanceView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_view);

        OlaMapView mapView = findViewById(R.id.mapview);

        // Initialize MapControlSettings
        MapControlSettings mapControlSettings = new MapControlSettings.Builder().build();

        String apiKey = getString(R.string.ola_map_api_key);

        // Pass 3 arguments: API Key, Callback, and MapControlSettings

        mapView.getMap("cY6MRtKtkA0Nu057fclSazPt6loiUFZLwMNS6F7h", new OlaMapCallback() {
            @Override
            public void onMapReady(OlaMap olaMap) {
                // Map is ready to use

                OlaLatLng olaPoint = new OlaLatLng(23.077718235622296, 76.85125490881825,0.0);

                OlaMarkerOptions markerOptions1 = new OlaMarkerOptions.Builder()
                        .setMarkerId("marker1")
                        .setPosition(olaPoint)
                        .setIsIconClickable(true)
                        .setIconRotation(0)
                        .setIsAnimationEnable(true)
                        .setIsInfoWindowDismissOnClick(true)
                        .build();

                Marker marker1 = olaMap.addMarker(markerOptions1);

                Handler handler = new Handler(Looper.getMainLooper());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        olaMap.moveCameraToLatLong(olaPoint, 15.0f, 2000);
                    }
                }, 2000);


            }

            @Override
            public void onMapError(String error) {
                // Handle map error
            }
        }, mapControlSettings);


    }
}
