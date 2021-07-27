package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<word> family = new ArrayList<>();

        family.add(new word("father","әpә"));
        family.add(new word("mother","әṭa"));
        family.add(new word("son","angsi"));
        family.add(new word("daughter","tune"));
        family.add(new word("older brother","taachi"));
        family.add(new word("younger brother","chalitti"));
        family.add(new word("older sister","teṭe"));
        family.add(new word("younger sister","kolliti"));
        family.add(new word("grandmother","ama"));
        family.add(new word("grand father","paapa"));

        WordAdapter wordAdapter = new  WordAdapter(this,family);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(wordAdapter);

    }
}