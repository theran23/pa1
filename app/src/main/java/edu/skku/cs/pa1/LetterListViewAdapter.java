package edu.skku.cs.pa1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class LetterListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> items;
    private String color;

    // ListViewAdapter 생성자가 필요하다
    public LetterListViewAdapter(Context mContext, ArrayList<String> items, String color){
//        Log.v("'test'","!!!!!!!!!!!!!!!!!!!!!!");
        this.mContext=mContext;
        this.items=items;
        this.color = color;
    }

    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int i){
        return items.get(i);
    }
    @Override
    public long getItemId(int i){
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){ // is important!!
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.letterlist_layout, viewGroup, false);
        }

        String letter = items.get(i);


        TextView t = view.findViewById(R.id.letter);
        t.setText(letter.toUpperCase());
        if(color=="gray"){
            t.setBackgroundColor(ContextCompat.getColor(mContext, R.color.background_out));
            t.setTextColor(ContextCompat.getColor(mContext, R.color.text_out));
        }
        else if(color=="green"){
            t.setBackgroundColor(ContextCompat.getColor(mContext, R.color.background_strike));
            t.setTextColor(ContextCompat.getColor(mContext, R.color.text_strike));
        }
        else{
            t.setBackgroundColor(ContextCompat.getColor(mContext, R.color.background_ball));
            t.setTextColor(ContextCompat.getColor(mContext, R.color.text_ball));
        }

        return view;
    }

}
