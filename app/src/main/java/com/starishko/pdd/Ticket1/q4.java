package com.starishko.pdd.Ticket1;

import android.app.Activity;
import android.content.Context;
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

public class q4 extends Fragment implements  View.OnClickListener{
    public interface Postman {
        void fragmentMail (int numberOfCorrect);
    }
    public int counter = 0;
    Activity activity;

    public q4() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity=(Activity) context;
        }
    }
    private String correctAnswer;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private TextView buttonCorrectAnswer;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.ticket1_q4, container, false);
        Objects.requireNonNull(getActivity()).findViewById(R.id.text_square4).setBackgroundResource(R.drawable.now);
        answer1 =  view.findViewById(R.id.answer1);
        answer2 =  view.findViewById(R.id.answer2);
        answer3 =  view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);
        String[] answers = getResources().getStringArray(R.array.q4Answers);
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
        answer4.setText(answers[3]);
        TextView question = view.findViewById(R.id.question);
        String[] Questions = getResources().getStringArray(R.array.Questions);
        question.setText(Questions[3]);
        correctAnswer = answers[3];
        buttonCorrectAnswer = answer4;

        ImageView Image = (ImageView) view.findViewById(R.id.ticketImageView);
        try {
            String filename = "1_4.jpg";
            InputStream inputStream = null;
            // получаем входной поток
            InputStream ims = Objects.requireNonNull(getContext()).getAssets().open(filename);
            // загружаем как Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // выводим картинку в ImageView
            Image.setImageDrawable(d);
        } catch (IOException ex) {}
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        Objects.requireNonNull(getActivity()).findViewById(R.id.text_square4).setEnabled(false);
        CharSequence answerGiven;
        switch (view.getId()) {
            case R.id.answer1:
                //присваиваем переменной answerGiven значение, содержащееся в buttonObjectChoice1
                //это значение мы сами поместили туда ранее
                answerGiven = answer1.getText();
                //получен верный овтет?
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer1.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square4).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {//нет, неверно!
                    answer1.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square4).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;

            case R.id.answer2:
                //то же самое, что и предыдущий case, только используем следующую кнопку
                answerGiven = answer2.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer2.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square4).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {//нет, неверно!
                    answer2.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square4).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;

            case R.id.answer3:
                //то же самое, что и предыдущий case, только используем следующую кнопку
                answerGiven = answer3.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer3.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square4).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {//нет, неверно!
                    answer3.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square4).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;
            case R.id.answer4:
                //то же самое, что и предыдущий case, только используем следующую кнопку
                answerGiven = answer4.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer4.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square4).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {//нет, неверно!
                    answer4.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square4).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;

        }

    }
    private void disableButtons() {
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        answer4.setEnabled(false);

    }
}
