package com.abhik.community_resource_locator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        CardView admincard= findViewById(R.id.admincard);
        CardView usercard= findViewById(R.id.usercard);

        admincard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, admin_login.class);
                startActivity(intent);
            }
        });

        usercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, user_login.class);
                startActivity(intent);
            }
        });
    }
}