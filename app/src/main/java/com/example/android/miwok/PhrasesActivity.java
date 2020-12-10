package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<word> num = new ArrayList<>();

        //Adding elements to the List
        num.add(new word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        num.add(new word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        num.add(new word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        num.add(new word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        num.add(new word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        num.add(new word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        num.add(new word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        num.add(new word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        num.add(new word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        num.add(new word("Come here.","әnni'nem",R.raw.phrase_come_here));

        wordAdapter adapter = new wordAdapter(this,  num,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                word wrd = num.get(position);

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,wrd.getAudioResourceId());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });




    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }


}