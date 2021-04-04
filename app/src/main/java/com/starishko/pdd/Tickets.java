package com.starishko.pdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tickets extends AppCompatActivity implements View.OnClickListener {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    int numberTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickets);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        //кнопка "назад"
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v_) {
                Intent intent = new Intent(Tickets.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        TextView textView11 = (TextView) findViewById(R.id.textView11);
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView13 = (TextView) findViewById(R.id.textView13);
        TextView textView14= (TextView) findViewById(R.id.textView14);
        TextView textView15= (TextView) findViewById(R.id.textView15);
        TextView textView16= (TextView) findViewById(R.id.textView16);
        TextView textView17= (TextView) findViewById(R.id.textView17);
        TextView textView18= (TextView) findViewById(R.id.textView18);
        TextView textView19= (TextView) findViewById(R.id.textView19);
        TextView textView20 = (TextView) findViewById(R.id.textView20);
        TextView textView21 = (TextView) findViewById(R.id.textView21);
        TextView textView22 = (TextView) findViewById(R.id.textView22);
        TextView textView23 = (TextView) findViewById(R.id.textView23);
        TextView textView24 = (TextView) findViewById(R.id.textView24);
        TextView textView25 = (TextView) findViewById(R.id.textView25);
        TextView textView26= (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView29 = (TextView) findViewById(R.id.textView29);
        TextView textView30 = (TextView) findViewById(R.id.textView30);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView32 = (TextView) findViewById(R.id.textView32);
        TextView textView33 = (TextView) findViewById(R.id.textView33);
        TextView textView34 = (TextView) findViewById(R.id.textView34);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView36 = (TextView) findViewById(R.id.textView36);
        TextView textView37 = (TextView) findViewById(R.id.textView37);
        TextView textView38 = (TextView) findViewById(R.id.textView38);
        TextView textView39 = (TextView) findViewById(R.id.textView39);
        TextView textView40 = (TextView) findViewById(R.id.textView40);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        textView9.setOnClickListener(this);
        textView10.setOnClickListener(this);
        textView11.setOnClickListener(this);
        textView12.setOnClickListener(this);
        textView13.setOnClickListener(this);
        textView14.setOnClickListener(this);
        textView15.setOnClickListener(this);
        textView16.setOnClickListener(this);
        textView17.setOnClickListener(this);
        textView18.setOnClickListener(this);
        textView19.setOnClickListener(this);
        textView20.setOnClickListener(this);
        textView21.setOnClickListener(this);
        textView22.setOnClickListener(this);
        textView23.setOnClickListener(this);
        textView24.setOnClickListener(this);
        textView25.setOnClickListener(this);
        textView26.setOnClickListener(this);
        textView27.setOnClickListener(this);
        textView28.setOnClickListener(this);
        textView29.setOnClickListener(this);
        textView30.setOnClickListener(this);
        textView31.setOnClickListener(this);
        textView32.setOnClickListener(this);
        textView33.setOnClickListener(this);
        textView34.setOnClickListener(this);
        textView35.setOnClickListener(this);
        textView36.setOnClickListener(this);
        textView37.setOnClickListener(this);
        textView38.setOnClickListener(this);
        textView39.setOnClickListener(this);
        textView40.setOnClickListener(this);

    }

    public void onClick(View view) {
        Intent intent = new Intent(Tickets.this, Ticket.class);
        switch (view.getId()){
            case R.id.textView1:
                numberTicket=1;
                break;
            case R.id.textView2:
                numberTicket=2;
                break;
            case R.id.textView3:
                numberTicket=3;
                break;
            case R.id.textView4:
                numberTicket=4;
                break;
            case R.id.textView5:
                numberTicket=5;
                break;
            case R.id.textView6:
                numberTicket=6;
                break;
            case R.id.textView7:
                numberTicket=7;
                break;
            case R.id.textView8:
                numberTicket=8;
                break;
            case R.id.textView9:
                numberTicket=9;
                break;
            case R.id.textView10:
                numberTicket=10;
                break;
            case R.id.textView11:
                numberTicket=11;
                break;
            case R.id.textView12:
                numberTicket=12;
                break;
            case R.id.textView13:
                numberTicket=13;
                break;
            case R.id.textView14:
                numberTicket=14;
                break;
            case R.id.textView15:
                numberTicket=15;
                break;
            case R.id.textView16:
                numberTicket=16;
                break;
            case R.id.textView17:
                numberTicket=17;
                break;
            case R.id.textView18:
                numberTicket=18;
                break;
            case R.id.textView19:
                numberTicket=19;
                break;
            case R.id.textView20:
                numberTicket=20;
                break;
            case R.id.textView21:
                numberTicket=21;
                break;
            case R.id.textView22:
                numberTicket=22;
                break;
            case R.id.textView23:
                numberTicket=23;
                break;
            case R.id.textView24:
                numberTicket=24;
                break;
            case R.id.textView25:
                numberTicket=25;
                break;
            case R.id.textView26:
                numberTicket=26;
                break;
            case R.id.textView27:
                numberTicket=27;
                break;
            case R.id.textView28:
                numberTicket=28;
                break;
            case R.id.textView29:
                numberTicket=29;
                break;
            case R.id.textView30:
                numberTicket=30;
                break;
            case R.id.textView31:
                numberTicket=31;
                break;
            case R.id.textView32:
                numberTicket=32;
                break;
            case R.id.textView33:
                numberTicket=33;
                break;
            case R.id.textView34:
                numberTicket=34;
                break;
            case R.id.textView35:
                numberTicket=35;
                break;
            case R.id.textView36:
                numberTicket=36;
                break;
            case R.id.textView37:
                numberTicket=37;
                break;
            case R.id.textView38:
                numberTicket=38;
                break;
            case R.id.textView39:
                numberTicket=39;
                break;
            case R.id.textView40:
                numberTicket=40;
                break;
        }
        intent.putExtra("numberTicket",  numberTicket);
        startActivity(intent);
        finish();

    }
    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Tickets.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
