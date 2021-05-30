package com.starishko.pdd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;



public class Stats extends AppCompatActivity {

    private static int goodQuestions=0;
    private static int goodTickets;
    public static int[] ResultTickets = new int[40];


    @SuppressLint("CommitPrefEdits")
    public static int getResultQuestions(int i) {
        return Integer.parseInt(MainActivity.pref.getString("ResultQuestions" +i, "0"));
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        goodQuestions=0;
        for (int j = 0; j<40; j++) {
            goodQuestions = goodQuestions + Integer.parseInt(MainActivity.pref.getString("ResultQuestions" +j, "0"));;
        }

        goodTickets=0;
        for (int j = 0; j<40; j++ ) {
            goodTickets = goodTickets + Integer.parseInt(MainActivity.pref.getString("ResultTickets" +j, "0"));;
        }

        TextView textGoodQuestionsOfAll = (TextView) findViewById(R.id.textViewGoodOfALL);
        textGoodQuestionsOfAll.setText(goodQuestions + "/"+ "800");
        ProgressBar progressQuestions = (ProgressBar) findViewById(R.id.progressBarQuestions);
        progressQuestions.setMax(800);
        progressQuestions.setProgress(goodQuestions);



        TextView textGoodTickets= (TextView) findViewById(R.id.textViewTicketsRes);
        textGoodTickets.setText(goodTickets + "/"+ "40");
        ProgressBar progressTickets = (ProgressBar) findViewById(R.id.progressBarTickets);
        progressTickets.setMax(40);
        progressTickets.setProgress(goodTickets);

        //кнопка "назад"
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Stats.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
        //Системная кнопка "назад"
        @Override
        public void onBackPressed(){
            Intent intent = new Intent(Stats.this, MainActivity.class);
            startActivity(intent);
            finish();
        }



}
