package com.example.multidialect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMultiAct extends AppCompatActivity{

    String playerUID = "0";
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://multidialect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
    private boolean currentUser = false;
    private boolean enemyfound = false;
    String enemyUID = "0";
    String status = "Matching";
    String roomID = "";
    String singleID = "";
    String NameEnemy = "";
    String getPlayername = "";
    ValueEventListener roundsEventListener , connectUsers , scoreEventListener;
    private int qnsIndicator, tNum, randomQns, eScore , pScore, fixQ;
    private List<ListQuestions> listOfquestions;
    private CountDownTimer counting;
    private Button choice1 , choice2, choice3, choice4;
    private TextView questionsBody, questionsNum, countdownTimer;
    int score1 = 0;
    int score2 = 0;
    int playerA = Color.argb(255,224,237,112);
    int playerB = Color.argb(255,55,220,226);
    ArrayList<Integer> randomQ = new ArrayList<Integer>();
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_multi);
        choice1 = findViewById(R.id.Mchoice1);
        choice2 = findViewById(R.id.Mchoice2);
        choice3 = findViewById(R.id.Mchoice3);
        choice4 = findViewById(R.id.Mchoice4);

        countdownTimer = findViewById(R.id.Mtimer);
        questionsBody = findViewById(R.id.MquestionText);
        questionsNum = findViewById(R.id.MquestionCount);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        tNum = sharedPref.getInt("topicID",0);
        getListofQuestions();

        playerUID = String.valueOf(System.currentTimeMillis());
        getPlayername = getIntent().getStringExtra("L");


        connectUsers = databaseReference.child("connections").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!enemyfound) {
                    if (snapshot.hasChildren()) {
                        for (DataSnapshot connections : snapshot.getChildren())
                        {
                            String conID = connections.getKey();
                            int getPlayercount = (int) connections.getChildrenCount();
                            if (status.equals("waiting"))
                            {
                                if (getPlayercount == 2) {

                                    boolean playerfound = false;
                                    for (DataSnapshot players : connections.getChildren()) {
                                        String getPlayerUID1 = players.getKey();
                                        if (getPlayerUID1.equals(playerUID))
                                        {
                                            playerfound = true;
                                            currentUser = true;
                                        } else if (playerfound) {
                                            String getNameEnemy = players.child("Nickname").getValue((String.class));
                                            NameEnemy = getNameEnemy;
                                            enemyUID = players.getKey();
                                            roomID = conID;
                                            enemyfound = true;
                                            databaseReference.child("rounds").child(roomID).addValueEventListener(roundsEventListener);
                                            setDialog(false);
                                            countdownStarted();
                                            databaseReference.child("connections").removeEventListener(this);

                                        }
                                    }
                                }
                            } else {
                                if (getPlayercount == 1) {
                                    connections.child(playerUID).child("Nickname").getRef().setValue(getPlayername);
                                    for (DataSnapshot players : connections.getChildren()) {
                                        String getNameEnemy = players.child("Nickname").getValue(String.class);
                                        enemyUID = players.getKey();
                                        NameEnemy = getNameEnemy;
                                        roomID = conID;
                                        enemyfound = true;
                                        databaseReference.child("rounds").child(roomID).addValueEventListener(roundsEventListener);
                                        countdownStarted();
                                        setDialog(false);
                                        databaseReference.child("connections").removeEventListener(this);

                                        break;
                                    }
                                }
                            }
                        }
                        if (!enemyfound && !status.equals("waiting"))
                        {
                            String connectionsUID = String.valueOf(System.currentTimeMillis());
                            snapshot.child(connectionsUID).child(playerUID).child("Nickname").getRef().setValue(getPlayername);
                            singleID = connectionsUID;
                            status = "waiting";

                        }
                    } else
                    {
                        String connectionsUID = String.valueOf(System.currentTimeMillis());
                        snapshot.child(connectionsUID).child(playerUID).child("Nickname").getRef().setValue(getPlayername);
                        singleID = connectionsUID;
                        status = "waiting";
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewing = LayoutInflater.from(this).inflate(R.layout.progress, null);
        builder.setView(viewing);
        builder.setCancelable(false);
        dialog = builder.create();
        setDialog(true);
        Button returnBack = viewing.findViewById(R.id.returnBack);
        returnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("connections").removeEventListener(connectUsers);
                databaseReference.child("connections").child(singleID).removeValue();
                status = "Matching";
                Intent nextPage = new Intent(GameMultiAct.this, TopicAct.class);
                startActivity(nextPage);
                GameMultiAct.this.finish();
            }
        });

        roundsEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               for(DataSnapshot datashot : snapshot.getChildren())
               {
                        if(datashot.hasChildren()) {

                        }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


        choice1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           if (currentUser) {
                                               ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerA));
                                               playercheck(1);
                                               choicesSelected();
                                           }
                                           else
                                           {
                                               ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerB));
                                               enemyCheck(1);
                                               choicesSelected();
                                           }

                                       }
                                   });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser) {
                    ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerA));
                   playercheck(2);
                    choicesSelected();
                }
                else
                {

                    ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerB));
                    enemyCheck(2);
                    choicesSelected();
                }

            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser) {
                    ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerA));
                    playercheck(3);
                    choicesSelected();
                }
                else
                {
                    ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerB));
                    enemyCheck(3);
                    choicesSelected();
                }

            }
        });

        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser) {
                    ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerA));
                   playercheck(4);
                    choicesSelected();
                }
                else
                {
                    ((Button)view).setBackgroundTintList(ColorStateList.valueOf(playerB));
                   enemyCheck(4);
                    choicesSelected();
                }

            }
        });
    }

    private void choicesSelected()
    {
        choice1.setEnabled(false);
        choice2.setEnabled(false);
        choice3.setEnabled(false);
        choice4.setEnabled(false);
    }

    private void setDialog(boolean show){
        if (show)
            dialog.show();
        else
            dialog.dismiss();
    }

    private void playercheck(int a)
    {
        if (a == listOfquestions.get(fixQ).getAnswerRight()) {
           score1++;
           String scoreA = String.valueOf(score1);
            databaseReference.child("rounds").child(roomID).child("Player_Score").setValue(scoreA);
        }
    }

    private void enemyCheck (int a)
    {
        if (a == listOfquestions.get(fixQ).getAnswerRight()) {
            score2++;
            String scoreB = String.valueOf(score2);
            databaseReference.child("rounds").child(roomID).child("Enemy_Score").setValue(scoreB);
        }
    }

    private void questionSetting()
    {
        qnsIndicator = 0;
        fixQ=0;
        countdownTimer.setText(String.valueOf(10));
        questionsNum.setText(String.valueOf(1) + "/" + String.valueOf(5));
        questionsBody.setText(listOfquestions.get(0).getQuizQuestion());
        choice1.setText(listOfquestions.get(0).getChoice1());
        choice2.setText(listOfquestions.get(0).getChoice2());
        choice3.setText(listOfquestions.get(0).getChoice3());
        choice4.setText(listOfquestions.get(0).getChoice4());
    }

    private void getListofQuestions() {
        Database data = new Database();
        listOfquestions = data.databaseNumbers();
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

    private void nextQuestion()
    {
        if (qnsIndicator < 5-1)
        {
            qnsIndicator++;
            //randomQns = new Random().nextInt(listOfquestions.size());
            fixQ = new Random().nextInt(listOfquestions.size());
            nextScreen(questionsBody,0,0);
            nextScreen(choice1,0,1);
            nextScreen(choice2,0,2);
            nextScreen(choice3,0,3);
            nextScreen(choice4,0,4);
            questionsNum.setText(String.valueOf(qnsIndicator+1) + "/" + String.valueOf(5));
            countdownTimer.setText(String.valueOf(10));
            choice1.setEnabled(true);
            choice2.setEnabled(true);
            choice3.setEnabled(true);
            choice4.setEnabled(true);
            countdownStarted();
        }
        else
        {
            databaseReference.child("rounds").child(roomID).removeEventListener(roundsEventListener);

            String Pname = getPlayername;
            String Ename = NameEnemy;
            ScoreNotifyAct scores;
            if(currentUser) {
                scores = new ScoreNotifyAct(this, Pname + "(Player)Has Scored " + score1 + "/5" + "\n" + Ename + "(Enemy)Has Scored " + score2 + "/5");
            }
            else
            {
                scores = new ScoreNotifyAct(this, Pname + "(Player)Has Scored " + score2 + "/5" + "\n" + Ename + "(Enemy)Has Scored " + score1 + "/5");
            }
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
                    //randomQns = new Random().nextInt(listOfquestions.size());
                    //randomQ.add(randomQns);
                    //fixQ = randomQ.get(qnsIndicator);
                    switch(count)
                    {
                        case 0:
                            ((TextView)v).setText(listOfquestions.get(fixQ).getQuizQuestion());
                            break;
                        case 1:
                            ((Button)v).setText(listOfquestions.get(fixQ).getChoice1());
                            break;
                        case 2:
                            ((Button)v).setText(listOfquestions.get(fixQ).getChoice2());
                            break;
                        case 3:
                            ((Button)v).setText(listOfquestions.get(fixQ).getChoice3());
                            break;
                        case 4:
                            ((Button)v).setText(listOfquestions.get(fixQ).getChoice4());
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
                answersRevealed();
      nextQuestion();
            }
        };
        counting.start();
    }

    private void answersRevealed()
    {
        switch (listOfquestions.get(fixQ).getAnswerRight())
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

}
