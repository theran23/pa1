package edu.skku.cs.pa1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class NthTrial{
    public String word; // 원래는 Private하고 get/set function 써줘야함.

    //기본적으로 생성자 만들어야함.
    NthTrial(String word){
        this.word = word;// this가 붙은 것은 local, 안붙은 것은 instance var.
    }
}

public class WordListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<NthTrial> items;

    // ListViewAdapter 생성자가 필요하다
    public WordListViewAdapter(Context mContext, ArrayList<NthTrial> items){
        this.mContext=mContext;
        this.items=items;
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
            view = inflater.inflate(R.layout.wordlist_layout, viewGroup, false);
        }

        String word = items.get(i).word;

        int[] WordViewIDs = new int[] {R.id.word0, R.id.word1, R.id.word2, R.id.word3, R.id.word4 };
        for(int k=0; k<WordViewIDs.length; k++) {
            TextView t = view.findViewById(WordViewIDs[k]);
            t.setText(String.valueOf(word.charAt(k)));
            t.setBackgroundColor(Color.parseColor("#FF787C7E"));
            t.setTextColor(Color.parseColor("#FFFFFF"));
        }


        return view;
    }

}
