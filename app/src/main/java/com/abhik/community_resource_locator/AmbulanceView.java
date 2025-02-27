package com.abhik.community_resource_locator;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;
import com.airbnb.lottie.LottieAnimationView;

public class AmbulanceView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AmbulanceAdapter adapter;
    private List<AmbulanceService> serviceList;
    private DatabaseReference databaseReference;
    private LottieAnimationView loadingAnimation;
    private boolean dataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_view);

        loadingAnimation = findViewById(R.id.loadingAnimation);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        serviceList = new ArrayList<>();
        adapter = new AmbulanceAdapter(serviceList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices").child("AmbulanceServices");

        // Start animation before loading data
        loadingAnimation.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serviceList.clear();
                for (DataSnapshot serviceSnapshot : snapshot.getChildren()) {
                    AmbulanceService service = serviceSnapshot.getValue(AmbulanceService.class);
                    serviceList.add(service);
                }
                adapter.notifyDataSetChanged();
                dataLoaded = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AmbulanceView.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                dataLoaded = true; // Prevent infinite loading
            }
        });

        new Handler().postDelayed(() -> {
            loadingAnimation.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }, 3000);
    }
}
