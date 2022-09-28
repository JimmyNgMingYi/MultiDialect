package com.example.multidialect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MaterialsLearn extends AppCompatActivity {
    private int tNum;
    private String tName;
    private TextView qrName,loadText;
    private ImageView qrScan;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials_learn);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        tNum = sharedPref.getInt("topicID",0);
        tName = sharedPref.getString("topicName","");
        Toolbar learnBar = findViewById(R.id.LearnBar);
        setSupportActionBar(learnBar);
        getSupportActionBar().setTitle(tName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        qrName = findViewById(R.id.qrCodeText);
        qrScan = findViewById(R.id.zoomQrCode);
        loadText = findViewById(R.id.loadingText);
        checkQrCodeName();
        loadLearnText();
        qrScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MaterialsLearn.this);
                View view1 = LayoutInflater.from(MaterialsLearn.this).inflate(R.layout.qr_scanner, null);
                builder.setView(view1);

                ImageView qrCode = view1.findViewById(R.id.qrCode);
                Button close = view1.findViewById(R.id.closeBtn);

                switch(tNum) {
                    case 0:
                    qrCode.setImageResource(R.drawable.qr_number);
                        break;
                    case 1:
                        qrCode.setImageResource(R.drawable.qr_greet);
                        break;
                    case 2:
                        qrCode.setImageResource(R.drawable.qr_job);
                        break;
                    case 3:
                        qrCode.setImageResource(R.drawable.qr_flag);
                        break;
                    case 4:
                        qrCode.setImageResource(R.drawable.qr_hobby);
                        break;
                    case 5:
                        qrCode.setImageResource(R.drawable.qr_family);
                        break;
                    case 6:
                        qrCode.setImageResource(R.drawable.qr_food);
                        break;
                    case 7:
                        qrCode.setImageResource(R.drawable.qr_okay);
                        break;

                }

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void checkQrCodeName()
    {
        switch(tNum)
        {
            case 0:
                qrName.setText("すうじ (suuji)");
                break;
            case 1:
                qrName.setText("どうも (Domo)");
                break;
            case 2:
                qrName.setText("ドクター (Dokutā)");
                break;
            case 3:
                qrName.setText("にっぽんのこっき (Nihon no kokki)");
                break;
            case 4:
                qrName.setText("おもちゃの列車 (Omocha no ressha)");
                break;
            case 5:
                qrName.setText("ザ・シンプソンズ (Za shinpusonzu)");
                break;
            case 6:
                qrName.setText("まぐろ寿司 (Maguro sushi)");
                break;
            case 7:
                qrName.setText("わかった (Wakatta)");
                break;
        }
    }

    private void loadLearnText(){
        switch (tNum)
            {
                case 0:
                    loadText.setText(getResources().getString(R.string.numbers));
                    break;
                case 1:
                    loadText.setText(getResources().getString(R.string.Greetings));
                    break;
                case 2:
                    loadText.setText(getResources().getString(R.string.Jobs));
                    break;
                case 3:
                    loadText.setText(getResources().getString(R.string.Countries));
                    break;
                case 4:
                    loadText.setText(getResources().getString(R.string.Hobbies));
                    break;
                case 5:
                    loadText.setText(getResources().getString(R.string.Family));
                    break;
                case 6:
                    loadText.setText(getResources().getString(R.string.Food));
                    break;
                case 7:
                    loadText.setText(getResources().getString(R.string.Gestures));
                    break;
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            MaterialsLearn.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}