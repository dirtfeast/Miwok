package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // ArrayList of miwoWords objects
        // Define English and Miwok words
        ArrayList<Word> miwokWords = new ArrayList<Word>();
        miwokWords.add(new Word("father", "әpә"));
        miwokWords.add(new Word("mother", "әṭa"));
        miwokWords.add(new Word("son", "angsi"));
        miwokWords.add(new Word("daughter", "tune"));
        miwokWords.add(new Word("older brother", "taachi"));
        miwokWords.add(new Word("younger brother", "chalitti"));
        miwokWords.add(new Word("older sister", "teṭe"));
        miwokWords.add(new Word("younger sister", "kolliti"));
        miwokWords.add(new Word("grandmother", "ama"));
        miwokWords.add(new Word("grandfather", "paapa"));

        // Locate root LinearLayout in word_list      // LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        // Add sub TextView to the root LinearLayout for each element
        // ArrayAdapter manages view recycling
        // Set text of each view

        // Custom WordAdapter class overrides ArrayAdapter
        // Pass context and object
        WordAdapter adapter = new WordAdapter(this, miwokWords);

        // Find the ListView object in the view hierarchy of the Activity
        // There should be a {@link ListView} with the view ID called list,
        // which is declared in the word_list.xml file
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the ListView use the WordAdapter we created above, so that the
        // ListView will display list items for each Word object in the list.
        listView.setAdapter(adapter);

    } // Close method onCreate()
} // Close class NumbersActivity
