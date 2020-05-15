package com.starishko.pdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Tickets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickets);

        //кнопка "назад"
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Tickets.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Кнопка перехода на  1 уровень
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Tickets.this, Universal.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Tickets.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
