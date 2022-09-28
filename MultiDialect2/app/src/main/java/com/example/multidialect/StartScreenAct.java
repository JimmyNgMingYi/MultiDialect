package com.example.multidialect;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class StartScreenAct extends AppCompatActivity {

    private TextView nameApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        nameApp = findViewById(R.id.nameApp);

        @SuppressLint("ResourceType") Animation animator = AnimationUtils.loadAnimation(this, R.animator.animation);
        nameApp.setAnimation(animator);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent next = new Intent(StartScreenAct.this,LoginAct.class);
                startActivity(next);

            }
        }).start();
    }
}
