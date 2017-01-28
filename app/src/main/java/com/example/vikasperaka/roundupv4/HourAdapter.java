package com.example.vikasperaka.roundupv4;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Nathan on 1/20/2017.
 */

public class HourAdapter extends ArrayAdapter<Hour> {

    private int lengthOfList;

    public HourAdapter(Activity context, ArrayList<Hour> hours){
        super(context, 0, hours);
        lengthOfList = hours.size();
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
    private Hour toAdd = new Hour();
    private Hour toRemove = new Hour();

    public void sortTheList(ArrayList<Hour> theList){
        if(theList.size() != 1){
            Collections.sort(theList, new Comparator<Hour>() {
                @Override
                public int compare(Hour o1, Hour o2) {
                    return o1.compareTo(o2);
                }
            });
        }
    }

    public int checkIfInList(Hour checkHour){
        /*boolean truth = false;
        for (int g = 0; g < theSelected.size(); g++){
            Hour tempHour = theSelected.get(g);
            int temp = tempHour.compareTo(checkHour);
            if(temp == 0){
                truth = true;
            }
            else{
                truth = false;
            }
        }
        return truth;*/
        int truth = theSelected.indexOf(checkHour);
        return truth;
    }

    public Hour getToAdd(){
        return toAdd;
    }

    public Hour getToRemove(){
        return toRemove;
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
        final TextView hour = (TextView) gridItemView.findViewById(R.id.hour);

        // Displays the day assigned to the GridView item (or the Dates object it is holding)
        hour.setText("" + currentHour.numToString(currentHour.getHour()) + " " + currentHour.getTimeOfDay());

        // The day is displayed in the font color specified for the Dates object
        hour.setTextColor(Color.GRAY);

        theSelected.clear();
        for(int h = 0; h < lengthOfList; h++){
            Hour tempHour = getItem(h);
            if(tempHour.isClicked()){
                theSelected.add(tempHour);
            }
        }

        if(currentHour.isClicked()){
            hour.setBackgroundColor(Color.CYAN);
        }
        else{
            hour.setBackgroundColor(Color.LTGRAY);
        }

        /*theSelected.clear();
        for(int h = 0; h < lengthOfList; h++){
            Hour tempHour = getItem(h);
            if(tempHour.isClicked()){
                theSelected.add(tempHour);
            }
        }

        // Initializes the background colors of the GridView items. This allows Views that are recycled to maintain the color
        // of selection when the View is scrolled back onto the screen
        if(currentHour.isClicked() == false){
            hour.setBackgroundColor(Color.LTGRAY); //Color.LTGRAY is the default color. The View is not selected.
            if(checkIfInList(currentHour) != -1) {
                numHours = numHours - 1;
                //theSelected.remove(currentHour);
                //sortTheList(theSelected);
            }
        }
        else if(currentHour.isClicked()){
            hour.setBackgroundColor(Color.CYAN); //Color.CYAN is the color when the View is selected.
            if(checkIfInList(currentHour) == -1) {
                numHours = numHours + 1;
                //theSelected.add(currentHour);
                //sortTheList(theSelected);
            }
        }

        ColorDrawable viewColor = (ColorDrawable)hour.getBackground();
        int colorID = viewColor.getColor();
        if(colorID == Color.CYAN){
            if(checkIfInList(currentHour) == -1) {
                numHours = numHours + 1;
                //theSelected.add(currentHour);
                //sortTheList(theSelected);
            }
        }
        else{
            if(checkIfInList(currentHour) != -1) {
                numHours = numHours - 1;
                //theSelected.remove(currentHour);
                //sortTheList(theSelected);
            }
        }
        */

        // Setting an onClickListener on each View performs actions when clicked
        gridItemView.setOnClickListener(new View.OnClickListener(){
            /**
             * The method onClick defines what happens when a GridView item is clicked/selected
             * @param v is the View that has been clicked/selected
             */
            @Override
            public void onClick(View v){
                TextView cur = (TextView) v.findViewById(R.id.hour); // Finds the TextView defined in the list_item layout

                ColorDrawable color = (ColorDrawable)hour.getBackground();
                int theColor = color.getColor();
                if(theColor == Color.CYAN){
                    currentHour.clickTheHour();
                    numHours = numHours - 1;
                    hour.setBackgroundColor(Color.LTGRAY);
                    theSelected.remove(currentHour);
                    sortTheList(theSelected);
                    toRemove = currentHour;
                }
                else{
                    currentHour.clickTheHour();
                    numHours = numHours + 1;
                    hour.setBackgroundColor(Color.CYAN);
                    theSelected.add(currentHour);
                    sortTheList(theSelected);
                    toAdd = currentHour;
                }

                /*
                currentHour.clickTheHour();
                // If the GridView clicked has been clicked before and is not an empty calendar spot, then the background color
                // is changed to show that it has been selected and the number of selected days goes up by one.
                if(currentHour.isClicked()){
                    cur.setBackgroundColor(Color.CYAN);
                    numHours = numHours + 1;
                    theSelected.add(currentHour);
                    sortTheList(theSelected);
                }

                // Else if the GridView clicked has not been clicked before and is not an empty calendar spot, then the
                // background color is changed to show that it has been unselected and the number of selected days goes
                // down by one.
                else if(!currentHour.isClicked()){
                    cur.setBackgroundColor(Color.LTGRAY);
                    numHours = numHours - 1;
                    //if(!theSelected.isEmpty()){
                        //theSelected.remove(currentHour);
                        //sortTheList(theSelected);
                    //}
                }*/

            }
        });

        // Return the whole list item layout so that it can be shown in the ListView
        return gridItemView;
    }
}
