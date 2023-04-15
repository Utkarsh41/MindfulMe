package com.utkarsh.scientific.mindfulMe.activityPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.utkarsh.scientific.mindfulMe.R;
import com.utkarsh.scientific.mindfulMe.activityPack.mobileusage.MobileUsage;
import com.utkarsh.scientific.mindfulMe.activityPack.moodtrack.MoodTracker;
import com.utkarsh.scientific.mindfulMe.activityPack.sleepcycle.SleepCycleM;

public class ActivityProgressTrack extends AppCompatActivity {

    CardView moodTrackerCv,sleepCycleCv,mobUsageCv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_track);

        moodTrackerCv = findViewById(R.id.MoodTrackerCV);
        sleepCycleCv = findViewById(R.id.SleepTrackerCV);
        mobUsageCv =findViewById(R.id.MobileUsageCV);

        moodTrackerCv.setOnClickListener(view -> startActivity(new Intent(ActivityProgressTrack.this, MoodTracker.class)));

        sleepCycleCv.setOnClickListener(view -> startActivity(new Intent(ActivityProgressTrack.this, SleepCycleM.class)));
        mobUsageCv.setOnClickListener(view -> {
            startActivity(new Intent(ActivityProgressTrack.this, MobileUsage.class));
        });
    }
}