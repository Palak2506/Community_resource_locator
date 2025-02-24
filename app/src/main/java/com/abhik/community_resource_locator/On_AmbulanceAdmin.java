package com.abhik.community_resource_locator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ola.mapsdk.camera.MapControlSettings;
import com.ola.mapsdk.interfaces.OlaMapCallback;
import com.ola.mapsdk.model.OlaLatLng;
import com.ola.mapsdk.view.OlaMap;
import com.ola.mapsdk.view.OlaMapView;

public class On_AmbulanceAdmin extends AppCompatActivity {


    private OlaMap olaMap;
    private DatabaseReference databaseReference;
    private ImageButton curr_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_ambulance_admin);

        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedLocations");
        OlaMapView mapView = findViewById(R.id.mapView);
        curr_location= findViewById(R.id.btnChooseLocation);

        MapControlSettings mapControlSettings = new MapControlSettings.Builder().setScrollGesturesEnabled(true).setZoomGesturesEnabled(true).build();

        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {
            @Override
            public void onMapReady(OlaMap map) {

                olaMap = map;

                curr_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        olaMap.showCurrentLocation();

                        OlaLatLng currentLocation = olaMap.getCurrentLocation();
                        if (currentLocation != null) {
                            //olaMap.zoomToLocation(currentLocation, 15.0);
                            olaMap.moveCameraToLatLong(currentLocation, 15.0f, 2000);
                            Log.d("CurrentLocation", "Lat: " + currentLocation.getLatitude() + ", Lng: " + currentLocation.getLongitude());
                        }
                        else {
                            Toast.makeText(On_AmbulanceAdmin.this, "Unable to fetch current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                olaMap.setOnMapClickedListener(latLng -> {
                    String locationKey = databaseReference.push().getKey();
                    databaseReference.child(locationKey).setValue(latLng);
                    Toast.makeText(On_AmbulanceAdmin.this, "Location saved: " + latLng.getLatitude() + ", " + latLng.getLongitude(), Toast.LENGTH_LONG).show();
                    Log.d("SelectedLocation", "Lat: " + latLng.getLatitude() + ", Lng: " + latLng.getLongitude());
                });
            }

            @Override
            public void onMapError(String error) {
                // Handle map error
            }
        }, mapControlSettings);

    }
}


//package com.abhik.community_resource_locator;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.ola.maps.sdk.core.client.Platform;
//import com.ola.maps.sdk.core.config.PlatformConfig;
//import com.ola.maps.sdk.forwardgeocoding.client.ForwardGeocodingClient;
//import com.ola.maps.sdk.model.geocode.request.ForwardGeocodingRequest;
//import com.ola.maps.sdk.model.geocode.response.ForwardGeocodingResponse;
//import com.ola.maps.sdk.model.nearbysearch.request.NearbySearchRequest;
//import com.ola.maps.sdk.model.nearbysearch.response.NearbySearchResponse;
//import com.ola.maps.sdk.model.placedetails.request.PlaceDetailsRequest;
//import com.ola.maps.sdk.model.placedetails.response.PlaceDetailsResponse;
//import com.ola.maps.sdk.model.places.request.AutocompleteRequest;
//import com.ola.maps.sdk.model.places.response.AutocompleteResponse;
//import com.ola.maps.sdk.model.reversegeocode.request.ReverseGeocodingRequest;
//import com.ola.maps.sdk.model.reversegeocode.response.ReverseGeocodingResponse;
//import com.ola.maps.sdk.model.textsearch.request.TextSearchRequest;
//import com.ola.maps.sdk.model.textsearch.response.TextSearchResponse;
//import com.ola.maps.sdk.nearbysearch.client.NearbySearchClient;
//import com.ola.maps.sdk.placedetails.client.PlaceDetailsClient;
//import com.ola.maps.sdk.places.client.PlacesClient;
//import com.ola.maps.sdk.reversegeocoding.client.ReverseGeocodingClient;
//import com.ola.maps.sdk.textsearch.client.TextSearchClient;
//import com.ola.mapsdk.camera.MapControlSettings;
//import com.ola.mapsdk.interfaces.OlaMapCallback;
//import com.ola.mapsdk.view.OlaMap;
//import com.ola.mapsdk.view.OlaMapView;
//
//
//public class On_AmbulanceAdmin extends AppCompatActivity {
//
//    private OlaMap olaMap;
//    private PlatformConfig config;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_on_ambulance_admin);
//
//        // Initialize PlatformConfig
//        config = new PlatformConfig.Builder()
//                .apiKey(String.valueOf(R.string.ola_map_api_key))
//                .baseUrl("https://api.olamaps.com/")
//                .maxRetryAttempts(3)
//                .build();
//
//        OlaMapView mapView = findViewById(R.id.mapView);
//
//        MapControlSettings mapControlSettings = new MapControlSettings.Builder()
//                .setScrollGesturesEnabled(true)
//                .setZoomGesturesEnabled(true)
//                .build();
//
//        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {
//            @Override
//            public void onMapReady(OlaMap map) {
//                olaMap = map;
//                olaMap.showCurrentLocation();
//                performGeolocationServices();
//            }
//
//            @Override
//            public void onMapError(String error) {
//                // Handle map error
//            }
//        }, mapControlSettings);
//    }
//
//    private void performGeolocationServices() {
//        // Autocomplete
//        PlacesClient placesClient = Platform.getPlacesClient(config);
//        AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder()
//                .queryText("airport")
//                .build();
//        AutocompleteResponse autocompleteResponse = placesClient.autocomplete(autocompleteRequest);
//
//        // Reverse Geocoding
//        ReverseGeocodingClient clientReverseGeocoding = Platform.getReverseGeocodingClient(config);
//        ReverseGeocodingRequest reverseGeocodingRequest = new ReverseGeocodingRequest.Builder()
//                .latlng("12.931316595874005,77.61649243443775")
//                .build();
//        ReverseGeocodingResponse reverseGeocodingResponse = clientReverseGeocoding.reverseGeocode(reverseGeocodingRequest);
//
//        // Forward Geocoding
//        ForwardGeocodingClient clientForwardGeocoding = Platform.getForwardGeocodingClient(config);
//        ForwardGeocodingRequest forwardGeocodingRequest = new ForwardGeocodingRequest.Builder()
//                .address("ola campus, bengaluru")
//                .build();
//        ForwardGeocodingResponse forwardGeocodingResponse = clientForwardGeocoding.forwardGeocode(forwardGeocodingRequest);
//
//        // Place Details
//        PlaceDetailsClient placeDetailsClient = Platform.getPlaceDetailsClient(config);
//        PlaceDetailsRequest placeDetailsRequest = new PlaceDetailsRequest.Builder()
//                .placeId("ola-platform:209ff095-37b5-49e8-ba31-91a493b2f554")
//                .build();
//        PlaceDetailsResponse placeDetailsResponse = placeDetailsClient.placeDetails(placeDetailsRequest);
//
//        // Nearby Search
//        NearbySearchClient nearbySearchClient = Platform.getNearbySearchClient(config);
//        NearbySearchRequest nearbySearchRequest = new NearbySearchRequest.Builder()
//                .limit(1)
//                .location("12.931316595874005,77.61649243443775")
//                .build();
//        NearbySearchResponse nearbySearchResponse = nearbySearchClient.nearbySearch(nearbySearchRequest);
//
//        // Text Search
//        TextSearchClient textSearchClient = Platform.getTextSearchClient(config);
//        TextSearchRequest textSearchRequest = new TextSearchRequest.Builder()
//                .queryText("hospital in koramangala")
//                .location("12.931316595874005,77.61649243443775")
//                .build();
//        TextSearchResponse textSearchResponse = textSearchClient.textSearch(textSearchRequest);
//    }
//}
