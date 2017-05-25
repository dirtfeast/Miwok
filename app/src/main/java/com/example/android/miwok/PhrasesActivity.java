package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // ArrayList of miwoWords objects
        // Define English and Miwok words
        ArrayList<Word> miwokWords = new ArrayList<Word>();
        miwokWords.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        miwokWords.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        miwokWords.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        miwokWords.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        miwokWords.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        miwokWords.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        miwokWords.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        miwokWords.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        miwokWords.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        miwokWords.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        // Locate root LinearLayout in word_list
        // LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        // Add sub TextView to the root LinearLayout for each element
        // ArrayAdapter manages view recycling
        // Set text of each view

        // Custom WordAdapter class overrides ArrayAdapter
        // Pass context and object
        WordAdapter adapter = new WordAdapter(this, miwokWords, R.color.category_phrases);

        // Find the ListView object in the view hierarchy of the Activity
        // There should be a {@link ListView} with the view ID called list,
        // which is declared in the word_list.xml file
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the ListView use the WordAdapter we created above, so that the
        // ListView will display list items for each Word object in the list.
        listView.setAdapter(adapter);

    } // Close method onCreate()
} // Close class NumbersActivity
