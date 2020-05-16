package com.starishko.pdd.Ticket1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.starishko.pdd.R;
import org.jetbrains.annotations.NotNull;


public class q1 extends Fragment {
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.ticket1_q1, container, false);
        TextView answer1 = (TextView) view.findViewById(R.id.answer1);
        TextView answer2 = (TextView) view.findViewById(R.id.answer2);
        TextView answer3 = (TextView) view.findViewById(R.id.answer3);
        String [] answers = getResources().getStringArray(R.array.q1Answers);
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
        TextView question = (TextView) view.findViewById(R.id.question);
        String [] Questions= getResources().getStringArray(R.array.Questions);
        question.setText(Questions[0]);

        return view;
    }


}
