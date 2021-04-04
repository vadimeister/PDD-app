package com.starishko.pdd.Ticket1;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    private CharSequence correctAnswer;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private  TextView answer_correct_id;
    private  TextView question;

    private String value1;
    private String value2;
    private String value3;
    private String value4;
    public int  numberTicket = 0;
    public int  numberQuestion = 0;


    private TextView buttonCorrectAnswer;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public  View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container,
                                          Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view =
                inflater.inflate(R.layout.ticket1_q1, container, false);
        Bundle args = getArguments();

        if(args != null){
            numberTicket = getArguments().getInt("numberTicket");
            numberQuestion= getArguments().getInt("numberQuestion");
        }
        else {
            numberTicket = 1;
            numberQuestion = 1;
        }


        Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1).setBackgroundResource(R.drawable.now);
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);



        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRefQuest = database.child ("Tickets").child (String.valueOf(numberTicket)).child(String.valueOf(numberQuestion));
        DatabaseReference myRefAnswers= database.child ("Tickets").child (String.valueOf(numberTicket)).child(String.valueOf(numberQuestion)).child("Answers");
        DatabaseReference myRefCorrect= database.child ("Tickets").child (String.valueOf(numberTicket)).child(String.valueOf(numberQuestion));



        question = view.findViewById(R.id.question);

        myRefQuest.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("question").getValue(String.class);
                question.setText(value);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {

            }
        });
        myRefAnswers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value1 = dataSnapshot.child("Answer1").getValue(String.class);
                answer1.setText(value1);

                value2 = dataSnapshot.child("Answer2").getValue(String.class);
                answer2.setText(value2);
                value3 = dataSnapshot.child("Answer3").getValue(String.class);
                answer3.setText(value3);

                value4 = dataSnapshot.child("Answer4").getValue(String.class);
                if (value4 != null)
                    answer4.setText(value4);
                else
                    answer4.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        answer_correct_id = view.findViewById(R.id.Correct_answer_id);
        myRefCorrect.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int a = 0;
                if(snapshot.exists() && snapshot.child("Correct_answer_id").getValue()!= null)
                   a = ((Long) Objects.requireNonNull(snapshot.child("Correct_answer_id").getValue())).intValue();
                String value = String.valueOf(a);

                answer_correct_id.setText(value);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        return view;


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        correct();
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

    private void correct() {
        String correct = answer_correct_id.getText().toString();

        int correct_id = Integer.parseInt(correct);

        if (correct_id == 1) {
            correctAnswer = (CharSequence) answer1.getText();
            buttonCorrectAnswer = answer1;
        }
        if (correct_id == 2) {
            correctAnswer = (CharSequence) answer2.getText();
            buttonCorrectAnswer = answer2;
        }
        if (correct_id == 3) {
            correctAnswer = (CharSequence) answer3.getText();
            buttonCorrectAnswer = answer3;
        }
        if (correct_id == 4) {
            correctAnswer = (CharSequence) answer4.getText();
            buttonCorrectAnswer = answer4;
        }
    }

    private void disableButtons() {
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
    }





    public void fragmentMail (int counter) {}
}