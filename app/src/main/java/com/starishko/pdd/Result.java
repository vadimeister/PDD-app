package com.starishko.pdd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_ticket);

        //кнопка "назад"
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Result.this, TicketsList.class);
                startActivity(intent);
                finish();
            }
        });
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        int count = arguments.getInt("goodAnswers");
        int numberTicket= arguments.getInt("numberTicket");
        TextView result = findViewById(R.id.result);

        setResultQuestions(numberTicket-1,count);

        if (count == 20) {
            result.setText("Билет №" + numberTicket + " Пройден:" + "\n" + count + " правильных ответов" + "\n" + "20 вопросов");
            setResultTickets(numberTicket-1,1);
        }
        else {
            result.setText("Билет №" + numberTicket + " Не пройден:" + "\n" + count + " правильных ответов" + "\n" + "20 вопросов");
            setResultTickets(numberTicket - 1, 0);
        }

    }
    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Result.this, TicketsList.class);
        startActivity(intent);
        finish();
    }
    public static void setResultQuestions(int i, int newValue) {
        SharedPreferences.Editor editor ;
        editor = MainActivity.pref.edit();
        editor.putString("ResultQuestions" +i, String.valueOf(newValue));
        editor.apply();

    }
    public static void setResultTickets(int i, int newValue) {
        SharedPreferences.Editor editor;
        editor = MainActivity.pref.edit();
        editor.putString("ResultTickets" +i, String.valueOf(newValue));
        editor.apply();
    }

}