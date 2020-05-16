package com.starishko.pdd.Ticket1;

import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import com.starishko.pdd.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class q11 extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.ticket1_q11, container, false);
        TextView answer1 = (TextView) view.findViewById(R.id.answer1);
        TextView answer2 = (TextView) view.findViewById(R.id.answer2);
        TextView answer3 = (TextView) view.findViewById(R.id.answer3);
        String[] answers = getResources().getStringArray(R.array.q2Answers);
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
        TextView question = (TextView) view.findViewById(R.id.question);
        String[] Questions = getResources().getStringArray(R.array.Questions);
        question.setText(Questions[10]);

        ImageView Image = (ImageView) view.findViewById(R.id.ticketImageView);
        try {
            String filename = "1_11.jpg";
            InputStream inputStream = null;
            // получаем входной поток
            InputStream ims = Objects.requireNonNull(getContext()).getAssets().open(filename);
            // загружаем как Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // выводим картинку в ImageView
            Image.setImageDrawable(d);
        } catch (IOException ex) {}
        return view;
    }
}
