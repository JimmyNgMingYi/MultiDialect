package com.example.multidialect;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OptionsGridView extends BaseAdapter {
    private int optionsNum;

    public OptionsGridView(int optionsNum) {
        this.optionsNum = optionsNum;
    }

    @Override
    public int getCount() {
        return optionsNum;
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
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.options_single_view,viewGroup,false);
        }
        else
        {
            v = view;
        }

        switch (i)
        {
            case 0:
                ((TextView) v.findViewById(R.id.options_Num)).setText(String.valueOf(i+1));
                int bronze = Color.argb(255,205,127,50);
                v.setBackgroundTintList(ColorStateList.valueOf(bronze));
                break;
            case 1:
                ((TextView) v.findViewById(R.id.options_Num)).setText(String.valueOf(i+1));
                int silver = Color.argb(255,192,192,192);
                v.setBackgroundTintList(ColorStateList.valueOf(silver));
                break;
            case 2:
                ((TextView) v.findViewById(R.id.options_Num)).setText(String.valueOf(i+1));
                int gold = Color.argb(255,255,215,0);
                v.setBackgroundTintList(ColorStateList.valueOf(gold));
                break;
            case 3:
                ((TextView) v.findViewById(R.id.options_Num)).setText("Learn");
                int aqua = Color.argb(255,0,255,255);
                v.setBackgroundColor(aqua);
                break;
        }

       v.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (i == 3)
               {
                   Intent nextPage = new Intent(viewGroup.getContext(), MaterialsLearn.class);
                   viewGroup.getContext().startActivity(nextPage);
               }
               else
               {
                   Intent nextPage = new Intent(viewGroup.getContext(), QuestionsAct.class);
                   nextPage.putExtra("NUM", i);
                   viewGroup.getContext().startActivity(nextPage);
               }
           }
       });

        return v;
    }
}
