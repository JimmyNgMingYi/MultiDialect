package com.example.multidialect;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsAct extends AppCompatActivity implements View.OnClickListener {

    private int qnsIndicator, diffculty ,quizSize, tNum, rQns,erQns;
    private List<ListQuestions> listOfquestions;
    private CountDownTimer counting;
    private Button choice1 , choice2, choice3, choice4;
    private TextView questionsBody, questionsNum, countdownTimer;
    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);

        countdownTimer = findViewById(R.id.timer);
        questionsBody = findViewById(R.id.questionText);
        questionsNum = findViewById(R.id.questionCount);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);
        diffculty = getIntent().getIntExtra("NUM",0);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        tNum = preferences.getInt("topicID",0);
        getListofQuestions();
    }

    private void questionSetting()
    {
        qnsIndicator = 0;
        rQns = new Random().nextInt(listOfquestions.size());
        countdownTimer.setText(String.valueOf(10));
        checkDiffculty();
        questionsNum.setText(String.valueOf(1) + "/" + String.valueOf(quizSize));
        questionsBody.setText(listOfquestions.get(rQns).getQuizQuestion());
        choice1.setText(listOfquestions.get(rQns).getChoice1());
        choice2.setText(listOfquestions.get(rQns).getChoice2());
        choice3.setText(listOfquestions.get(rQns).getChoice3());
        choice4.setText(listOfquestions.get(rQns).getChoice4());
        countdownStarted();

    }

    private void checkDiffculty()
    {
        switch(diffculty)
        {
            case 0:
                quizSize = 5;
                break;
            case 1:
                quizSize = 10;
                break;
            case 2:
                quizSize = 15;
                break;

        }
    }

    private void getListofQuestions() {
        Database data = new Database();

        switch(tNum)
        {
            case 0:
                listOfquestions = data.databaseNumbers();
                break;
            case 1:
                listOfquestions = data.databaseGreetings();
                break;
            case 2:
                listOfquestions = data.databaseJobs();
                break;
            case 3:
                listOfquestions = data.databaseCountries();
                break;
            case 4:
                listOfquestions = data.databaseHobbies();
                break;
            case 5:
                listOfquestions = data.databaseFamilies();
                break;
            case 6:
                listOfquestions = data.databaseFood();
                break;
            case 7:
                listOfquestions = data.databaseGestures();
                break;
            default:
        }
        questionSetting();
    }

    @Override
    public void onClick(View view) {
    int choiceSelected = 0;
        switch (view.getId())
    {
        case R.id.choice1:
            choiceSelected = 1;
            choicesSelected();
            break;

        case R.id.choice2:
            choiceSelected = 2;
            choicesSelected();
            break;

        case R.id.choice3:
            choiceSelected = 3;
            choicesSelected();
            break;

        case R.id.choice4:
            choiceSelected = 4;
            choicesSelected();
            break;

        default:
    }
    counting.cancel();
    answerChecking(choiceSelected , view);
    }

    private void countdownStarted()
    {
        counting = new CountDownTimer(12000,1000) {
            @Override
            public void onTick(long l) {
                if(l <10000)
                    countdownTimer.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                nextQuestion();
            }
        };
        counting.start();
    }

    private void nextQuestion()
    {
        if (qnsIndicator < quizSize-1)
        {
            qnsIndicator++;
            rQns = new Random().nextInt(listOfquestions.size());
            nextScreen(questionsBody,0,0);
            nextScreen(choice1,0,1);
            nextScreen(choice2,0,2);
            nextScreen(choice3,0,3);
            nextScreen(choice4,0,4);
            questionsNum.setText(String.valueOf(qnsIndicator+1) + "/" + String.valueOf(quizSize));
            countdownTimer.setText(String.valueOf(10));
            choice1.setEnabled(true);
            choice2.setEnabled(true);
            choice3.setEnabled(true);
            choice4.setEnabled(true);
            countdownStarted();
        }
        else
        {
            ScoreNotifyAct scores = new ScoreNotifyAct(this,"Your Score is " + score + "/" + quizSize);
            scores.setCancelable(false);
            scores.show();
        }
    }

    private void nextScreen(final View v, final int number, final int count)
    {
        v.animate().alpha(number).scaleX(number).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener((new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator)
            {
                if(number == 0)
                {
                    switch(count)
                    {
                        case 0:
                            ((TextView)v).setText(listOfquestions.get(rQns).getQuizQuestion());
                            break;
                        case 1:
                            ((Button)v).setText(listOfquestions.get(rQns).getChoice1());
                            break;
                        case 2:
                            ((Button)v).setText(listOfquestions.get(rQns).getChoice2());
                            break;
                        case 3:
                            ((Button)v).setText(listOfquestions.get(rQns).getChoice3());
                            break;
                        case 4:
                            ((Button)v).setText(listOfquestions.get(rQns).getChoice4());
                            break;
                    }
                    if(count != 0)
                    {
                        ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#96DED1")));
                    }
                    nextScreen(v,1,count);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }));
    }

    private void choicesSelected()
    {
        choice1.setEnabled(false);
        choice2.setEnabled(false);
        choice3.setEnabled(false);
        choice4.setEnabled(false);
    }

    private void answerChecking(int choiceEntered, View v)
    {
        if (choiceEntered == listOfquestions.get(rQns).getAnswerRight())
        {
            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
        }
        else
        {
            //Wrong
            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch (listOfquestions.get(rQns).getAnswerRight())
            {
                case 1:
                    choice1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    choice2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    choice3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    choice4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }
        Handler delay = new Handler();
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        counting.cancel();
    }


}