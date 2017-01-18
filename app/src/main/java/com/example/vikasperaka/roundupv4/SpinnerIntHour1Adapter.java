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

public class SpinnerIntHour1Adapter extends BaseAdapter{

    Context context;
    private int[] times;
    private LayoutInflater inflater;
    private int idOfSpinner;
    private int position;

    public SpinnerIntHour1Adapter(Activity context, int[] times){
        this.context = context;
        this.times = times;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount(){
        return times.length;
    }

    @Override
    public Object getItem(int i){
        return null;
    }

    @Override
    public long getItemId(int i){
        return 1;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        convertView = inflater.inflate(R.layout.custom_spinner_item, null);
        TextView item = (TextView) convertView.findViewById(R.id.spinner_item);
        item.setText("" + times[position]);
        this.position = position;
        return convertView;
    }
}
