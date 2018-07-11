/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {
    private int headIndex, bodyIndex, legsIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null){

            Bundle b = getIntent().getExtras();

            if(b != null){
                headIndex = b.getInt("headIndex");
                bodyIndex = b.getInt("bodyIndex");
                legsIndex = b.getInt("legsIndex");
            }

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
                    .add(R.id.leg_container, legsPartFragment)
                    .commit();
        }
    }
}
