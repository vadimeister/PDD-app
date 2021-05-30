package com.starishko.pdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Rule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule);

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        int numberTheme = arguments.getInt("numberTheme");
        final TextView textRule = findViewById(R.id.rule);
        final TextView textThemeRule = findViewById(R.id.ruleTheme);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRefRule= database.child ("Rules").child (String.valueOf(numberTheme));
        DatabaseReference myRefThemeRule= database.child ("Rules").child (String.valueOf(numberTheme));

        myRefRule .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("textRule").getValue(String.class);
                textRule.setText(value);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {

            }
        });

        myRefThemeRule .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("Theme").getValue(String.class);
                textThemeRule.setText(value);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {

            }
        });




        Button button_back =findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v_) {
                Intent intent = new Intent(Rule.this, RulesList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Rule.this, RulesList.class);
        startActivity(intent);
        finish();
    }
}
