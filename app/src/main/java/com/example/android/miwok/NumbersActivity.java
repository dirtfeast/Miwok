package com.example.android.miwok;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

//        String[] words = new String[] {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
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
        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        // Add sub TextView to the root LinearLayout for each element
        // Set text of each view

        for(int index = 0; index < miwokWords.size();index++) {
            TextView wordView = new TextView(this);
            wordView.setText(miwokWords.get(index));
            rootView.addView(wordView);
        }

//        int index = 0;
//        while(index < miwokWords.size()) {
//            TextView wordView = new TextView(this);
//            wordView.setText(miwokWords.get(index));
//            rootView.addView(wordView);
//            index++;
//        }

    }
}
