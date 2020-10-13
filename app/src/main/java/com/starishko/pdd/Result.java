package com.starishko.pdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_ticket);

        //кнопка "назад"
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Result.this, Tickets.class);
                startActivity(intent);
                finish();
            }
        });
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        int n = arguments.getInt("goodAnswers");
        TextView result = findViewById(R.id.result);
        result.setText("Билет №1 Ваш результат:" + "\n" +  n + " правильных ответов" + "\n" + "20 вопросов");

    }
    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Result.this, Tickets.class);
        startActivity(intent);
        finish();
    }
}