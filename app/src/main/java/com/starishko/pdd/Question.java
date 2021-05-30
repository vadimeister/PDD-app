package com.starishko.pdd;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class Question extends Fragment implements View.OnClickListener{



    public interface Postman {
        void fragmentMail (int numberOfCorrect);
    }
    public int counter = 0;
    Activity activity;

    public Question() {}

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
    private int numberTicket;
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private TextView textSquare;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }
    private TextView buttonCorrectAnswer;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public  View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container,
                                          Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view =
                inflater.inflate(R.layout.question, container, false);
        TextView text_numberOfTicket= getActivity().findViewById(R.id.text_numberOfTicket);
        String stringNumberOfTicket = text_numberOfTicket.getText().toString();
        int numberTicket = Integer.parseInt(stringNumberOfTicket.substring(7));

        Bundle args = getArguments();
        int numberQuestion;
        if(args != null){

            numberQuestion = args.getInt("numberQuestion");
        }
        else {
            numberQuestion = 1;


        }
        chooseTextSquare(numberQuestion);
        textSquare.setBackgroundResource(R.drawable.now);

        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);
        question = view.findViewById(R.id.question);
        final ImageView Image = (ImageView) view.findViewById(R.id.ticketImageView);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRefQuest = database.child ("Tickets").child (String.valueOf(numberTicket)).child(String.valueOf(numberQuestion));
        DatabaseReference myRefAnswers= database.child ("Tickets").child (String.valueOf(numberTicket)).child(String.valueOf(numberQuestion)).child("Answers");
        DatabaseReference myRefCorrect= database.child ("Tickets").child (String.valueOf(numberTicket)).child(String.valueOf(numberQuestion));

        String photoNumberTicket = ""+ numberTicket;
        String photoNumberQuestion =numberTicket+"_"+numberQuestion;



        StorageReference islandRef = FirebaseStorage.getInstance().getReference().child("Tickets").child(photoNumberTicket).child(photoNumberQuestion + ".jpg");
        showImage(islandRef, Image, photoNumberQuestion);




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
                if (value3 != null)
                    answer3.setText(value3);
                else{
                    answer3.setVisibility(View.GONE);
                    answer3.setEnabled(false);}

                value4 = dataSnapshot.child("Answer4").getValue(String.class);
                if (value4 != null)
                    answer4.setText(value4);
                else{
                    answer4.setVisibility(View.GONE);
                    answer4.setEnabled(false);}
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
        answer4.setOnClickListener(this);
        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDetach() {
        super.onDetach();
        boolean bgAlready = textSquare.getBackground().getConstantState() ==
                Objects.requireNonNull(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.now)).getConstantState();
        if (bgAlready) {
           textSquare.setBackgroundResource(R.color.darkBlue);
        // assertThat(shadowOf((BitmapDrawable)view.getBackground()).getCreatedFromResId()).isEqualTo(R.drawable.an_image);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        correct();
        textSquare.setEnabled(false);
        CharSequence answerGiven;
        switch (view.getId()) {
            case R.id.answer1:

                answerGiven = answer1.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer1.setBackgroundResource(R.color.correctAnswer);
                    textSquare.setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {
                    answer1.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    textSquare.setBackgroundResource(R.color.wrongAnswer);
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
                    textSquare.setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                else {
                    answer2.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    textSquare.setBackgroundResource(R.color.wrongAnswer);
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
                    textSquare.setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                else {
                    answer3.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    textSquare.setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;
            case R.id.answer4:
                //то же самое, что и предыдущий case, только используем следующую кнопку
                answerGiven = answer4.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer4.setBackgroundResource(R.color.correctAnswer);
                    textSquare.setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {//нет, неверно!
                    answer4.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    textSquare.setBackgroundResource(R.color.wrongAnswer);
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
        answer4.setEnabled(false);


    }
    private void showImage(StorageReference islandRef, final ImageView Image, String photoNumberQuestion) {
        File localFile = null;

        try {
            localFile = File.createTempFile(photoNumberQuestion, "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }



        final File finalLocalFile = localFile;
        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Bitmap bitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                Image.setImageBitmap(bitmap);

                // Local temp file has been created
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e){
                Image.setImageBitmap(null);

            }
        });
    }
    public void showToast(String s) {
        Toast.makeText(activity.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void chooseTextSquare(int numberQuestion){
        switch(numberQuestion){
            case 1:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square1);
                break;
            case 2:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square2);
                break;
            case 3:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square3);
                break;
            case 4:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square4);
                break;
            case 5:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square5);
                break;
            case 6:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square6);
                break;
            case 7:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square7);
                break;
            case 8:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square8);
                break;
            case 9:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square9);
                break;
            case 10:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square10);
                break;
            case 11:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square11);
                break;
            case 12:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square12);
                break;
            case 13:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square13);
                break;
            case 14:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square14);
                break;
            case 15:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square15);
                break;
            case 16:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square16);
                break;
            case 17:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square17);
                break;
            case 18:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square18);
                break;
            case 19:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square19);
                break;
            case 20:
                textSquare = Objects.requireNonNull(getActivity()).findViewById(R.id.text_square20);
                break;



        }


    }

    public void fragmentMail (int counter) {}
}