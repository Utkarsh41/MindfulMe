package com.utkarsh.scientific.mindfulMe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.utkarsh.scientific.mindfulMe.activityPack.ActivityProgressTrack;
import com.utkarsh.scientific.mindfulMe.audioTherapyPack.AudioTherapy;
import com.utkarsh.scientific.mindfulMe.egitaPack.EGita;
import com.utkarsh.scientific.mindfulMe.gamepack.Games;
import com.utkarsh.scientific.mindfulMe.mediPack.MeditationYoga;
import com.utkarsh.scientific.mindfulMe.cohortChatbot.SrMainActivity;
import com.utkarsh.scientific.mindfulMe.selfHelpBooksPack.SelfHelpBooks;

public class ExploreMindfulMe extends AppCompatActivity {
    
    private CardView mTalkWithKrishna,mEGita,mSelfHelpBook,mAudioTherapy,mMeditationYoga,mGames,mActivityProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_sarathi);
        
        mTalkWithKrishna = findViewById(R.id.cv_KrishnaChatbot);
        mEGita = findViewById(R.id.cv_Gita);
        mSelfHelpBook = findViewById(R.id.cv_Selfhelpbook);
        mAudioTherapy = findViewById(R.id.cv_Audiotherapy);
        mMeditationYoga = findViewById(R.id.cv_MeditationndYoga);
        mGames = findViewById(R.id.cardViewGames);
        mActivityProgress = findViewById(R.id.cv_ActivityProgress);


        mTalkWithKrishna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExploreMindfulMe.this, SrMainActivity.class);
                startActivity(i);
            }
        });
        mEGita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExploreMindfulMe.this, EGita.class);
                startActivity(i);
            }
        });
        mSelfHelpBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExploreMindfulMe.this, SelfHelpBooks.class);
                startActivity(i);
            }
        });
        mAudioTherapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExploreMindfulMe.this, AudioTherapy.class);
                startActivity(i);
            }
        });
        mMeditationYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExploreMindfulMe.this, MeditationYoga.class);
                startActivity(i);
            }
        });

        mGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExploreMindfulMe.this, Games.class);
                startActivity(i);
            }
        });
        mActivityProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExploreMindfulMe.this, ActivityProgressTrack.class);
                startActivity(i);
            }
        });



    }
}