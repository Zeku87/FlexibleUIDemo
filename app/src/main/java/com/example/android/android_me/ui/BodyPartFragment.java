package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class BodyPartFragment extends Fragment {

    private List<Integer> imageIds;
    private int listIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstance){
        View rootView = inflater.inflate(R.layout.fragment_body_part,
                container, false);

        ImageView imageViewBodyPart = rootView.findViewById(R.id.image_view_body_part);

        if(imageIds != null){

            imageViewBodyPart.setImageResource(imageIds.get(this.listIndex));
        }


        return rootView;
    }

    public void setListOfImageResources(List<Integer> imageIds){
        this.imageIds = imageIds;
    }

    public void setListIndex(int listIndex){
        this.listIndex = listIndex;
    }
}
