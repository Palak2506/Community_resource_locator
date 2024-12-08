package com.abhik.community_resource_locator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_profile_activity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvMobile, tvAddress;
    private Button btnLogout;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        // Initialize views
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvMobile = findViewById(R.id.tvMobile);
        tvAddress = findViewById(R.id.tvAddress);
        //btnLogout = findViewById(R.id.btnLogout);

        // Get the currently logged-in user's email from SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);

        if (email == null) {
            Toast.makeText(this, "No user logged in!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Fetch user details
        fetchUserData(email);

        // Logout button action
//        btnLogout.setOnClickListener(v -> {
//            // Clear login state
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.clear();
//            editor.apply();
//
//            Toast.makeText(User_profile_activity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
//
//            // Redirect to Login Activity
//            Intent intent = new Intent(User_profile_activity.this, user_login.class);
//            startActivity(intent);
//            finish();
//        });
    }

    private void fetchUserData(String email) {
        String sanitizedEmail = email.replace(".", "_");
        databaseReference.child(sanitizedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("Name").getValue(String.class);
                    String mobile = snapshot.child("Mobile").getValue(String.class);
                    String address = snapshot.child("Address").getValue(String.class);

                    tvName.setText("Name: " + name);
                    tvEmail.setText("Email: " + email);
                    tvMobile.setText("Mobile: " + mobile);
                    tvAddress.setText("Address: " + address);
                } else {
                    Toast.makeText(User_profile_activity.this, "User data not found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(User_profile_activity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
