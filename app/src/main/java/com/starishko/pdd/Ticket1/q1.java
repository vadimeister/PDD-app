package com.starishko.pdd.Ticket1;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.starishko.pdd.R;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;


public class q1 extends Fragment implements View.OnClickListener{


    public interface Postman {
        void fragmentMail (int numberOfCorrect);
    }
    public int counter = 0;
    Activity activity;

    public q1() {}

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
    private TextView buttonCorrectAnswer;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle bundle) {
        super.onCreateView(inflater, container, bundle);
        View view =
                inflater.inflate(R.layout.ticket1_q1, container, false);
        Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setBackgroundResource(R.drawable.now);
        String[] answers = getResources().getStringArray(R.array.q1Answers);
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
        TextView question = view.findViewById(R.id.question);
        String[] Questions = getResources().getStringArray(R.array.Questions);
        question.setText(Questions[0]);
        correctAnswer = answers[1];
        buttonCorrectAnswer = answer2;
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setEnabled(false);
        CharSequence answerGiven;
        switch (view.getId()) {
            case R.id.answer1:

                answerGiven = answer1.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer1.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {
                    answer1.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;

            case R.id.answer2:
                answerGiven = answer2.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer2.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                else {
                    answer2.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;

            case R.id.answer3:
                answerGiven = answer3.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer3.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                }
                else {
                    answer3.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square1).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((Postman) activity).fragmentMail(counter);
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

    }

    public void fragmentMail (int counter) {}
}