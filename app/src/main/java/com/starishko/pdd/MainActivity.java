package com.starishko.pdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton= (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                try {
                    Intent intent = new Intent(MainActivity.this, Tickets.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });
        Button statButton= (Button) findViewById(R.id.statButton);
        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                try {
                    Intent intent = new Intent(MainActivity.this, Stats.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });
        Button optionsButton= (Button) findViewById(R.id.optionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                try {
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
}

