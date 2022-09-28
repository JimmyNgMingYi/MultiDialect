package com.example.multidialect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TopicAct extends AppCompatActivity {

    private GridView topicGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        topicGrid = findViewById(R.id.topicGrids);
        Toolbar topicBar = findViewById(R.id.topicBar);
        setSupportActionBar(topicBar);
        getSupportActionBar().setTitle("Topics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> topicList = new ArrayList<>();
        topicList.add("Number");
        topicList.add("Greetings");
        topicList.add("Jobs");
        topicList.add("Countries");
        topicList.add("Hobbies");
        topicList.add("Families");
        topicList.add("Food");
        topicList.add("Gestures");

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.numbers);
        imageList.add(R.drawable.greeting);
        imageList.add(R.drawable.jobs);
        imageList.add(R.drawable.countries);
        imageList.add(R.drawable.hobby);
        imageList.add(R.drawable.family);
        imageList.add(R.drawable.food);
        imageList.add(R.drawable.gestures);

        TopicGridView gridView = new TopicGridView(topicList,imageList);
        topicGrid.setAdapter(gridView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            Toast.makeText(TopicAct.this,"User Has Sign In Already",Toast.LENGTH_SHORT).show();
            Intent next = new Intent(TopicAct.this, LoginAct.class);
            startActivity(next);
            //TopicAct.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}