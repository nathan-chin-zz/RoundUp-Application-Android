package com.example.vikasperaka.roundupv4;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Nathan on 1/17/2017.
 */

public class SpinnerIncrementAdapter extends BaseAdapter {

    Context context;
    private String[] increments;
    private LayoutInflater inflater;
    private int idOfSpinner;
    private int position;

    public SpinnerIncrementAdapter(Activity context, String[] times){
        this.context = context;
        this.increments = times;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount(){
        return increments.length;
    }

    @Override
    public Object getItem(int i){
        return null;
    }

    @Override
    public long getItemId(int i){
        return 5;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        convertView = inflater.inflate(R.layout.custom_spinner_item, null);
        TextView item = (TextView) convertView.findViewById(R.id.spinner_item);
        item.setText(increments[position]);
        this.position = position;
        return convertView;
    }
}
