package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    private MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseResource();

    private void releaseResource() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("PhraseActivity", "Phrase Stop");
        releaseResource();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mOnAudioFocusChangeListener = focusChange -> {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
                mMediaPlayer.start();
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.stop();
                releaseResource();
            }
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.seekTo(0);
            }
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
                mMediaPlayer.stop();
        };

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int focusRequest = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

        final ArrayList<word> phrase = new ArrayList<>();

        phrase.add(new word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phrase.add(new word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrase.add(new word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phrase.add(new word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrase.add(new word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrase.add(new word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrase.add(new word("Yes, I'm coming.", "hәә' әәnәm", R.raw.phrase_yes_im_coming));
        phrase.add(new word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        phrase.add(new word("Let's go.", "yoowutis", R.raw.phrase_lets_go));
        phrase.add(new word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this, phrase, R.color.category_phrases);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            releaseResource();
            word currentView = phrase.get(position);
            if(focusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                Log.v("NumbersActivity", "Current word: " + currentView);

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, currentView.getMediaResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }

        });


    }
}