package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;


    private final MediaPlayer.OnCompletionListener mCompletionListener = MediaPlayer -> releaseResource();

    public void releaseResource() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            audioManager.abandonAudioFocus(mAudioFocusChangeListener);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("NumberActivity", "Number Stop");
        releaseResource();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioFocusChangeListener = focusChange -> {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.stop();
                releaseResource();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mMediaPlayer.stop();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.seekTo(0);
            }

        };


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int  focusRequest = audioManager.requestAudioFocus(mAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);


        final ArrayList<word> words = new ArrayList<>();

        words.add(new word("One", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new word("Two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new word("Ten", "na'accha", R.drawable.number_ten, R.raw.number_ten));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener((parent, view, position, id) -> {
            releaseResource();
            word currentView = words.get(position);
            if ( focusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                Log.v("NumbersActivity", "Current word: " + currentView);
                mMediaPlayer = MediaPlayer.create(NumberActivity.this, currentView.getMediaResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }

        });


    }

}