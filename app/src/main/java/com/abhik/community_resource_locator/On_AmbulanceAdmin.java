package com.abhik.community_resource_locator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.ola.mapsdk.camera.MapControlSettings;
import com.ola.mapsdk.interfaces.OlaMapCallback;
import com.ola.mapsdk.model.OlaLatLng;
import com.ola.mapsdk.view.OlaMap;
import com.ola.mapsdk.view.OlaMapView;

public class On_AmbulanceAdmin extends AppCompatActivity {

    private OlaMap olaMap;
    private ImageButton curr_location;
    private String userName; // Store the username received from intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_ambulance_admin);

        OlaMapView mapView = findViewById(R.id.mapView);
        curr_location = findViewById(R.id.btnChooseLocation);

        userName = getIntent().getStringExtra("USER_NAME"); // Get username from intent

        MapControlSettings mapControlSettings = new MapControlSettings.Builder().setScrollGesturesEnabled(true).setZoomGesturesEnabled(true).build();

        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {
            @Override
            public void onMapReady(OlaMap map) {
                olaMap = map;

                curr_location.setOnClickListener(v -> {
                    olaMap.showCurrentLocation();
                    OlaLatLng currentLocation = olaMap.getCurrentLocation();
                    if (currentLocation != null) {
                        olaMap.moveCameraToLatLong(currentLocation, 15.0f, 2000);
                    } else {
                        Toast.makeText(On_AmbulanceAdmin.this, "Unable to fetch current location", Toast.LENGTH_SHORT).show();
                    }
                });

                olaMap.setOnMapClickedListener(latLng -> {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("latitude", latLng.getLatitude());
                    resultIntent.putExtra("longitude", latLng.getLongitude());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                });
            }

            @Override
            public void onMapError(String error) {
                Toast.makeText(On_AmbulanceAdmin.this, "Map Error: " + error, Toast.LENGTH_SHORT).show();
            }
        },mapControlSettings);
    }
}

