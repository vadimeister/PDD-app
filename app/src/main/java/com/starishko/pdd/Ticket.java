package com.starishko.pdd;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
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
        setContentView(R.layout.ticket);
        Button button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Ticket.this, TicketsList.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        numberTicket= arguments.getInt("numberTicket");

        // номер текущего вопроса
        TextView questionNumberTextView = findViewById(R.id.text_numberOfTicket);
        questionNumberTextView.setText("Билет №" + numberTicket);
    }

    public void Сhange(View view) {

        TextView cool;
        Fragment fragment = null;
        fragment = new Question();
        switch (view.getId()) {
            case R.id.text_square1:
                numberQuestion = 1;
                cool = findViewById(R.id.text_square1);
                break;
            case R.id.text_square2:
                numberQuestion = 2;
                cool = findViewById(R.id.text_square1);
                break;
            case R.id.text_square3:
                numberQuestion = 3;
                cool = findViewById(R.id.text_square1);
                break;
            case R.id.text_square4:
                numberQuestion = 4;
                cool = findViewById(R.id.text_square1);
                break;
            case R.id.text_square5:
                numberQuestion = 5;
                cool = findViewById(R.id.text_square2);
                break;
            case R.id.text_square6:
                numberQuestion = 6;
                cool = findViewById(R.id.text_square3);
                break;
            case R.id.text_square7:
                numberQuestion = 7;
                cool = findViewById(R.id.text_square4);
                break;
            case R.id.text_square8:
                numberQuestion = 8;
                cool = findViewById(R.id.text_square5);
                break;
            case R.id.text_square9:
                numberQuestion = 9;
                cool = findViewById(R.id.text_square6);
                break;
            case R.id.text_square10:
                cool = findViewById(R.id.text_square7);
                numberQuestion = 10;
                break;
            case R.id.text_square11:
                cool = findViewById(R.id.text_square8);
                numberQuestion = 11;
                break;
            case R.id.text_square12:
                cool = findViewById(R.id.text_square9);
                numberQuestion = 12;
                break;
            case R.id.text_square13:
                cool = findViewById(R.id.text_square10);
                numberQuestion = 13;
                break;
            case R.id.text_square14:
                numberQuestion = 14;
                cool = findViewById(R.id.text_square11);
                break;
            case R.id.text_square15:
                numberQuestion = 15;
                cool = findViewById(R.id.text_square12);
                break;
            case R.id.text_square16:
                numberQuestion = 16;
                cool = findViewById(R.id.text_square13);
                break;
            case R.id.text_square17:
                numberQuestion = 17;
                cool = findViewById(R.id.text_square14);
                break;
            case R.id.text_square18:
                numberQuestion = 18;
                cool = findViewById(R.id.text_square15);
                break;
            case R.id.text_square19:
                numberQuestion = 19;
                cool = findViewById(R.id.text_square16);
                break;
            case R.id.text_square20:
                cool = findViewById(R.id.text_square17);
                numberQuestion = 20;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        final HorizontalScrollView scrollQuestion = findViewById(R.id.horizontalScrollView);

        final float Cod= cool.getX();
        scrollQuestion.post(new Runnable() {
            public void run() {
                scrollQuestion.smoothScrollTo((int) Cod-55, (int) Cod-55);
            }
        });

        Bundle args = new Bundle();
        args.putInt("numberTicket", numberTicket);
        args.putInt("numberQuestion", numberQuestion);
        fragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr_place, fragment);
        ft.commit();
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Ticket.this, TicketsList.class);
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
            intent.putExtra("numberTicket", numberTicket);
            startActivity(intent);
            finish();
        }
    }

}

