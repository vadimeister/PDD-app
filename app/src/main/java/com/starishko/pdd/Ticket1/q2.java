package com.starishko.pdd.Ticket1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.starishko.pdd.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class q2 extends Fragment implements  View.OnClickListener{

    public interface Postman {
        void fragmentMail (int numberOfClicks);
    }
    public int counter = 0;
    Activity activity;

    public q2() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity=(Activity) context;
        }
    }
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();



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

    private TextView buttonCorrectAnswer;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public  View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container,
                              Bundle bundle) {
        super.onCreateView(inflater, container, bundle);
        final View view =
                inflater.inflate(R.layout.ticket1_q2, container, false);
        Objects.requireNonNull(getActivity()).findViewById(R.id.text_square2).setBackgroundResource(R.drawable.now);
        Bundle args = getArguments();
        int n = 0;
        if(args != null)
            n = getArguments().getInt("number");
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = null;



        question = view.findViewById(R.id.question);

        DatabaseReference myRefQuest = database.child ("Ticket1").child("Question_ids").child("2");
        myRefQuest.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("Question").getValue(String.class);
                question.setText(value);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {

            }
        });
        DatabaseReference myRefAnswers= database.child ("Ticket1").child("Question_ids").child("2").child("Answers");
        myRefAnswers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value1 = dataSnapshot.child("Answer1").getValue(String.class);
                answer1.setText(value1);
                value2 = dataSnapshot.child("Answer2").getValue(String.class);
                answer2.setText(value2);
                value3 = dataSnapshot.child("Answer3").getValue(String.class);
                answer3.setText(value3);
                if (answer4 != null){
                    value4 = dataSnapshot.child("Answer4").getValue(String.class);
                    answer4.setText(value4);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference myRefCorrect= database.child ("Ticket1").child("Question_ids").child("2");
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



        StorageReference islandRef = FirebaseStorage.getInstance().getReference().child("Ticket 1/1_2.jpg");
        File localFile = null;
        try {
            localFile = File.createTempFile("1_2", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ImageView Image = (ImageView) view.findViewById(R.id.ticketImageView);

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
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
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
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square2).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                } else {
                    answer1.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square2).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;

            case R.id.answer2:
                answerGiven = answer2.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer2.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square2).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                else {
                    answer2.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square2).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
                    } catch (ClassCastException ignored) {}
                }
                disableButtons();
                break;

            case R.id.answer3:
                answerGiven = answer3.getText();
                if (answerGiven == correctAnswer) {//да - это верный ответ
                    answer3.setBackgroundResource(R.color.correctAnswer);
                    Objects.requireNonNull(getActivity()).findViewById(R.id.text_square3).setBackgroundResource(R.color.correctAnswer);
                    counter++;
                }
                else {
                    answer3.setBackgroundResource(R.color.wrongAnswer);
                    buttonCorrectAnswer.setBackgroundResource(R.color.correctAnswer);
                    getActivity().findViewById(R.id.text_square2).setBackgroundResource(R.color.wrongAnswer);
                    try {
                        ((q1.Postman) activity).fragmentMail(counter);
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

}




