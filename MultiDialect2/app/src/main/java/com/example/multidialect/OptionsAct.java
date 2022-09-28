package com.example.multidialect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

public class OptionsAct extends AppCompatActivity {

    private GridView options_view;
    private Button Invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Toolbar optionsBar = findViewById(R.id.optionsBar);
        setSupportActionBar(optionsBar);
        String name = getIntent().getStringExtra("TOPICS");
        getSupportActionBar().setTitle(name);
        options_view = findViewById(R.id.options_views);
        OptionsGridView oGridview = new OptionsGridView(4);
        options_view.setAdapter(oGridview);

        Invite = findViewById(R.id.Invite);
        int topicNum = getIntent().getIntExtra("P",0);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editing = sharedPref.edit();
        editing.putInt("topicID",topicNum);
        editing.putString("topicName" , name);
        editing.apply();

        EditText playName = findViewById(R.id.playName);

        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String getPlayername = playName.getText().toString();
                    if (TextUtils.isEmpty(getPlayername)) {
                        Toast.makeText(OptionsAct.this, "Enter a name!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent nextPage = new Intent(OptionsAct.this, GameMultiAct.class);
                        nextPage.putExtra("L", getPlayername);
                        startActivity(nextPage);
                        finish();
                    }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            OptionsAct.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}