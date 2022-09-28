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

public class RegisterAct extends AppCompatActivity {

    private Button RegisterButton;
    private EditText RemailAdd, Rpassword , passwordCFM;
    private FirebaseAuth firebaseEnable;

    private void firebaseRegisterProcess() {
        firebaseEnable.createUserWithEmailAndPassword(RemailAdd.getText().toString(), Rpassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterAct.this,"Successful Registration",Toast.LENGTH_SHORT).show();
                            Intent nextPage = new Intent(RegisterAct.this,TopicAct.class);
                            startActivity(nextPage);
                            finish();
                        } else {
                            Toast.makeText(RegisterAct.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RemailAdd = findViewById(R.id.RemailAdd);
        Rpassword = findViewById(R.id.Rpassword);
        RegisterButton = findViewById(R.id.RegisterButton);
        firebaseEnable = FirebaseAuth.getInstance();
        passwordCFM = findViewById(R.id.passwordConfirm);


        RegisterButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  if (RemailAdd.getText().toString().isEmpty()) {
                                                      RemailAdd.setError("Enter Email Address");
                                                      return;
                                                  } else {
                                                      RemailAdd.setError(null);
                                                  }
                                                  if (Rpassword.getText().toString().isEmpty()) {
                                                      Rpassword.setError("Enter Password");
                                                      return;
                                                  } else {
                                                      Rpassword.setError(null);
                                                  }
                                                  if (passwordCFM.getText().toString().isEmpty() ||
                                                          !passwordCFM.getText().toString().equals(Rpassword.getText().toString()))
                                                  {
                                                      passwordCFM.setError("Password is not matching");
                                                      return;
                                                  }
                                                  else
                                                  {
                                                      passwordCFM.setError(null);
                                                  }

                                                  firebaseRegisterProcess();
                                              }
                                          }
        );

        /*if(firebaseEnable.getCurrentUser() != null)
        {
            Intent next = new Intent(RegisterAct.this, TopicAct.class);
            startActivity(next);
            finish();
        }
        else
        {
            Intent back = new Intent (RegisterAct.this , LoginAct.class);
            startActivity(back);
            finish();
        }*/
    }



}