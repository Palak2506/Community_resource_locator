package com.abhik.community_resource_locator;

//package com.abhik.community_resource_locator;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//import com.ola.mapsdk.view.OlaMapView;
//import com.ola.mapsdk.camera.MapControlSettings;
//import com.ola.mapsdk.interfaces.OlaMapCallback;
//import com.ola.mapsdk.model.OlaLatLng;
//import com.ola.mapsdk.view.OlaMap;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

//public class AmbulanceView extends AppCompatActivity {
//
//    private OlaMap olaMap;
//    private DatabaseReference databaseReference;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ambulance_view);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedLocations");
//        OlaMapView mapView = findViewById(R.id.mapView);
//
//        MapControlSettings mapControlSettings = new MapControlSettings.Builder()
//                .setScrollGesturesEnabled(true)
//                .setZoomGesturesEnabled(true)
//                .build();
//
////        mapView.getMap(getString(R.string.ola_map_api_key), new OlaMapCallback() {
////            @Override
////            public void onMapReady(OlaMap map) {
////                olaMap = map;
////                olaMap.showCurrentLocation();
////
////                olaMap.setOnMapClickedListener(latLng -> {
////                    String locationKey = databaseReference.push().getKey();
////                    databaseReference.child(locationKey).setValue(latLng);
////                    Toast.makeText(AmbulanceView.this, "Location saved: " + latLng.getLatitude() + ", " + latLng.getLongitude(), Toast.LENGTH_LONG).show();
////                    Log.d("SelectedLocation", "Lat: " + latLng.getLatitude() + ", Lng: " + latLng.getLongitude());
////                });
////            }
////
////            @Override
////            public void onMapError(String error) {
////                Log.e("OlaMapError", error);
////            }
////        }, mapControlSettings);
//    }
//}

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class AmbulanceView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AmbulanceAdapter adapter;
    private List<AmbulanceService> serviceList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        serviceList = new ArrayList<>();
        adapter = new AmbulanceAdapter(serviceList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices").child("AmbulanceServices");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serviceList.clear();
                for (DataSnapshot serviceSnapshot : snapshot.getChildren()) {
                    AmbulanceService service = serviceSnapshot.getValue(AmbulanceService.class);
                    serviceList.add(service);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AmbulanceView.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}







