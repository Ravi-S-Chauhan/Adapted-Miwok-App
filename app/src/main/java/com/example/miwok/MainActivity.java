/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.miwok;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView numberTextView = findViewById(R.id.numbers);
        numberTextView.setOnClickListener(v -> {
            Intent i= new Intent(MainActivity.this,NumberActivity.class);
            startActivity(i);
        });


        TextView familyTextView = findViewById(R.id.family);
        familyTextView.setOnClickListener(v -> {
            Intent i= new Intent(MainActivity.this,FamilyMembersActivity.class);
            startActivity(i);

        });

        TextView colorTextView = findViewById(R.id.colors);
        colorTextView.setOnClickListener(v -> {
            Intent i= new Intent(MainActivity.this,ColorsActivity.class);
            startActivity(i);

        });

        TextView phrasesTextView = findViewById(R.id.phrases);
        phrasesTextView.setOnClickListener(v -> {
            Intent i= new Intent(MainActivity.this,PhrasesActivity.class);
            startActivity(i);

        });
    }

  }



