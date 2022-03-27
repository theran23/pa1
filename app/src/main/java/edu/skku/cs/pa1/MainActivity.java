package edu.skku.cs.pa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WordListViewAdapter wordlistviewadpater;
    private ArrayList<NthTrial> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView wordlist = findViewById(R.id.wordlist);
        Button submit_btn = findViewById(R.id.submit_btn);

        items = new ArrayList<NthTrial>();

        submit_btn.setOnClickListener(view->{
            EditText edittext = findViewById(R.id.edittext);
            String input_word = edittext.getText().toString();

            items.add(new NthTrial(input_word));

            wordlistviewadpater = new WordListViewAdapter(getApplicationContext(), items);
            wordlist.setAdapter(wordlistviewadpater);
        });


    }
}