/*package com.starishko.pdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
 import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Ticket1 extends Fragment {
    private static final String TAG = "FlagQuiz Activity";

    private static final int imageInTicket = 15; //количество картинок в билете

    private Set<String> regionsSet; // world regions in current quiz
    private String correctAnswer; // Правильный ответ
    private int totalGuesses; // количество всех ответов
    private int correctAnswers; // количество правильных ответов
    private int guessRows; // Количество вариантов ответа
    private SecureRandom random; // used to randomize the quiz
    private Handler handler; // used to delay loading next flag
    private Animation shakeAnimation; // animation for incorrect guess

    private TextView answerTextView; // Правильный ответ


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.questions, container, false);


        // Имена файлов с картинками билетов
        List<String> fileNameList = new ArrayList<>();
        // countries in current quiz
        List<String> quizCountriesList = new ArrayList<>();
        handler = new Handler();

        // get references to GUI components
        // макет билета
        ConstraintLayout universalTicket = view.findViewById(R.id.universalTicket);
        // вывод картинки билета
        ImageView ticketImageView = view.findViewById(R.id.ticketImageView);
        //вопрос
        TextView question = view.findViewById(R.id.question);
        // Строки ответов
        TextView[] guessLinearLayouts = new TextView[3];
        guessLinearLayouts[0] = view.findViewById(R.id.answer1);
        guessLinearLayouts[1] = view.findViewById(R.id.answer2);
        guessLinearLayouts[2] = view.findViewById(R.id.answer3);
        guessLinearLayouts[3] = view.findViewById(R.id.answer4);

        // configure listeners for the guess Buttons
        for (TextView row : guessLinearLayouts) {
            TextView answer = (TextView) row;
            answer.setOnClickListener(guessButtonListener);
        }



        question.setText(getResources().getStringArray(R.array.Ticket1));
        return view; // return the fragment's view for display
    }


    private OnClickListener guessButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button guessButton = ((Button) v);
            String guess = guessButton.getText().toString();
            String answer = getCountryName(correctAnswer);
            ++totalGuesses; // increment number of guesses the user has made

            if (guess.equals(answer)) { // if the guess is correct
                ++correctAnswers; // increment the number of correct answers

                // display correct answer in green text
                answerTextView.setText(answer + "!");
                answerTextView.setTextColor(
                        getResources().getColor(R.color.correct_answer,
                                getContext().getTheme()));

            }
        }
    }
}

 */