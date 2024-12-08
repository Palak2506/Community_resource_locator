package com.abhik.community_resource_locator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private DatabaseReference databaseReference;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        emailEditText = findViewById(R.id.mailid);
        passwordEditText = findViewById(R.id.pass);
        loginButton = findViewById(R.id.btnlogin);
        register= findViewById(R.id.regsiter);

        // Firebase Database reference
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(user_login.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    validateUser(email, password);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(user_login.this, user_register.class);
                startActivity(intent);
            }
        });
    }

//    private void validateUser(String email, String password) {
//        databaseReference.child(email.replace(".", ",")).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    String storedPassword = dataSnapshot.child("Password").getValue(String.class);
//                    if (storedPassword != null && storedPassword.equals(password)) {
//                        Toast.makeText(user_login.this, "Login successful!", Toast.LENGTH_SHORT).show();
//                        // Navigate to the next activity
//                    } else {
//                        Toast.makeText(user_login.this, "Invalid password!", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(user_login.this, "User not found. Please register first.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(user_login.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void validateUser(String email, String password) {
        // Replace "." with "_" to match the database key
        databaseReference.child(email.replace(".", "_")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.child("Password").getValue(String.class);
                    if (storedPassword != null && storedPassword.equals(password)) {
                        Toast.makeText(user_login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        // Navigate to the next activity
                        Intent intent= new Intent(user_login.this,user_main.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(user_login.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(user_login.this, "User not found. Please register first.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(user_login.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}