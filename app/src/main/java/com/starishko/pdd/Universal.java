package com.starishko.pdd;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.starishko.pdd.Ticket1.q1;
import com.starishko.pdd.Ticket1.q10;
import com.starishko.pdd.Ticket1.q11;
import com.starishko.pdd.Ticket1.q12;
import com.starishko.pdd.Ticket1.q13;
import com.starishko.pdd.Ticket1.q14;
import com.starishko.pdd.Ticket1.q15;
import com.starishko.pdd.Ticket1.q16;
import com.starishko.pdd.Ticket1.q17;
import com.starishko.pdd.Ticket1.q18;
import com.starishko.pdd.Ticket1.q19;
import com.starishko.pdd.Ticket1.q2;
import com.starishko.pdd.Ticket1.q20;
import com.starishko.pdd.Ticket1.q3;
import com.starishko.pdd.Ticket1.q4;
import com.starishko.pdd.Ticket1.q5;
import com.starishko.pdd.Ticket1.q6;
import com.starishko.pdd.Ticket1.q7;
import com.starishko.pdd.Ticket1.q8;
import com.starishko.pdd.Ticket1.q9;

public class Universal extends AppCompatActivity implements q1.Postman {
    int goodAnswers = 0;
    int allAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Universal.this, Tickets.class);
                startActivity(intent);
                finish();
            }
        });

        // номер текущего вопроса
        TextView questionNumberTextView = findViewById(R.id.text_nubmerOfTicket);
        questionNumberTextView.setText(R.string.ticket1);
    }

    public void Сhange(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.text_square1:
                fragment = new q1();
                break;
            case R.id.text_square2:
                fragment = new q2();
                break;
            case R.id.text_square3:
                fragment = new q3();
                break;
            case R.id.text_square4:
                fragment = new q4();
                break;
            case R.id.text_square5:
                fragment = new q5();
                break;
            case R.id.text_square6:
                fragment = new q6();
                break;
            case R.id.text_square7:
                fragment = new q7();
                break;
            case R.id.text_square8:
                fragment = new q8();
                break;
            case R.id.text_square9:
                fragment = new q9();
                break;
            case R.id.text_square10:
                fragment = new q10();
                break;
            case R.id.text_square11:
                fragment = new q11();
                break;
            case R.id.text_square12:
                fragment = new q12();
                break;
            case R.id.text_square13:
                fragment = new q13();
                break;
            case R.id.text_square14:
                fragment = new q14();
                break;
            case R.id.text_square15:
                fragment = new q15();
                break;
            case R.id.text_square16:
                fragment = new q16();
                break;
            case R.id.text_square17:
                fragment = new q17();
                break;
            case R.id.text_square18:
                fragment = new q18();
                break;
            case R.id.text_square19:
                fragment = new q19();
                break;
            case R.id.text_square20:
                fragment = new q20();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        assert fragment != null;
        ft.replace(R.id.fr_place, fragment);
        ft.commit();

    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Universal.this, Tickets.class);
        ;
        startActivity(intent);
        finish();
    }

    @Override
    public void fragmentMail(int numberOfCorrect) {
        goodAnswers = goodAnswers + numberOfCorrect;
        allAnswers = allAnswers + 1;
        if (allAnswers >= 20) {
            Intent intent = new Intent(Universal.this, Result.class);
            intent.putExtra("goodAnswers", goodAnswers);
            startActivity(intent);
            finish();
        }
    }

}

