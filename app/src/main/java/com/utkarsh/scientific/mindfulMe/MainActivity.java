package com.utkarsh.scientific.mindfulMe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionTv;
    private   android.widget.Button option1,option2,option3,option4,exploreSarathiButton;
    private ImageSlider mImageSlider;

    Random random;
    int currScore=0,questionAttempted=1,currPos;
    private ArrayList<QuestionModal> questionModalArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageSlider = findViewById(R.id.imageSlider);
        questionTv = findViewById(R.id.questionTv);
        exploreSarathiButton=findViewById(R.id.exploreApp);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        questionModalArrayList=new ArrayList<>();
        random = new Random();

        getQuestions(questionModalArrayList);




        ArrayList<SlideModel>slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1564121211835-e88c852648ab?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1620147461831-a97b99ade1d3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8bWVudGFsJTIwaGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1564682895970-0dbb72e18d97?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fG1lbnRhbCUyMGhlYWx0aHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1603729336521-9bff55419157?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OTl8fG1lbnRhbCUyMGhlYWx0aHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://plus.unsplash.com/premium_photo-1663090635094-5cc9111742ae?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8bWVudGFsJTIwaGVhbHRoJTIwc3VwcG9ydHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1501770118606-b1d640526693?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bWVudGFsJTIwaGVhbHRoJTIwc3VwcG9ydHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", ScaleTypes.FIT));

        mImageSlider.setImageList(slideModels,ScaleTypes.FIT);


        exploreSarathiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ExploreMindfulMe.class);
                startActivity(i);
            }
        });
    }

    private void getQuestions(ArrayList<QuestionModal> questionModalArrayList) {

//        questionModalArrayList.add(new QuestionModal(""));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ut, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_AboutUs){
            Intent i = new Intent(MainActivity.this, AboutUs.class);
            startActivity(i);
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

}