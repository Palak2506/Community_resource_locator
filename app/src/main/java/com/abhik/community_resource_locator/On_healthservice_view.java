package com.abhik.community_resource_locator;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ola.mapsdk.camera.MapControlSettings;
import com.ola.mapsdk.interfaces.OlaMapCallback;
import com.ola.mapsdk.model.OlaLatLng;

import com.ola.mapsdk.model.OlaMarkerOptions;
import com.ola.mapsdk.view.OlaMap;
import com.ola.mapsdk.view.OlaMapView;

import com.airbnb.lottie.LottieAnimationView;

public class On_healthservice_view extends AppCompatActivity {

    private OlaMap olaMap;
    private DatabaseReference databaseReference;
    private boolean isFirstMarker = true;
    private LottieAnimationView loadingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_ambulance_view);

        OlaMapView mapView = findViewById(R.id.mapView);
        loadingAnimation = findViewById(R.id.loadingAnimation);

        loadingAnimation.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            loadingAnimation.setVisibility(View.GONE);

        }, 3000);

        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices/HealthServices");

        MapControlSettings mapControlSettings = new MapControlSettings.Builder()
                .setScrollGesturesEnabled(true)
                .setZoomGesturesEnabled(true)
                .build();

        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {

            @Override
            public void onMapReady(OlaMap map) {

                olaMap = map;
                fetchAndDisplayLocations();
            }
            @Override
            public void onMapError(String error) {
                Toast.makeText(On_healthservice_view.this, "Map Error: " + error, Toast.LENGTH_SHORT).show();
            }
        }, mapControlSettings);
    }

    private void fetchAndDisplayLocations() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot providerSnapshot : snapshot.getChildren()) {
                        // Fetch latitude and longitude
                        Double latitude = providerSnapshot.child("location/latitude").getValue(Double.class);
                        Double longitude = providerSnapshot.child("location/longitude").getValue(Double.class);
                        String name = providerSnapshot.getKey();

                        if (latitude != null && longitude != null) {
                            Log.d("FirebaseData", "Fetched: " + name + " - Lat: " + latitude + ", Lng: " + longitude);
                            addMarkerToMap(latitude, longitude, name);
                        }
                    }
                }
                else {
                    Log.e("FirebaseData", "No data found in Firebase");
                    Toast.makeText(On_healthservice_view.this, "No location data available!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("FirebaseData", "Error: " + error.getMessage());
                Toast.makeText(On_healthservice_view.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addMarkerToMap(Double latitude, Double longitude, String title) {

        OlaLatLng location = new OlaLatLng(latitude, longitude,0);

        OlaMarkerOptions markerOptions = new OlaMarkerOptions.Builder()
                .setMarkerId(title)
                .setPosition(location)
                .setIsIconClickable(true)
                .setIsAnimationEnable(true)
                .setIsInfoWindowDismissOnClick(true)
                .build();

        olaMap.addMarker(markerOptions);

        if (isFirstMarker) {
            olaMap.moveCameraToLatLong(location, 15.0f, 2000);
            isFirstMarker = false;
        }
    }


}
