package edu.skku.cs.pa1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private WordListViewAdapter wordlistviewadpater;
    private LetterListViewAdapter graylistviewadapter,greenlistviewadapter,yellowlistviewadapter;
    private ArrayList<NthTrial> items;
    private ArrayList<String> greens, yellows, grays;

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
        the_word = dictionaries[r.nextInt(dictionaries.length)];



        ListView wordlist = findViewById(R.id.wordlist);
        ListView graylist = findViewById(R.id.graylist);
        ListView greenlist = findViewById(R.id.greenlist);
        ListView yellowlist = findViewById(R.id.yellowlist);
        Button submit_btn = findViewById(R.id.submit_btn);

        items = new ArrayList<NthTrial>();
        yellows = new ArrayList<String>();
        greens = new ArrayList<String>();
        grays = new ArrayList<String>();

        wordlistviewadpater = new WordListViewAdapter(getApplicationContext(), items, the_word);
//        wordlist.setAdapter(wordlistviewadpater);
        graylistviewadapter = new LetterListViewAdapter(getApplicationContext(), grays, "gray");
        greenlistviewadapter = new LetterListViewAdapter(getApplicationContext(), greens, "green");
        yellowlistviewadapter = new LetterListViewAdapter(getApplicationContext(), yellows, "yellow");
//        graylist.setAdapter(graylistviewadapter);
//        greenlist.setAdapter(greenlistviewadapter);
//        yellowlist.setAdapter(yellowlistviewadapter);


        submit_btn.setOnClickListener(view->{
            EditText edittext = findViewById(R.id.edittext);
            String input_word = edittext.getText().toString().toLowerCase();

            boolean in_dictionaries = false;
            for(int i=0; i< dictionaries.length; i++){
                if(dictionaries[i].equals(input_word)){
                    in_dictionaries = true;
                }
            }
            if(in_dictionaries){
                items.add(new NthTrial(input_word));
//                wordlistviewadpater = new WordListViewAdapter(getApplicationContext(), items, the_word);
                wordlist.setAdapter(wordlistviewadpater);


                for(int k=0; k<5; k++) {
                    if(the_word.indexOf(input_word.charAt(k))<0){
                        if(grays.indexOf(Character.toString(input_word.charAt(k)))<0){
                            grays.add(Character.toString(input_word.charAt(k)));

                        }
                    }
                    else if(the_word.charAt(k)==input_word.charAt(k)){
                        if(greens.indexOf(Character.toString(input_word.charAt(k)))<0){
                            greens.add(Character.toString(input_word.charAt(k)));
                        }
                        if(yellows.indexOf(Character.toString(input_word.charAt(k)))>=0){
                            yellows.remove(Character.toString(input_word.charAt(k)));
                        }
                    }
                    else{
                        if(greens.indexOf(Character.toString(input_word.charAt(k)))<0 && yellows.indexOf(Character.toString(input_word.charAt(k)))<0){
                            yellows.add(Character.toString(input_word.charAt(k)));
                        }
                    }
                }

//                graylistviewadapter = new LetterListViewAdapter(getApplicationContext(), grays, "gray");
//                greenlistviewadapter = new LetterListViewAdapter(getApplicationContext(), greens, "green");
//                yellowlistviewadapter = new LetterListViewAdapter(getApplicationContext(), yellows, "yellow");
                graylist.setAdapter(graylistviewadapter);
                greenlist.setAdapter(greenlistviewadapter);
                yellowlist.setAdapter(yellowlistviewadapter);



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