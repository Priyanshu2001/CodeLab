package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.recyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> wordsList = new ArrayList<>();
    ActivityMainBinding b;
    WordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        createData();
        setupAdapter();
        setupFAB();
    }

    public void createData() {

        for (int i = 0; i < 20; i++) {
            wordsList.add("Word " + i);
        }
    }

    private void setupAdapter() {
        //Create adapter object
        adapter = new WordListAdapter(this, wordsList);
        b.mReclyclerView.setAdapter(adapter);
        b.mReclyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setupFAB() {
        b.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create new word
                String newWord = "+ Word" + wordsList.size();

                //Add the word to list
                wordsList.add(newWord);

                //Notify adapter
                adapter.notifyItemInserted(wordsList.size() - 1);

                //Scroll to bottom
                b.mReclyclerView.smoothScrollToPosition(wordsList.size() - 1);
            }
        });
    }
}