package com.starishko.pdd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;



public class TicketsList extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketslist);
        RecyclerView ticketsList = findViewById(R.id.ticketsList);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        ticketsList.setLayoutManager(layoutManager);
        ticketsList.setHasFixedSize(true);
        TicketsAdapter ticketsAdapter = new TicketsAdapter(39, this);
        ticketsList.setAdapter(ticketsAdapter);


        //кнопка "назад"
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v_) {
                Intent intent = new Intent(TicketsList.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(TicketsList.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
