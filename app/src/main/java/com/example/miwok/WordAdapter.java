package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {
    public WordAdapter(Activity context, ArrayList<word> words){

        super(context,0,words);
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

        return listItemVIew;
    }

}
