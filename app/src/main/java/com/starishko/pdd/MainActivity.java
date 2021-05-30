package com.starishko.pdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private static FirebaseDatabase mDatabase;
    public static SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDatabase();
        pref = getSharedPreferences("Data", MODE_PRIVATE);

        Button startButton= (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                try {
                    Intent intent = new Intent(MainActivity.this, TicketsList.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                }
            }
        });
        Button rulesButton= (Button) findViewById(R.id.ruleButton);
        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                try {
                    Intent intent = new Intent(MainActivity.this, RulesList.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
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
                } catch (Exception ignored) {
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
                } catch (Exception ignored) {
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    public static void getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
    }
    /*private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                });
    }*/



}

