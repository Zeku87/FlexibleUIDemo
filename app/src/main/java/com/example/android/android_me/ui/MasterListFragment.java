package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    private OnImageClickListener imageCallback;

    /**
     * Through this interface there's a communication between the fragment and the host activity
     */
    public interface OnImageClickListener{
        public void onImageClick(int position);
    }

    /**
     *  We need to make sure that the callback has been implemented by MainActivity
     * @param context
     */
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            this.imageCallback = (OnImageClickListener) context;
        }catch (ClassCastException e){
            Log.e("CAST EXCEPTION", "MUST IMPLEMENT OnImageClickListener", e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstance){

        View rootView = inflater.inflate(R.layout.fragment_master_list,
                container, false);

        GridView gridView = rootView.findViewById(R.id.gridViewAllImages);

        MasterListAdapter adapter = new MasterListAdapter(getContext(),
                AndroidImageAssets.getAll());

        gridView.setAdapter(adapter);

        //Here we're triggering onImageClick callback
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                imageCallback.onImageClick(position);
            }
        });

        return rootView;

    }
}
