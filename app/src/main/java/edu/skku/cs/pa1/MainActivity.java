package edu.skku.cs.pa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private WordListViewAdapter wordlistviewadpater;
    private ArrayList<NthTrial> items;

    public static Context mContext;
    public String the_word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        String result = "";
        try {
            InputStream is = getApplicationContext().getAssets().open("wordle_words.txt");
            result = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
//            result = "error !!";
//            e.printStackTrace();
        }
        String dictionaries[]=result.split("\\r?\\n");
        Random r = new Random();
        TextView t = findViewById(R.id.textview);

        the_word = dictionaries[r.nextInt(dictionaries.length)];
        t.setText(the_word);



        ListView wordlist = findViewById(R.id.wordlist);
        Button submit_btn = findViewById(R.id.submit_btn);

        items = new ArrayList<NthTrial>();

        submit_btn.setOnClickListener(view->{
            EditText edittext = findViewById(R.id.edittext);
            String input_word = edittext.getText().toString();

            boolean in_dictionaries = false;
            for(int i=0; i< dictionaries.length; i++){
                if(dictionaries[i].equals(input_word)){
                    in_dictionaries = true;
                }
            }
            if(in_dictionaries){
                items.add(new NthTrial(input_word));
                wordlistviewadpater = new WordListViewAdapter(getApplicationContext(), items, the_word);
                wordlist.setAdapter(wordlistviewadpater);
                edittext.setText("");
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "Word '"+input_word+"' not in dictionary!",
                        Toast.LENGTH_SHORT).show();
            }

        });


    }
}