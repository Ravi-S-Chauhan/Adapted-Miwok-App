package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<word> phrase = new ArrayList<>();

        phrase.add(new word("Where are you going?","minto wuksus"));
        phrase.add(new word("What is your name?","tinnә oyaase'nә"));
        phrase.add(new word("My name is...","oyaaset..."));
        phrase.add(new word("How are you feeling?","michәksәs?"));
        phrase.add(new word("I’m feeling good.", "kuchi achit"));
        phrase.add(new word("Are you coming?","әәnәs\'aa?"));
        phrase.add(new word("Yes, I\'m coming.","hәә\' әәnәm"));
        phrase.add(new word("I’m coming.","әәnәm"));
        phrase.add(new word("Let\'s go.","yoowutis"));
        phrase.add(new word("Come here.","әnni\'nem"));

        WordAdapter phraseAdapter = new WordAdapter(this,phrase);

        ListView phraseListView = findViewById(R.id.list);

        phraseListView.setAdapter(phraseAdapter);


    }
}