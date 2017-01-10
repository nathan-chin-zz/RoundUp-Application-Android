package com.example.android.roundup;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nathan on 1/10/2017.
 */

public class CalendarAdapter extends ArrayAdapter<Dates>{

    public CalendarAdapter(Activity context, ArrayList<Dates> dates){
        super(context, 0, dates);
    }

    int numDays = 0;

    public int getNumDays(){
        return numDays;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if(gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final Dates currentDate = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView date = (TextView) gridItemView.findViewById(R.id.number);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        date.setText("" + currentDate.getDay());

        if(currentDate.isClicked() == false){
            date.setBackgroundColor(Color.MAGENTA);
        }
        else{
            date.setBackgroundColor(Color.CYAN);
        }


        gridItemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView hi = (TextView) v.findViewById(R.id.number);
                currentDate.clickTheDay();
                if(currentDate.isClicked() == true){
                    hi.setBackgroundColor(Color.CYAN);
                    numDays = numDays + 1;
                }
                else {
                    hi.setBackgroundColor(Color.MAGENTA);
                    numDays = numDays - 1;
                }
            }
        });

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return gridItemView;
    }
}
