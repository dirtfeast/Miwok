package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    // Object to handle playback of mp3 files
    private MediaPlayer mMediaPlayer;

    // Object to invoke audio focus gain and release
    private AudioManager mAudioManager;

    // Listener object to track audio focus changes
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
    }; // Close method MediaPlayer.OnCompletionListener()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Request Audio Focus and register OnAudioFocusChangeListener
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        // ArrayList of miwokWords objects
        // Define English and Miwok words
        final ArrayList<Word> miwokWords = new ArrayList<Word>();
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

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // Implements interface, so I have to define the method
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Get the {@link Word} object at the given position the user clicked on
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
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmRawResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                } // Close if

            } // Close override method onItemClick()

        }); // Close method call listView.setOnClickListener()

    } // Close method onCreate()

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    } // Close override method onStop()

    // Clean up the media player
    // Release resources, set to null, abandon audio focus
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        } // Close if
    } // Close method releaseMediaPlayer()

} // Close class PhrasesActivity
