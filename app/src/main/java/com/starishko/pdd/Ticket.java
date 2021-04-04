package com.starishko.pdd;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Ticket extends FragmentActivity implements Question.Postman{
    int goodAnswers = 0;
    int allAnswers = 0;
    public int numberTicket;
    public int numberQuestion;
    @SuppressLint({"Assert", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Ticket.this, Tickets.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        numberTicket= arguments.getInt("numberTicket");





        // номер текущего вопроса
        TextView questionNumberTextView = findViewById(R.id.text_nubmerOfTicket);
        questionNumberTextView.setText("Билет №" + numberTicket);
    }

    public void Сhange(View view) {
        Fragment fragment = null;
        fragment = new Question();
        switch (view.getId()) {
            case R.id.text_square1:
                numberQuestion = 1;
                break;
            case R.id.text_square2:
                numberQuestion = 2;
                break;
            case R.id.text_square3:
                numberQuestion = 3;
                break;
            case R.id.text_square4:
                numberQuestion = 4;
                break;
            case R.id.text_square5:
                numberQuestion = 5;
                break;
            case R.id.text_square6:
                numberQuestion = 6;
                break;
            case R.id.text_square7:
                numberQuestion = 7;
                break;
            case R.id.text_square8:
                numberQuestion = 8;
                break;
            case R.id.text_square9:
                numberQuestion = 9;
                break;
            case R.id.text_square10:
                numberQuestion = 10;
                break;
            case R.id.text_square11:
                numberQuestion = 11;
                break;
            case R.id.text_square12:
                numberQuestion = 12;
                break;
            case R.id.text_square13:
                numberQuestion = 13;
                break;
            case R.id.text_square14:
                numberQuestion = 14;
                break;
            case R.id.text_square15:
                numberQuestion = 15;
                break;
            case R.id.text_square16:
                numberQuestion = 16;
                break;
            case R.id.text_square17:
                numberQuestion = 17;
                break;
            case R.id.text_square18:
                numberQuestion = 18;
                break;
            case R.id.text_square19:
                numberQuestion = 19;
                break;
            case R.id.text_square20:
                numberQuestion = 20;
                break;
        }

        Bundle args = new Bundle();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr_place, fragment);
        ft.commit();

    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Ticket.this, Tickets.class);
        ;
        startActivity(intent);
        finish();
    }

    @Override
    public void fragmentMail(int numberOfCorrect) {
        goodAnswers = goodAnswers + numberOfCorrect;
        allAnswers = allAnswers + 1;
        if (allAnswers >= 20) {
            Intent intent = new Intent(Ticket.this, Result.class);
            intent.putExtra("goodAnswers", goodAnswers);
            startActivity(intent);
            finish();
        }
    }

}

