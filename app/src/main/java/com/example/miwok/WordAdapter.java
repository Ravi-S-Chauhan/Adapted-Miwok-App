package com.example.miwok;

import android.app.Activity;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {

    private int mColorResourceId;


    public WordAdapter(Activity context, ArrayList<word> words,int colorResourceId){

        super(context,0,words);
        mColorResourceId = colorResourceId;

    }
    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {
        View listItemVIew = convertView;
        if(listItemVIew == null){
            listItemVIew = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        word currentListItemView = getItem(position);

        TextView defaultTextView = listItemVIew.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentListItemView.getDefaultTranslation());

        TextView miwokTextView = listItemVIew.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentListItemView.getMiwokTranslation());

        ImageView imageView= listItemVIew.findViewById(R.id.img_view);


        if (currentListItemView.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentListItemView.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemVIew.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        return listItemVIew;
    }

}
