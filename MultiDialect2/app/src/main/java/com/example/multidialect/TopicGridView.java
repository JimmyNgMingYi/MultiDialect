package com.example.multidialect;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class TopicGridView extends BaseAdapter {

    private List<String> topicList;
    private List<Integer> imageList;

    public TopicGridView(List<String> topicList , List<Integer> imageList)
    {
        this.topicList = topicList;
        this.imageList = imageList;
    }
    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v;
        if(view == null)
        {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_single_view,viewGroup,false);
        }
        else
        {
            v = view;
        }

        ((TextView) v.findViewById(R.id.topicName)).setText(topicList.get(i));
        ((ImageView) v.findViewById(R.id.imageView4)).setImageResource(imageList.get(i));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent optionsNext = new Intent(viewGroup.getContext(),OptionsAct.class);
                optionsNext.putExtra("TOPICS" , topicList.get(i));
                optionsNext.putExtra("P" , i);
                viewGroup.getContext().startActivity(optionsNext);
            }
        });

        Random d = new Random();
        int mixRed = d.nextInt(128) + 128;
        int mixGreen = d.nextInt(128) + 128;
        int mixBlue = d.nextInt(128) + 128;
        int colorful = Color.argb(255,mixRed,mixGreen,mixBlue);
        v.setBackgroundColor(colorful);

            return v;
    }
}
