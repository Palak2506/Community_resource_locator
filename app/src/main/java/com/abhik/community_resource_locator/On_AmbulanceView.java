//package com.abhik.community_resource_locator;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.mapbox.mapboxsdk.camera.CameraUpdate;
//import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
//import com.ola.mapsdk.camera.MapControlSettings;
//import com.ola.mapsdk.interfaces.OlaMapCallback;
//import com.ola.mapsdk.model.OlaLatLng;
//import com.ola.mapsdk.view.OlaMap;
//import com.ola.mapsdk.view.OlaMapView;
//
//public class On_AmbulanceView extends AppCompatActivity {
//
//    private OlaMap olaMap;
//    private DatabaseReference databaseReference;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_on_ambulance_view);
//
//        // Initialize Map View
//       OlaMapView mapView = findViewById(R.id.mapView);
//
//        // Initialize Firebase Reference (Path to AmbulanceServices)
//        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices/AmbulanceServices");
//
//        MapControlSettings mapControlSettings = new MapControlSettings.Builder().setScrollGesturesEnabled(true).setZoomGesturesEnabled(true).build();
//
//        // Load map
//        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {
//            @Override
//            public void onMapReady(OlaMap map) {
//                olaMap = map;
//
//                // Fetch and display locations from Firebase
//                fetchAndDisplayLocations();
//            }
//
//            @Override
//            public void onMapError(String error) {
//                Toast.makeText(On_AmbulanceView.this, "Map Error: " + error, Toast.LENGTH_SHORT).show();
//            }
//        },mapControlSettings);
//    }
//
//    private void fetchAndDisplayLocations() {
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    for (DataSnapshot providerSnapshot : snapshot.getChildren()) {
//                        // Fetch latitude and longitude
//                        Double latitude = providerSnapshot.child("location/latitude").getValue(Double.class);
//                        Double longitude = providerSnapshot.child("location/longitude").getValue(Double.class);
//                        String name = providerSnapshot.getKey();  // Provider name (e.g., "abhik")
//
//                        if (latitude != null && longitude != null) {
//                            Log.d("FirebaseData", "Fetched: " + name + " - Lat: " + latitude + ", Lng: " + longitude);
//                            // Add marker on Ola Map
//                            addMarkerToMap(latitude, longitude, name);
//                        }
//                    }
//                } else {
//                    Log.e("FirebaseData", "No data found in Firebase");
//                    Toast.makeText(On_AmbulanceView.this, "No location data available!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                Log.e("FirebaseData", "Error: " + error.getMessage());
//                Toast.makeText(On_AmbulanceView.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void addMarkerToMap(Double latitude, Double longitude, String title) {
//
//        OlaLatLng location = new OlaLatLng();
//
//        // Add marker
//        OlaMarker marker = new OlaMarker.Builder()
//                .position(location)
//                .title(title)
//                .build();
//
//        olaMap.addMarker(marker);
//
//        // Move camera to the first marker
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 12);
//        olaMap.moveCamera(cameraUpdate);
//    }
//
//    }
//}

package com.abhik.community_resource_locator;

import android.os.Bundle;
import android.util.Log;
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

public class On_AmbulanceView extends AppCompatActivity {

    private OlaMap olaMap;
    private DatabaseReference databaseReference;
    private boolean isFirstMarker = true;  // Flag to move camera only once

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_ambulance_view);

        // Initialize Map View
        OlaMapView mapView = findViewById(R.id.mapView);

        // Initialize Firebase Reference (Path to AmbulanceServices)
        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices/AmbulanceServices");

        MapControlSettings mapControlSettings = new MapControlSettings.Builder()
                .setScrollGesturesEnabled(true)
                .setZoomGesturesEnabled(true)
                .build();

        // Load map
        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {
            @Override
            public void onMapReady(OlaMap map) {
                olaMap = map;
                fetchAndDisplayLocations(); // Fetch and display locations from Firebase
            }

            @Override
            public void onMapError(String error) {
                Toast.makeText(On_AmbulanceView.this, "Map Error: " + error, Toast.LENGTH_SHORT).show();
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
                        String name = providerSnapshot.getKey();  // Provider name (e.g., "abhik")

                        if (latitude != null && longitude != null) {
                            Log.d("FirebaseData", "Fetched: " + name + " - Lat: " + latitude + ", Lng: " + longitude);
                            addMarkerToMap(latitude, longitude, name);
                        }
                    }
                } else {
                    Log.e("FirebaseData", "No data found in Firebase");
                    Toast.makeText(On_AmbulanceView.this, "No location data available!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("FirebaseData", "Error: " + error.getMessage());
                Toast.makeText(On_AmbulanceView.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addMarkerToMap(Double latitude, Double longitude, String title) {
        // Correct way to create OlaLatLng
        OlaLatLng location = new OlaLatLng(latitude, longitude,0);

        // Create marker options
        OlaMarkerOptions markerOptions = new OlaMarkerOptions.Builder()
                .setMarkerId(title)  // Use provider name as marker ID
                .setPosition(location)
                .setIsIconClickable(true)
                .setIsAnimationEnable(true)
                .setIsInfoWindowDismissOnClick(true)
                .build();

        // Add marker to the map
        olaMap.addMarker(markerOptions);

        // Move camera only to the first marker
        if (isFirstMarker) {
            olaMap.moveCameraToLatLong(location, 15.0f, 2000);  // Smooth camera animation
            isFirstMarker = false;
        }
    }


}
