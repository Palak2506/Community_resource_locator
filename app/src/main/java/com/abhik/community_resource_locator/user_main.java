//package com.abhik.community_resource_locator;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.Objects;
//
//public class user_main extends AppCompatActivity {
//
//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle toggle;
//    private NavigationView navigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.user_main);
//
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//        // Initialize Drawer
//        drawerLayout = findViewById(R.id.drawerLayout);
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//
//
//        navigationView = findViewById(R.id.navigationView);
//
//        toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close
//        );
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        // Set Navigation Item Click Listeners
//        navigationView.setNavigationItemSelectedListener(item -> {
//            int id = item.getItemId();
//            if (id == R.id.nav_my_account) {
//                // Open Account Activity
//                Intent accountIntent = new Intent(user_main.this, User_profile_activity.class);
//                startActivity(accountIntent);
//            } else if (id == R.id.nav_resources) {
//                // Handle Resources
//                Toast.makeText(user_main.this, "Resources clicked!", Toast.LENGTH_SHORT).show();
//            } else if (id == R.id.nav_logout) {
//                // Logout Action
//                Toast.makeText(user_main.this, "Logged out!", Toast.LENGTH_SHORT).show();
//                Intent logoutIntent = new Intent(user_main.this, user_login.class);
//                startActivity(logoutIntent);
//                finish();
//            }
//            return true;
//        });
//
//        // Retrieve user info and set header name
////        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users")
////                .child("starabhik20@gmail_com");
////        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String userName = snapshot.child("Name").getValue(String.class);
////                View headerView = navigationView.getHeaderView(0);
////                TextView headerUserName = headerView.findViewById(R.id.headerUserName);
////                if (userName != null) {
////                    headerUserName.setText(userName);
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(UserMainActivity.this, "Failed to fetch user data.", Toast.LENGTH_SHORT).show();
////            }
////        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}



//package com.abhik.community_resource_locator;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class user_main extends AppCompatActivity {
//
//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle toggle;
//    private NavigationView navigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.user_main);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        // Initialize Drawer
//        drawerLayout = findViewById(R.id.drawerLayout);
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView = findViewById(R.id.navigationView);
//
//        toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close
//        );
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        // Set Navigation Item Click Listeners
//        navigationView.setNavigationItemSelectedListener(item -> {
//            int id = item.getItemId();
//            if (id == R.id.nav_my_account) {
//                Intent accountIntent = new Intent(user_main.this, User_profile_activity.class);
//                startActivity(accountIntent);
//            } else if (id == R.id.nav_resources) {
//                Toast.makeText(user_main.this, "Resources clicked!", Toast.LENGTH_SHORT).show();
//            } else if (id == R.id.nav_logout) {
//                // Clear login state
//                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("isLoggedIn", false);
//                editor.apply();
//
//                Toast.makeText(user_main.this, "Logged out!", Toast.LENGTH_SHORT).show();
//                Intent logoutIntent = new Intent(user_main.this, user_login.class);
//                startActivity(logoutIntent);
//                finish();
//            }
//            return true;
//        });
//
//        // Start the typing animation for the TextView
//        TextView animatedTextView = findViewById(R.id.animatedTextView);
//        String textToAnimate = "What are you looking for ??";
//        animateText(animatedTextView, textToAnimate, 65); // 100 ms delay between letters
//    }
//
//    // Function to animate text letter by letter
//    private void animateText(final TextView view, final String text, final long delay) {
//        final Handler handler = new Handler(Looper.getMainLooper());
//        final int[] index = {0};
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (index[0] < text.length()) {
//                    view.setText(text.substring(0, index[0] + 1));
//                    index[0]++;
//                    handler.postDelayed(this, delay);
//                }
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}


//package com.abhik.community_resource_locator;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class user_main extends AppCompatActivity {
//
//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle toggle;
//    private NavigationView navigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.user_main);
//
//        // Initialize Toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        // Initialize Drawer
//        drawerLayout = findViewById(R.id.drawerLayout);
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        // Navigation View Setup
//        navigationView = findViewById(R.id.navigationView);
//        toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close
//        );
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        // Set Navigation Item Click Listeners
//        navigationView.setNavigationItemSelectedListener(item -> {
//            int id = item.getItemId();
//            if (id == R.id.nav_my_account) {
//                Intent accountIntent = new Intent(user_main.this, User_profile_activity.class);
//                startActivity(accountIntent);
//            } else if (id == R.id.nav_resources) {
//                Toast.makeText(user_main.this, "Resources clicked!", Toast.LENGTH_SHORT).show();
//            } else if (id == R.id.nav_logout) {
//                // Clear login state on logout
//                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("isLoggedIn", false);
//                editor.apply();
//
//                Toast.makeText(user_main.this, "Logged out!", Toast.LENGTH_SHORT).show();
//                Intent logoutIntent = new Intent(user_main.this, user_login.class);
//                startActivity(logoutIntent);
//                finish(); // End current activity so the user cannot navigate back to it
//            }
//            return true;
//        });
//
//        // Check if the user is already logged in (SharedPreferences check)
//        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//        if (!isLoggedIn) {
//            // If not logged in, navigate to login activity
//            Intent loginIntent = new Intent(user_main.this, user_login.class);
//            startActivity(loginIntent);
//            finish();
//        }
//
//        // Start the typing animation for the TextView
//        TextView animatedTextView = findViewById(R.id.animatedTextView);
//        String textToAnimate = "What are you looking for ??";
//        animateText(animatedTextView, textToAnimate, 65); // 65 ms delay between letters
//    }
//
//    // Function to animate text letter by letter
//    private void animateText(final TextView view, final String text, final long delay) {
//        final Handler handler = new Handler(Looper.getMainLooper());
//        final int[] index = {0};
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (index[0] < text.length()) {
//                    view.setText(text.substring(0, index[0] + 1));
//                    index[0]++;
//                    handler.postDelayed(this, delay);
//                }
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}


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
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_main extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private String userEmail; // Store logged-in user's email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

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

        // Load user email from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        userEmail = sharedPreferences.getString("email", null);

        if (!isLoggedIn || userEmail == null) {
            // Redirect to login if not logged in
            Intent loginIntent = new Intent(user_main.this, user_login.class);
            startActivity(loginIntent);
            finish();
            return; // Prevent further execution
        }

        // Set Navigation Item Click Listeners
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



