package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

//Implements an interface to keep tracking of image selected
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    //Keep tracking of body parts. By default is -1, see AndroidMeActivity.
    private int headIndex = -1, bodyIndex = -1, legsIndex = -1;
    private boolean isTwoPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //It'll be null when using smaller width than 600dp
        if(this.findViewById(R.id.android_me_linear_layout) == null){
            isTwoPaneLayout = false;
        }
        else{//it'll use our double pane layout so display is 600dp width or wider
            isTwoPaneLayout = true;

            //Let's improve little bit the layout when the two panes are visible
            final Button buttonNext = (Button) this.findViewById(R.id.buttonNext);
            buttonNext.setVisibility(View.GONE);
            final GridView gridView = (GridView) this.findViewById(R.id.gridViewAllImages);
            gridView.setNumColumns(2);

            //As in AndroidMeActivity we need to show the three fragments
            if (savedInstanceState == null){


                FragmentManager fragmentManager = getSupportFragmentManager();

                //***************** HEAD FRAGMENT *****************//
                BodyPartFragment headPartFragment = new BodyPartFragment();
                headPartFragment.setImageIds(AndroidImageAssets.getHeads());

                if(headIndex > -1){
                    headPartFragment.setListIndex(headIndex);
                }

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headPartFragment)
                        .commit();

                //**************** BODY FRAGMENT *****************//
                BodyPartFragment bodyPartFragment = new BodyPartFragment();
                bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());

                if(bodyIndex > -1){
                    bodyPartFragment.setListIndex(bodyIndex);
                }

                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyPartFragment)
                        .commit();

                //***************** LEGS FRAGMENT *****************//
                BodyPartFragment legsPartFragment = new BodyPartFragment();
                legsPartFragment.setImageIds(AndroidImageAssets.getLegs());

                if(legsIndex > -1){
                    legsPartFragment.setListIndex(legsIndex);
                }

                fragmentManager.beginTransaction()
                        .add(R.id.legs_container, legsPartFragment)
                        .commit();
            }
        }
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

        if(isTwoPaneLayout){
            BodyPartFragment bodyPartFragment = new BodyPartFragment();

            switch (bodyPart){
                case 0:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, bodyPartFragment)
                            .commit();
                    break;

                case 1:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setListIndex(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyPartFragment)
                            .commit();
                    break;

                case 2:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setListIndex(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legs_container, bodyPartFragment)
                            .commit();
                    break;

                default: break;
            }
        }

        if(!isTwoPaneLayout) {
            //Finally let's assign the image position to the correct body part
            switch (bodyPart) {
                case 0:
                    headIndex = index;
                    break;

                case 1:
                    bodyIndex = index;
                    break;

                case 2:
                    legsIndex = index;
                    break;

                default:
                    break;
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
}
