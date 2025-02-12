package com.abhik.community_resource_locator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class user_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        EditText name = findViewById(R.id.etname);
        EditText email = findViewById(R.id.etmail);
        EditText mobileNumber = findViewById(R.id.etmobnum);
        EditText address = findViewById(R.id.etaddress);
        EditText password = findViewById(R.id.etpass);
        EditText confirmPassword = findViewById(R.id.etconfpass);
        Button registerButton = findViewById(R.id.btnregister);
        ImageView back= findViewById(R.id.icon_back);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("Users");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_register.this, user_login.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(v -> {
            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userMobile = mobileNumber.getText().toString().trim();
            String userAddress = address.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            String userConfirmPassword = confirmPassword.getText().toString().trim();


            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userEmail) ||
                    TextUtils.isEmpty(userMobile) || TextUtils.isEmpty(userAddress) ||
                    TextUtils.isEmpty(userPassword) || TextUtils.isEmpty(userConfirmPassword)) {
                Toast.makeText(user_register.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                Toast.makeText(user_register.this, "Enter a valid email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!userPassword.equals(userConfirmPassword)) {
                Toast.makeText(user_register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userMobile.length() != 10 || !TextUtils.isDigitsOnly(userMobile)) {
                Toast.makeText(user_register.this, "Enter a valid mobile number!", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, String> userData = new HashMap<>();
            userData.put("Name", userName);
            userData.put("Email", userEmail);
            userData.put("Mobile", userMobile);
            userData.put("Address", userAddress);
            userData.put("Password", userPassword); 

            // Save user data in Firebase
            usersRef.child(userEmail.replace(".", "_")).setValue(userData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(user_register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        // Redirect to login or another activity if needed
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(user_register.this, "Registration failed! " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
