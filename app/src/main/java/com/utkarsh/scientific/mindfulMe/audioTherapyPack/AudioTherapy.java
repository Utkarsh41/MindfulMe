package com.utkarsh.scientific.mindfulMe.audioTherapyPack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.utkarsh.scientific.mindfulMe.R;
import com.utkarsh.scientific.mindfulMe.selfHelpBooksPack.ViewPdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioTherapy extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    ProgressDialog pd;
    List<AudioModel>audioModelList;
    SongAdapter songAdapter;
    FloatingActionButton floatingActionButton;

    Query query;
    MediaPlayer mediaPlayer;

    public ImageView mPlayButton;

    boolean isAudioPlayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_therapy);
//        mPlayButton  = findViewById(R.id.playButton);
//        loadAudioSetup();

        pd = new ProgressDialog(this);
        floatingActionButton = findViewById(R.id.fabPause);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("audioTherapy");
        mRecyclerView = findViewById(R.id.myRecyclerViewAudio);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        audioModelList=new ArrayList<>();
        songAdapter =new SongAdapter(AudioTherapy.this,audioModelList);
        mRecyclerView.setAdapter(songAdapter);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        Toast.makeText(this, "Use Headphones For Better Experience", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "Use Headphones For Better Experience", Toast.LENGTH_LONG).show();

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                audioModelList.clear();
                for(DataSnapshot dss:snapshot.getChildren()){
                    AudioModel audioModel = dss.getValue(AudioModel.class);
//                    audioModel.setaDesc(
                    audioModelList.add(audioModel);
                }
                songAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AudioTherapy.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AudioTherapy.this, "Stopping Audio", Toast.LENGTH_LONG).show();
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer=null;
                }
                else {
                    Toast.makeText(AudioTherapy.this, "No Audio is playing", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(valueEventListener);
        if(mediaPlayer!=null) {
            mediaPlayer.release();
        }

    }



    private void loadAudioSetup() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("audioTherapy");
        mRecyclerView = findViewById(R.id.myRecyclerViewAudio);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        audioModelList=new ArrayList<>();
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        mediaPlayer = new MediaPlayer();


        query = databaseReference.orderByChild("filename");
        //we will request the files with the filename, if filename exists then it will show in the recyclerView
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    progressBar.setVisibility(View.GONE);
                    showAudioFiles();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(AudioTherapy.this, ":", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }

    private void showAudioFiles() {

        FirebaseRecyclerOptions<AudioModel> options = new FirebaseRecyclerOptions.Builder<AudioModel>()
                .setQuery(query, AudioModel.class)
                .build();
        FirebaseRecyclerAdapter<AudioModel, AudioAdapter> adapter = new FirebaseRecyclerAdapter<AudioModel, AudioAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AudioAdapter holder, int position, @NonNull AudioModel model) {

//                progressBar.setVisibility(View.GONE);
                holder.audioTitle.setText(model.getaTitle());
                holder.audioDes.setText(model.getaDesc());
                String audioUrl = model.getaUrl();
                String audioTitile = model.getaTitle();

                holder.mplayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AudioTherapy.this, ViewPdf.class);
                        intent.putExtra("audioUrl",audioUrl);
                        intent.putExtra("audioTitle",audioTitile);
                        startActivity(intent);
//                        finish();
                    }
                });

            }
            @NonNull
            @Override
            public AudioAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
                return new AudioAdapter(view);
            }
        };

        mRecyclerView.setAdapter(adapter);
        adapter.startListening();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void playSong(List<AudioModel> listAudioModels, int adapterPosition) throws IOException {
        pd.setTitle("Loading Song");
        pd.setMessage("Please wait...");
        pd.show();
        AudioModel audioModel = listAudioModels.get(adapterPosition);
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(audioModel.getaUrl());
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                pd.dismiss();
            }
        });
        mediaPlayer.prepareAsync();
    }

/***
    private void playAudio(String audioUrl) {

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(audioUrl);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
            mediaPlayer.start();

        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
        // below line is use to display a toast message.
        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();
        mPlayButton=(ImageView) findViewById(R.id.playButton);
        mPlayButton.setVisibility(View.GONE);


    }***/

}