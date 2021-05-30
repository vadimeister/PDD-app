package com.starishko.pdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RulesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ruleslist);

        RecyclerView rulesList = findViewById(R.id.rulesList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rulesList.setLayoutManager(layoutManager);
        rulesList.setHasFixedSize(true);
        RulesAdapter rulesAdapter = new RulesAdapter(27, this);
        rulesList.setAdapter(rulesAdapter);



        Button button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v_) {
                Intent intent = new Intent(RulesList.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RulesList.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}