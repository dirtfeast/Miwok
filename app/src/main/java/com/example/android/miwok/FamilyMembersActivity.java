package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    // Handles playback of mp3 files
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
        miwokWords.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        miwokWords.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        miwokWords.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        miwokWords.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        miwokWords.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        miwokWords.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        miwokWords.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        miwokWords.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        miwokWords.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        miwokWords.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        // Custom WordAdapter class overrides ArrayAdapter
        // Pass context and object
        WordAdapter adapter = new WordAdapter(this, miwokWords, R.color.category_family);

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
                mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, word.getmRawResourceId());
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

} // Close class FamilyMembersActivity
