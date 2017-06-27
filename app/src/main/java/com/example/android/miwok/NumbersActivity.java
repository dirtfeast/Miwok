package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // ArrayList of miwokWords objects
        // Define English and Miwok words
        ArrayList<Word> miwokWords = new ArrayList<Word>();
        miwokWords.add(new Word("One", "lutti", R.drawable.number_one, R.raw.number_one));
        miwokWords.add(new Word("Two", "otiiko", R.drawable.number_two, R.raw.number_two));
        miwokWords.add(new Word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        miwokWords.add(new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        miwokWords.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        miwokWords.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        miwokWords.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        miwokWords.add(new Word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        miwokWords.add(new Word("Nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        miwokWords.add(new Word("Ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        // Locate root LinearLayout in word_list      // LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        // Add sub TextView to the root LinearLayout for each element
        // ArrayAdapter manages view recycling
        // Set text of each view

        // Custom WordAdapter class overrides ArrayAdapter
        // Pass context and object
        WordAdapter adapter = new WordAdapter(this, miwokWords, R.color.category_numbers);

        // Find the ListView object in the view hierarchy of the Activity
        // There should be a {@link ListView} with the view ID called list,
        // which is declared in the word_list.xml file
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the ListView use the WordAdapter we created above, so that the
        // ListView will display list items for each Word object in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // Implements interface, so I have to define the method
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast: pass in context, message, int duration
                // then call its show() method
                Toast.makeText(NumbersActivity.this, "List item clicked", Toast.LENGTH_SHORT).show();
            } // Close method onItemClick()

        }); // Close method call listView.setOnClickListener()

    } // Close method onCreate()
} // Close class NumbersActivity
