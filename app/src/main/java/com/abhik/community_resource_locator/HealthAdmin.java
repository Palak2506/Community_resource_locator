package com.abhik.community_resource_locator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class HealthAdmin extends AppCompatActivity {

    private Button chooseMap, btnSave;
    private DatabaseReference databaseReference;
    private TextInputEditText nameEditText, phoneEditText, emailEditText, addressEditText, ambulanceEditText, serviceHourEditText;
    private double latitude = 0.0, longitude = 0.0; // Store retrieved location

    private static final int LOCATION_REQUEST_CODE = 1; // Request code for location intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_admin);

        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices");

        chooseMap = findViewById(R.id.btnChooseLocation);
        btnSave = findViewById(R.id.btnSave);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        addressEditText = findViewById(R.id.addressEditText);
        //ambulanceEditText = findViewById(R.id.ambulanceEditText);
        serviceHourEditText = findViewById(R.id.serviceHourEditText);

        chooseMap.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(HealthAdmin.this, "Please enter a name first", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(HealthAdmin.this, On_AmbulanceAdmin.class);
            intent.putExtra("USER_NAME", name);
            startActivityForResult(intent, LOCATION_REQUEST_CODE);
        });

        //btnSave.setOnClickListener(v -> saveDataToFirebase());

        btnSave.setOnClickListener(v -> {
            saveDataToFirebase();
        });
    }

    // Handle result from On_AmbulanceAdmin
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQUEST_CODE && resultCode == RESULT_OK) {
            latitude = data.getDoubleExtra("latitude", 0.0);
            longitude = data.getDoubleExtra("longitude", 0.0);
            Toast.makeText(this, "Location Selected: " + latitude + ", " + longitude, Toast.LENGTH_LONG).show();
        }
    }


    private void saveDataToFirebase() {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        //String vehicleCount = ambulanceEditText.getText().toString().trim();
        String serviceHours = serviceHourEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() ||  serviceHours.isEmpty()) {
            Toast.makeText(HealthAdmin.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference userRef = databaseReference.child("HealthServices").child(name);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("name",name);
        dataMap.put("address", address);
        //dataMap.put("VehicleCount", vehicleCount);
        dataMap.put("email", email);
        dataMap.put("phone", phone);
        dataMap.put("serviceHours", serviceHours);

        // Save user details first
        userRef.setValue(dataMap).addOnSuccessListener(aVoid -> {
            // Now save location separately to ensure it appears last
            HashMap<String, Double> locationMap = new HashMap<>();
            locationMap.put("latitude", latitude);
            locationMap.put("longitude", longitude);


            userRef.child("location").setValue(locationMap).addOnSuccessListener(aVoid1 -> {
                Toast.makeText(HealthAdmin.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Return to the previous activity
            }).addOnFailureListener(e ->
                    Toast.makeText(HealthAdmin.this, "Failed to save location", Toast.LENGTH_SHORT).show()
            );
        }).addOnFailureListener(e ->
                Toast.makeText(HealthAdmin.this, "Failed to save data", Toast.LENGTH_SHORT).show()
        );
    }

}

