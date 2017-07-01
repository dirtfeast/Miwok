package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    // Object to handle playback of mp3 files
    private MediaPlayer mMediaPlayer;

    // Object to invoke audio focus gain and release
    private AudioManager mAudioManager;

    // Object to track audio focus changes
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } // Close if
        } // Close method override onAudioFocusChange()
    }; // Close method AudioManager.OnAudioFocusChangeListener()

    // Listener object triggered upon completion of MediaPlayer mp3 file
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        // mp3 playback finished, release the media player resources, abandon audio focus
        {releaseMediaPlayer();} // Close method override onCompletion()
    }; // Close method  MediaPlayer.OnCompletionListener()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Request Audio Focus and register OnAudioFocusChangeListener
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        // ArrayList of miwokWords objects
        // Define English and Miwok words
        final ArrayList<Word> miwokWords = new ArrayList<Word>();
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

        // ArrayAdapter manages view recycling

        // Custom WordAdapter class overrides ArrayAdapter
        // Pass context and object
        WordAdapter adapter = new WordAdapter(this, miwokWords, R.color.category_numbers);

        // Find the ListView object in the view hierarchy of the Activity
        // There should be a ListView with the view ID called list,
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

                // Get the Word object at the given position the user clicked on
                Word word = miwokWords.get(position);

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // if audio focus granted
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // MediaPlayer to play MP3 file onItemClick
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmRawResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                } // Close if

        // Toast: pass in context, message, int duration
        // then call its show() method
        // Toast.makeText(NumbersActivity.this, "List item clicked", Toast.LENGTH_SHORT).show();
        } // Close method onItemClick()

        }); // Close method call listView.setOnClickListener()

    } // Close method onCreate()

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    } // Close override method onStop()

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

            // Abandon audio focus
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        } // Close if

    } // Close method releaseMediaPlayer()

} // Close class NumbersActivity
