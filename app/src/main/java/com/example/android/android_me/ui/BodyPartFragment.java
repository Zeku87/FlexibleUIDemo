package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    private List<Integer> imageIds;
    private int listIndex = 0;
    private final String IMAGE_ID_STATE = "IMAGE_STATE_STATE";
    private final String LIST_INDEX_STATE = "LIST_INDEX_STATE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstance){
        View rootView = inflater.inflate(R.layout.fragment_body_part,
                container, false);

        final ImageView imageViewBodyPart = rootView.findViewById(R.id.image_view_body_part);

        if(imageIds != null){
            imageViewBodyPart.setImageResource(imageIds.get(this.listIndex));
        }

        imageViewBodyPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListIndex(getListIndex() + 1);
                imageViewBodyPart.setImageResource(getImageIds().get(getListIndex()));
            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        bundle.putInt(LIST_INDEX_STATE, getListIndex());
        bundle.putIntegerArrayList(IMAGE_ID_STATE,(ArrayList<Integer>) getImageIds());
    }

    public void setImageIds(List<Integer> imageIds){
        this.imageIds = imageIds;
    }

    public List<Integer> getImageIds(){
        return imageIds;
    }

    public void setListIndex(int listIndex){
        if(listIndex == getImageIds().size())
            this.listIndex = 0;
        else{
            this.listIndex = listIndex;
        }
    }

    public int getListIndex(){
        return listIndex;
    }
}
