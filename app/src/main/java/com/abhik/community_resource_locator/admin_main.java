package com.abhik.community_resource_locator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

public class admin_main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);

        TextView animatedTextView = findViewById(R.id.animatedTextView);
        String textToAnimate = "What Services are you offering ??";
        animateText(animatedTextView, textToAnimate, 65);


        CardView ambulance= findViewById(R.id.ambulancecard);

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(admin_main.this,AmbulanceAdmin.class);
                startActivity(intent);
            }
        });
    }

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
}