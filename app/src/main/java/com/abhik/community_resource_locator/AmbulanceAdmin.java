//package com.abhik.community_resource_locator;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import java.util.HashMap;
//
//public class AmbulanceAdmin extends AppCompatActivity {
//
//    private Button chooseMap, btnSave;
//    private DatabaseReference databaseReference;
//    private TextInputEditText nameEditText, phoneEditText, emailEditText, addressEditText, ambulanceEditText, serviceHourEditText;
//    private double latitude = 0.0, longitude = 0.0;
//
//    private static final int LOCATION_REQUEST_CODE = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ambulance_admin);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices");
//
//        chooseMap = findViewById(R.id.btnChooseLocation);
//        btnSave = findViewById(R.id.btnSave);
//
//        nameEditText = findViewById(R.id.nameEditText);
//        phoneEditText = findViewById(R.id.phoneEditText);
//        emailEditText = findViewById(R.id.emailEditText);
//        addressEditText = findViewById(R.id.addressEditText);
//        ambulanceEditText = findViewById(R.id.ambulanceEditText);
//        serviceHourEditText = findViewById(R.id.serviceHourEditText);
//
//        chooseMap.setOnClickListener(v -> {
//            String name = nameEditText.getText().toString().trim();
//            if (name.isEmpty()) {
//                Toast.makeText(AmbulanceAdmin.this, "Please enter a name first", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            Intent intent = new Intent(AmbulanceAdmin.this, On_AmbulanceAdmin.class);
//            intent.putExtra("USER_NAME", name);
//            startActivityForResult(intent, LOCATION_REQUEST_CODE);
//        });
//
//        btnSave.setOnClickListener(v -> {
//            saveDataToFirebase();
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == LOCATION_REQUEST_CODE && resultCode == RESULT_OK) {
//            latitude = data.getDoubleExtra("latitude", 0.0);
//            longitude = data.getDoubleExtra("longitude", 0.0);
//            Toast.makeText(this, "Location Selected: " + latitude + ", " + longitude, Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//    private void saveDataToFirebase() {
//        String name = nameEditText.getText().toString().trim();
//        String phone = phoneEditText.getText().toString().trim();
//        String email = emailEditText.getText().toString().trim();
//        String address = addressEditText.getText().toString().trim();
//        String ambulanceCount = ambulanceEditText.getText().toString().trim();
//        String serviceHours = serviceHourEditText.getText().toString().trim();
//
//        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || ambulanceCount.isEmpty() || serviceHours.isEmpty()) {
//            Toast.makeText(AmbulanceAdmin.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        DatabaseReference userRef = databaseReference.child("AmbulanceServices").child(name);
//
//        HashMap<String, Object> dataMap = new HashMap<>();
//        dataMap.put("name",name);
//        dataMap.put("address", address);
//        dataMap.put("ambulanceCount", ambulanceCount);
//        dataMap.put("email", email);
//        dataMap.put("phone", phone);
//        dataMap.put("serviceHours", serviceHours);
//
//        // Save user details first
//        userRef.setValue(dataMap).addOnSuccessListener(aVoid -> {
//            // Now save location separately to ensure it appears last
//            HashMap<String, Double> locationMap = new HashMap<>();
//            locationMap.put("latitude", latitude);
//            locationMap.put("longitude", longitude);
//
//            userRef.child("location").setValue(locationMap).addOnSuccessListener(aVoid1 -> {
//                Toast.makeText(AmbulanceAdmin.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
//                finish(); // Return to the previous activity
//            }).addOnFailureListener(e ->
//                    Toast.makeText(AmbulanceAdmin.this, "Failed to save location", Toast.LENGTH_SHORT).show()
//            );
//        }).addOnFailureListener(e ->
//                Toast.makeText(AmbulanceAdmin.this, "Failed to save data", Toast.LENGTH_SHORT).show()
//        );
//    }
//
//}
//

package com.abhik.community_resource_locator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class AmbulanceAdmin extends AppCompatActivity {

    private static final int LOCATION_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 100;

    private Button chooseMap, btnSave, btnUploadID;
    private ImageView imagePreview;
    private TextInputEditText nameEditText, phoneEditText, emailEditText, addressEditText, ambulanceEditText, serviceHourEditText;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Uri imageUri;
    private double latitude = 0.0, longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_admin);

        databaseReference = FirebaseDatabase.getInstance().getReference("MarkedServices");
        storageReference = FirebaseStorage.getInstance().getReference("ambulance_ids");

        chooseMap = findViewById(R.id.btnChooseLocation);
        btnSave = findViewById(R.id.btnSave);
        btnUploadID = findViewById(R.id.btnUploadID);
        imagePreview = findViewById(R.id.imagePreview);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        addressEditText = findViewById(R.id.addressEditText);
        ambulanceEditText = findViewById(R.id.ambulanceEditText);
        serviceHourEditText = findViewById(R.id.serviceHourEditText);

        chooseMap.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(AmbulanceAdmin.this, "Please enter a name first", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(AmbulanceAdmin.this, On_AmbulanceAdmin.class);
            intent.putExtra("USER_NAME", name);
            startActivityForResult(intent, LOCATION_REQUEST_CODE);
        });

        btnUploadID.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select ID"), PICK_IMAGE_REQUEST);
        });

        btnSave.setOnClickListener(v -> saveDataToFirebase());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            latitude = data.getDoubleExtra("latitude", 0.0);
            longitude = data.getDoubleExtra("longitude", 0.0);
            Toast.makeText(this, "Location Selected: " + latitude + ", " + longitude, Toast.LENGTH_LONG).show();
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imagePreview.setImageURI(imageUri);
        }
    }

    private void saveDataToFirebase() {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String ambulanceCount = ambulanceEditText.getText().toString().trim();
        String serviceHours = serviceHourEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || ambulanceCount.isEmpty() || serviceHours.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference userRef = databaseReference.child("AmbulanceServices").child(name);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", name);
        dataMap.put("phone", phone);
        dataMap.put("email", email);
        dataMap.put("address", address);
        dataMap.put("ambulanceCount", ambulanceCount);
        dataMap.put("serviceHours", serviceHours);

        userRef.setValue(dataMap).addOnSuccessListener(aVoid -> {
            HashMap<String, Double> locationMap = new HashMap<>();
            locationMap.put("latitude", latitude);
            locationMap.put("longitude", longitude);

            userRef.child("location").setValue(locationMap).addOnSuccessListener(aVoid1 -> {
                Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }).addOnFailureListener(e ->
                    Toast.makeText(this, "Failed to save location", Toast.LENGTH_SHORT).show()
            );

            if (imageUri != null) {
                StorageReference fileRef = storageReference.child(name + "_id.jpg");
                fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                    fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        userRef.child("idProofUrl").setValue(uri.toString());
                    });
                }).addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to upload ID Proof", Toast.LENGTH_SHORT).show()
                );
            }
        }).addOnFailureListener(e ->
                Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
        );
    }
}
