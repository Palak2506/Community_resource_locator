package com.abhik.community_resource_locator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import com.airbnb.lottie.LottieAnimationView;

public class user_main extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private String userEmail; // Store logged-in user's email
    private LottieAnimationView loadingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        CardView ambulance= findViewById(R.id.ambulancecard);
        CardView fireservice= findViewById(R.id.firecard);
        CardView healthservice= findViewById(R.id.healthcard);
        CardView Foodshelter= findViewById(R.id.foodcard);

        loadingAnimation = findViewById(R.id.loadingAnimation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigationView);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        userEmail = sharedPreferences.getString("email", null);

        if (!isLoggedIn || userEmail == null) {
            Intent loginIntent = new Intent(user_main.this, user_login.class);
            startActivity(loginIntent);
            finish();
            return;
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_my_account) {
                Intent accountIntent = new Intent(user_main.this, User_profile_activity.class);
                startActivity(accountIntent);
            } else if (id == R.id.nav_resources) {
                Toast.makeText(user_main.this, "Resources clicked!", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_logout) {
                // Clear login state
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Clear all stored data
                editor.apply();

                Toast.makeText(user_main.this, "Logged out!", Toast.LENGTH_SHORT).show();
                Intent logoutIntent = new Intent(user_main.this, user_login.class);
                startActivity(logoutIntent);
                finish();
            }
            return true;
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(user_main.this, AmbulanceView.class));
            }
        });

        fireservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(user_main.this, FireServiceview.class));
            }
        });

        healthservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(user_main.this, AmbulanceView.class));
            }
        });

        Foodshelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(user_main.this, AmbulanceView.class));
            }
        });

        // Start the typing animation for the TextView
        TextView animatedTextView = findViewById(R.id.animatedTextView);
        String textToAnimate = "What are you looking for ??";
        animateText(animatedTextView, textToAnimate, 65); // 65 ms delay between letters
    }

    // Function to animate text letter by letter
    private void animateText(final TextView view, final String text, final long delay) {
        final Handler handler = new Handler(Looper.getMainLooper());
        final int[] index = {0};

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (index[0] < text.length()) {
                    view.setText(text.substring(0, index[0] + 1));
                    index[0]++;
                    handler.postDelayed(this, delay);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



