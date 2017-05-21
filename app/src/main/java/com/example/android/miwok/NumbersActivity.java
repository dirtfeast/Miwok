package com.example.android.miwok;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> miwokWords = new ArrayList<String>();
        miwokWords.add("One");
        miwokWords.add("Two");
        miwokWords.add("Three");
        miwokWords.add("Four");
        miwokWords.add("Five");
        miwokWords.add("Six");
        miwokWords.add("Seven");
        miwokWords.add("Eight");
        miwokWords.add("Nine");
        miwokWords.add("Ten");

        // Locate root LinearLayout in activity_numbers.xml
        // LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        // Add sub TextView to the root LinearLayout for each element
        // ArrayAdapter manages view recycling
        // Set text of each view
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, miwokWords);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

    } // Close method onCreate()
} // Close class NumbersActivity
