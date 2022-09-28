package com.example.multidialect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginAct extends AppCompatActivity {

    private Button loginButton , registerButton;
    private EditText emailAdd, password;
    private FirebaseAuth firebaseEnable;

    private void firebaseLoginProcess()
    {
        firebaseEnable.signInWithEmailAndPassword(emailAdd.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginAct.this,"Successful Login",Toast.LENGTH_SHORT).show();
                            Intent next1 = new Intent(LoginAct.this,TopicAct.class);
                            startActivity(next1);
                            finish();
                        } else {
                            Toast.makeText(LoginAct.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAdd = findViewById(R.id.emailAdd);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.Register);
        firebaseEnable = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextReg = new Intent(LoginAct.this,RegisterAct.class);
                startActivity(nextReg);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailAdd.getText().toString().isEmpty()) {
                    emailAdd.setError("Enter Email Address");
                    return;
                } else {
                    emailAdd.setError(null);
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Enter Password");
                    return;
                } else {
                    password.setError(null);
                }
                firebaseLoginProcess();
            }
        }
        );

       /*if(firebaseEnable.getCurrentUser() != null)
        {
            Intent next = new Intent(LoginAct.this, TopicAct.class);
           startActivity(next);
           finish();
        }*/
    }


}