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
import com.starishko.pdd.Ticket1.q2;

public class Universal extends AppCompatActivity {

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
    public void Сhange (View view){
        Fragment fragment = null;
        switch(view.getId()) {
            case R.id.text_square1:
                fragment = new q1();
                break;
            case R.id.text_square2:
                fragment = new q2();
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
    public void onBackPressed () {
        Intent intent = new Intent(Universal.this, Tickets.class);
        ;
        startActivity(intent);
        finish();
    }


}

