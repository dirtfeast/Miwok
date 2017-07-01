package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    // Listener triggered upon completion of MediaPlayer mp3 file
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // mp3 playback finished, release the media player resources
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // ArrayList of miwokWords objects
        // Define English and Miwok words
        final ArrayList<Word> miwokWords = new ArrayList<Word>();
        miwokWords.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        miwokWords.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        miwokWords.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        miwokWords.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        miwokWords.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        miwokWords.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        miwokWords.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        miwokWords.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // Custom WordAdapter class overrides ArrayAdapter
        // Pass context and object
        WordAdapter adapter = new WordAdapter(this, miwokWords, R.color.category_colors);

        // Find the ListView object in the view hierarchy of the Activity
        // There should be a {@link ListView} with the view ID called list,
        // which is declared in the word_list.xml file
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the ListView use the WordAdapter we created above, so that the
        // ListView will display list items for each Word object in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // Implements interface, so I have to define the method
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = miwokWords.get(position);

                // MediaPlayer to play MP3 file onItemClick
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getmRawResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            } // Close method onItemClick()

        }); // Close method call listView.setOnClickListener()

    } // Close method onCreate()

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    } // Closed override method onStop()


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        } // Close if
    } // Close method releaseMediaPlayer()

} // Close class ColorsActivity
