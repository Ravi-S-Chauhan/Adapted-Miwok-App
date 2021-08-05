package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

MediaPlayer mMediaPlayer;
AudioManager audioManager;
AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener ;


private final MediaPlayer.OnCompletionListener  mCompletionListener = mediaPlayer -> releaseResource();

    private void releaseResource() {
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v("FamilyActivity","Family Stop");
        releaseResource();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mOnAudioFocusChangeListener = focusChange -> {
            if(focusChange ==AudioManager.AUDIOFOCUS_GAIN ){
                mMediaPlayer.start();
            }
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS ){
                mMediaPlayer.stop();
                releaseResource();
            }
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mMediaPlayer.stop();
            }
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.seekTo(0);
            }
        };

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int focusRequest = audioManager.requestAudioFocus(mOnAudioFocusChangeListener , AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

       final ArrayList<word> family = new ArrayList<>();

        family.add(new word("father","әpә",R.drawable.family_father,R.raw.family_father));
        family.add(new word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        family.add(new word("son","angsi",R.drawable.family_son,R.raw.family_son));
        family.add(new word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter wordAdapter = new  WordAdapter(this,family,R.color.category_family);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            releaseResource();
            word currentView = family.get(position);
            if( focusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                Log.v("NumbersActivity", "Current word: " + currentView);

                mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, currentView.getMediaResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }

        });

    }

}