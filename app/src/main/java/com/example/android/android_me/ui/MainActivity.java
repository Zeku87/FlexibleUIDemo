package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

//Implements an interface to keep tracking of image selected
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    //Keep tracking of body parts. By default is -1, see AndroidMeActivity.
    private int headIndex = -1, bodyIndex = -1, legsIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageClick(int position){
        Log.d("IMAGE", "IMAGE CLICKED");

        //First let's identify which body part was clicked
        //For each body part we have 12 images
        //if clicked between [0,11] -> bodyPart = 0 (HEAD)
        //if clicked between [12,23] -> bodyPart = 1 (BODY)
        //if clicked between [24,35] -> bodyPart = 2 (LEGS)
        int bodyPart = position / 12;

        //Second we need to know which particular image was clicked
        //We have two ways. Use whichever index or index2
        int index = position % 12;
        int index2 = position - bodyPart*12;

        //Finally let's assign the image position to the correct body part
        switch (bodyPart){
            case 0:
                headIndex = index;
                break;

            case 1:
                bodyIndex = index;
                break;

            case 2:
                legsIndex = index;
                break;

            default: break;
        }

        //Now let's communicate this to AndroidMeActivity to display the body part image
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legsIndex", legsIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        //Button
        Button buttonNext = (Button) this.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTON", "BUTTON CLICKED");
                startActivity(intent);
            }
        });
    }
}
