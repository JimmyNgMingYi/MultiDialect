package com.example.multidialect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreNotifyAct extends Dialog
{
String Textmessages;
GameMultiAct mplay;
QuestionsAct play;
boolean single;
    public ScoreNotifyAct(@NonNull Context context , String Textmessages) {
        super(context);
        this.Textmessages = Textmessages;
        if(context instanceof QuestionsAct){
            single = true;
            play = ((QuestionsAct) context);
        }
        else if(context instanceof GameMultiAct){
            single = false;
            mplay = ((GameMultiAct) context);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_notify);
        TextView bodyMessages = findViewById(R.id.bodyMessages);
        Button goBack = findViewById(R.id.goBack);

        bodyMessages.setText(Textmessages);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                getContext().startActivity(new Intent(getContext(),TopicAct.class));
                if(single)
                play.finish();
                else
               mplay.finish();

            }
        });
    }
}