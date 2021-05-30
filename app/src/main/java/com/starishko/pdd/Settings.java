package com.starishko.pdd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Set;


public class Settings extends AppCompatActivity {

    AlertDialog.Builder ad;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //кнопка "назад"
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button clearSettings= (Button) findViewById(R.id.statButton);
        clearSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogClearSettings myDialogFragment = new DialogClearSettings();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                myDialogFragment.show(transaction, "dialog");
                //MainActivity.pref.edit().clear().apply();
            }
        });
    }



    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}