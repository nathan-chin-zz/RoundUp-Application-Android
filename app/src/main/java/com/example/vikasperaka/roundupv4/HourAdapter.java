package com.example.vikasperaka.roundupv4;

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
 * Created by Nathan on 1/20/2017.
 */

public class HourAdapter extends ArrayAdapter<Hour> {

    public HourAdapter(Activity context, ArrayList<Hour> hours){
        super(context, 0, hours);
    }

    // Instance variables
    private int numHours = 0;    // Implemented to keep track of the number of days selected by the user
    private ArrayList<Hour> theSelected = new ArrayList<>();

    public int getNumDays(){
        return numHours;
    }

    public ArrayList<Hour> getTheSelected(){
        return theSelected;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if(gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.hours_item, parent, false);
        }

        // Get the object located at this position in the list
        final Hour currentHour = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView hour = (TextView) gridItemView.findViewById(R.id.hour);

        // Displays the day assigned to the GridView item (or the Dates object it is holding)
        hour.setText("" + currentHour.numToString(currentHour.getHour()) + " " + currentHour.getTimeOfDay());

        // The day is displayed in the font color specified for the Dates object
        hour.setTextColor(Color.GRAY);

        // Initializes the background colors of the GridView items. This allows Views that are recycled to maintain the color
        // of selection when the View is scrolled back onto the screen
        if(currentHour.isClicked() == false){
            hour.setBackgroundColor(Color.LTGRAY); //Color.LTGRAY is the default color. The View is not selected.
            numHours = numHours - 1;
            theSelected.remove(currentHour);
        }
        else if(currentHour.isClicked()){
            hour.setBackgroundColor(Color.CYAN); //Color.CYAN is the color when the View is selected.
            numHours = numHours + 1;
            theSelected.add(currentHour);
        }

        // Setting an onClickListener on each View performs actions when clicked
        gridItemView.setOnClickListener(new View.OnClickListener(){
            /**
             * The method onClick defines what happens when a GridView item is clicked/selected
             * @param v is the View that has been clicked/selected
             */
            @Override
            public void onClick(View v){
                TextView cur = (TextView) v.findViewById(R.id.hour); // Finds the TextView defined in the list_item layout
                currentHour.clickTheHour();
                // If the GridView clicked has been clicked before and is not an empty calendar spot, then the background color
                // is changed to show that it has been selected and the number of selected days goes up by one.
                if(currentHour.isClicked()){
                    cur.setBackgroundColor(Color.CYAN);
                    numHours = numHours + 1;
                    theSelected.add(currentHour);
                }

                // Else if the GridView clicked has not been clicked before and is not an empty calendar spot, then the
                // background color is changed to show that it has been unselected and the number of selected days goes
                // down by one.
                else if(!currentHour.isClicked()){
                    cur.setBackgroundColor(Color.LTGRAY);
                    numHours = numHours - 1;
                    if(!theSelected.isEmpty()){
                        theSelected.remove(currentHour);
                    }
                }

            }
        });

        // Return the whole list item layout so that it can be shown in the ListView
        return gridItemView;
    }
}
