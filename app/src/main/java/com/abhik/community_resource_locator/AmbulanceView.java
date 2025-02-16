//package com.abhik.community_resource_locator;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.location.Location;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.mapbox.mapboxsdk.geometry.LatLng;
//import com.ola.mapsdk.interfaces.OlaMapCallback;
//import com.ola.mapsdk.model.OlaLatLng;
//import com.ola.mapsdk.model.OlaMarkerOptions;
//import com.ola.mapsdk.view.Marker;
//import com.ola.mapsdk.view.OlaMap;
//import com.ola.mapsdk.view.OlaMapView;
//import com.ola.mapsdk.camera.MapControlSettings;  // Import MapControlSettings
//
//
//// temp
//import android.location.Location;
//import android.location.LocationManager;
//import android.location.LocationProvider;
//import android.content.Context;
//import androidx.core.app.ActivityCompat;
//import android.content.pm.PackageManager;
//import android.Manifest;
//import java.util.List;
//
//import java.util.List;
//
//public class AmbulanceView extends AppCompatActivity {
//
//
//
//
//    //OlaMap olaMap2;
//
//    private OlaMap olamap;
//
//
//
//
////    private Location getLastKnownLocation(Context context) {
////        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
////        if (mLocationManager == null) return null;
////
////        List<String> providers = mLocationManager.getProviders(true);
////        Location bestLocation = null;
////
////        for (String provider : providers) {
////            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
////                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////                return null;  // Permissions not granted
////            }
////
////            Location l = mLocationManager.getLastKnownLocation(provider);
////            if (l != null && (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy())) {
////                bestLocation = l;
////            }
////        }
////
////        if (bestLocation == null) {
////            // Fallback location
////            bestLocation = new Location("");
////            bestLocation.setLatitude(23.0777);
////            bestLocation.setLongitude(76.8512);
////        }
////
////        return bestLocation;
////    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ambulance_view);
//
////        OlaMapView mapView = findViewById(R.id.mapview);
////
////
////
////        MapControlSettings mapControlSettings = new MapControlSettings.Builder().build();
////
////        String apiKey = getString(R.string.ola_map_api_key);
////
////        mapView.getMap("cY6MRtKtkA0Nu057fclSazPt6loiUFZLwMNS6F7h", new OlaMapCallback() {
////            @Override
////            public void onMapReady(OlaMap olaMap) {
////                // Map is ready to use
////
//////                OlaLatLng olaPoint = new OlaLatLng(23.077718235622296, 76.85125490881825,0.0);
////
////                olaMap2=olaMap;
////
////                OlaLatLng currentLocation = olaMap2 != null ? olaMap2.getCurrentLocation() : null;
////
////
////
////                Handler handler = new Handler(Looper.getMainLooper());
////
////
////                if (currentLocation != null) {
////                    // Use the current location
////                    olaMap2.moveCameraToLatLong(currentLocation, 15.0f, 2000);
////
//////                    OlaMarkerOptions markerOptions1 = new OlaMarkerOptions.Builder()
//////                            .setMarkerId("marker1")
//////                            .setPosition(currentLocation)
//////                            .setSnippet("Ambulance 1")
//////                            .setIsIconClickable(true)
//////                            .setIconRotation(0)
//////                            .setIsAnimationEnable(true)
//////                            .setIsInfoWindowDismissOnClick(true)
//////                            .build();
//////
//////                    Marker marer1 = olaMap.addMarker(markerOptions1);
////
////                } else {
////                    // Handle the case where the location is not available
////                    Toast.makeText(AmbulanceView.this,"Unable to fetch location",Toast.LENGTH_SHORT).show();
////                    Log.d("showLocation", "onMapReady: "+ currentLocation);
////                }
////
//////                handler.postDelayed(new Runnable() {
//////                    @Override
//////                    public void run() {
//////                        olaMap.moveCameraToLatLong(olaPoint, 15.0f, 2000);
//////                    }
//////                }, 2000);
////
////
////            }
////
////
////            @Override
////            public void onMapError(String error) {
////                // Handle map error
////            }
////        }, mapControlSettings);
////
////        Location currentLocation = getLastKnownLocation(this);
////        if (currentLocation != null) {
////            OlaLatLng olaCurrentLocation = new OlaLatLng(currentLocation.getLatitude(), currentLocation.getLongitude(), 0.0);
////            olaMap2.moveCameraToLatLong(olaCurrentLocation, 15.0f, 2000);
////        } else {
////            Toast.makeText(this, "Unable to fetch location", Toast.LENGTH_SHORT).show();
////        }
//
//
////
//       OlaMapView mapView = findViewById(R.id.mapView);
//       MapControlSettings mapControlSettings = new MapControlSettings.Builder().build();
//
//        mapView.getMap("cY6MRtKtkA0Nu057fclSazPt6loiUFZLwMNS6F7h", new OlaMapCallback() {
//            public void onMapReady(OlaMap map) {
//                olaMap = map;
//
//                // Show current location on the map
//                olaMap.showCurrentLocation();
//
//                // Fetch current location
//                OlaLatLng currentLocation = olaMap.getCurrentLocation();
//                if (currentLocation != null) {
//                    olaMap.zoomToLocation(currentLocation, 15.0);  // Zoom to the current location
//                    Log.d("CurrentLocation", "Lat: " + currentLocation.getLatitude() + ", Lng: " + currentLocation.getLongitude());
//                } else {
//                    Toast.makeText(AmbulanceView.this, "Unable to fetch current location", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onMapError(String error) {
//                Log.e("OlaMapError", "Error: " + error);
//            }
//        }, mapControlSettings);
//  }
//}
//
//
//
//

//package com.abhik.community_resource_locator;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//import com.ola.mapsdk.interfaces.OlaMapCallback;
//import com.ola.mapsdk.model.OlaLatLng;
//import com.ola.mapsdk.view.OlaMap;
//import com.ola.mapsdk.view.OlaMapView;
//import com.ola.mapsdk.camera.MapControlSettings;
//import com.ola.mapsdk.view.Marker;
//import com.ola.mapsdk.model.OlaMarkerOptions;
//
//public class AmbulanceView extends AppCompatActivity {
//
//    private OlaMap olaMap;
//    private Marker marker1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ambulance_view);
//
//        OlaMapView mapView = findViewById(R.id.mapView);
//        MapControlSettings mapControlSettings = new MapControlSettings.Builder().build();
//        String apiKey = getString(R.string.ola_map_api_key);
//
//        mapView.getMap(apiKey, new OlaMapCallback() {
//            @Override
//            public void onMapReady(OlaMap map) {
//                olaMap = map;
//
//                olaMap.showCurrentLocation();
//
//                OlaLatLng currentLocation = olaMap.getCurrentLocation();
//                if (currentLocation != null) {
//
//                    OlaMarkerOptions markerOptions1 = new OlaMarkerOptions.Builder()
//                            .setMarkerId("001")
//                            .setPosition(currentLocation)
//                            .setSnippet("Ambulance-1")
//                            .setIsIconClickable(true)
//                            .setIconRotation(0f)
//                            .setIsAnimationEnable(true)
//                            .setIsInfoWindowDismissOnClick(true)
//                            .build();
//
//                    marker1 = olaMap.addMarker(markerOptions1);
//
//                    olaMap.moveCameraToLatLong(currentLocation, 15.0f, 2000);
//                    Log.d("CurrentLocation", "Lat: " + currentLocation.getLatitude() + ", Lng: " + currentLocation.getLongitude());
//                }
//                else{
//                    Toast.makeText(AmbulanceView.this, "Unable to fetch current location", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onMapError(String error) {
//                Log.e("OlaMapError", "Error: " + error);
//            }
//        }, mapControlSettings);
//    }
//}


package com.abhik.community_resource_locator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.ola.mapsdk.view.OlaMapView;
import com.ola.mapsdk.camera.MapControlSettings;
import com.ola.mapsdk.interfaces.OlaMapCallback;
import com.ola.mapsdk.model.OlaLatLng;
import com.ola.mapsdk.view.OlaMap;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AmbulanceView extends AppCompatActivity {

    private OlaMap olaMap;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_view);

        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedLocations");
        OlaMapView mapView = findViewById(R.id.mapView);

        MapControlSettings mapControlSettings = new MapControlSettings.Builder()
                .setScrollGesturesEnabled(true)
                .setZoomGesturesEnabled(true)
                .build();

        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {
            @Override
            public void onMapReady(OlaMap map) {
                olaMap = map;
                olaMap.showCurrentLocation();

                olaMap.setOnMapClickedListener(latLng -> {
                    String locationKey = databaseReference.push().getKey();
                    databaseReference.child(locationKey).setValue(latLng);
                    Toast.makeText(AmbulanceView.this, "Location saved: " + latLng.getLatitude() + ", " + latLng.getLongitude(), Toast.LENGTH_LONG).show();
                    Log.d("SelectedLocation", "Lat: " + latLng.getLatitude() + ", Lng: " + latLng.getLongitude());
                });
            }

            @Override
            public void onMapError(String error) {
                Log.e("OlaMapError", error);
            }
        }, mapControlSettings);
    }
}



