package com.example.vikasperaka.roundupv4;

// Import statements (auto import on)
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Nathan on 1/10/2017.
 */

/**
 * The CalendarAdapter class was created specifically for this app. Using GridViews, the adapter sees if the screen is fully
 * occupied. If not, it inflates a view with the list_item (should be named grid_item whoops) format specified into a new View,
 * and the values for this View are taken from the ArrayList<Dates> that is an input parameter. This class allows Views to be
 * recycled when they are not presently on the screen and saves memory.
 * @author Nathan Chin
 * @date 1/10/2017
 */

public class CalendarAdapter extends ArrayAdapter<Dates>{

    public CalendarAdapter(Activity context, ArrayList<Dates> dates){
        super(context, 0, dates);
    }

    // Instance variables
    private int numDays = 0;    // Implemented to keep track of the number of days selected by the user

    /**
     * The getNumDays() method allows the variable (that is keeping track of the number of selected days by the user) to be
     * returned in a different class.
     * @return numDays an integer value of the number of GridView items that hold a Dates object that have been selected
     */
    public int getNumDays(){
        return numDays;
    }

    // Created a new Calendar object to get the current time and date of the phone
    Calendar calendar = Calendar.getInstance();

    // Store the current day into an int variable currentDay so that the current date can be underlined in the GridView
    SimpleDateFormat theDay = new SimpleDateFormat("d", Locale.US);
    String strDay = theDay.format(calendar.getTime());
    int currentDay = Integer.parseInt(strDay);

    // Store the current month into an int variable currentMonth so that the current date can be underlined in the GridView
    SimpleDateFormat theMonth = new SimpleDateFormat("M", Locale.US);
    String strMonth = theMonth.format(calendar.getTime());
    int currentMonth = Integer.parseInt(strMonth);


    /**
     * The getView method checks to see if an existing View is being reused when the user scrolls on the screen. If it is,
     * the data from the View is recycled onto a used GridView item. If the View is new, then data is inflated from the
     * ArrayList<Dates> passed in
     * @param position the position of the ArrayList element in the ArrayList
     * @param convertView the View that is being recycled
     * @param parent provided so you can inflate the recycled view in the correct layout
     * @return gridItemView the whole item grid layout so it can be shown in the GridView
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if(gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the object located at this position in the list
        final Dates currentDate = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView date = (TextView) gridItemView.findViewById(R.id.number);

        // Displays the day assigned to the GridView item (or the Dates object it is holding)
        date.setText("" + currentDate.getDay());

        // The day is displayed in the font color specified for the Dates object
        date.setTextColor(currentDate.getFontColor());

        // Initializes the background colors of the GridView items. This allows Views that are recycled to maintain the color
        // of selection when the View is scrolled back onto the screen
        if(currentDate.isClicked() == false){
            date.setBackgroundColor(Color.LTGRAY); //Color.LTGRAY is the default color. The View is not selected.
        }
        else{
            date.setBackgroundColor(Color.CYAN); //Color.CYAN is the color when the View is selected.
        }

        // This is written to underline the current day according to the phone's settings. This lets the user know which day
        // it actually is when presented with the calendars on the screen
        if((currentDate.getDay() == currentDay) && (currentDate.getMonth() == currentMonth)){
            SpannableString content = new SpannableString("" + currentDay);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            date.setText(content);
        }

        // Setting an onClickListener on each View performs actions when clicked
        gridItemView.setOnClickListener(new View.OnClickListener(){
            /**
             * The method onClick defines what happens when a GridView item is clicked/selected
             * @param v is the View that has been clicked/selected
             */
            @Override
            public void onClick(View v){
                TextView cur = (TextView) v.findViewById(R.id.number); // Finds the TextView defined in the list_item layout

                // If the day clicked/selected is actually a calendar day (and not an empty calendar spot for spacing), then
                // change its boolean value so it is clicked

                ColorDrawable color = (ColorDrawable)cur.getBackground();
                int theColor = color.getColor();
                if(numDays < 21){
                    if(theColor == Color.CYAN){
                        currentDate.clickTheDay();
                        numDays = numDays - 1;
                        cur.setBackgroundColor(Color.LTGRAY);
                    }
                    else{
                        if (currentDate.getFontColor() == Color.TRANSPARENT) {
                            //Toast.makeText(getContext(), "Please select a valid day", Toast.LENGTH_SHORT).show();
                        } else if ((currentDate.getDay() < currentDay) && (currentDate.getMonth() == currentMonth)) {
                            Toast.makeText(getContext(), "Please select a valid day", Toast.LENGTH_SHORT).show();
                        } else if(currentDate.getMonth() < currentMonth) {
                            Toast.makeText(getContext(), "Please select a valid day", Toast.LENGTH_SHORT).show();
                        }
                        if((currentDate.getFontColor() != Color.TRANSPARENT) && ((currentDate.getDay() >= currentDay) && (currentDate.getMonth() == currentMonth))){
                            currentDate.clickTheDay();
                            cur.setBackgroundColor(Color.CYAN);
                            numDays = numDays + 1;
                        }
                        else if((currentDate.getFontColor() != Color.TRANSPARENT) && (currentDate.getMonth() > currentMonth)){
                            currentDate.clickTheDay();
                            cur.setBackgroundColor(Color.CYAN);
                            numDays = numDays + 1;
                        }
                    }
                }
                else{
                    if(theColor == Color.CYAN){
                        currentDate.clickTheDay();
                        numDays = numDays - 1;
                        cur.setBackgroundColor(Color.LTGRAY);
                    }
                    else{
                        if (currentDate.getFontColor() == Color.TRANSPARENT) {
                            //Toast.makeText(getContext(), "Please select a valid day", Toast.LENGTH_SHORT).show();
                        } else if ((currentDate.getDay() < currentDay) && (currentDate.getMonth() == currentMonth)) {
                            Toast.makeText(getContext(), "Please select a valid day", Toast.LENGTH_SHORT).show();
                        } else if(currentDate.getMonth() < currentMonth) {
                            Toast.makeText(getContext(), "Please select a valid day", Toast.LENGTH_SHORT).show();
                        }
                        if((currentDate.getFontColor() != Color.TRANSPARENT) && ((currentDate.getDay() >= currentDay) && (currentDate.getMonth() == currentMonth))){
                            Toast.makeText(getContext(), "Please do not select more than 21 dates", Toast.LENGTH_SHORT).show();
                        }
                        else if((currentDate.getFontColor() != Color.TRANSPARENT) && (currentDate.getMonth() > currentMonth)){
                            Toast.makeText(getContext(), "Please do not select more than 21 dates", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                    // If day selected has a number on it and is either the current day or later, then click the day
                    /*if((currentDate.getFontColor() != Color.TRANSPARENT) && ((currentDate.getDay() >= currentDay) && (currentDate.getMonth() == currentMonth))){
                        currentDate.clickTheDay();
                    }
                    // Else if day selected has a number on it and is a month
                    else if((currentDate.getFontColor() != Color.TRANSPARENT) && ((currentDate.getMonth() > currentMonth))){
                        currentDate.clickTheDay();
                    }
                    // Else if day selected has no number on it then click on it
                    else if((currentDate.getFontColor() == Color.TRANSPARENT)){
                        //currentDate.clickTheDay();
                    }
                    else{
                        Toast.makeText(getContext(), "Please select a valid day", Toast.LENGTH_SHORT).show();
                        numDays = numDays + 1;
                    }

                    // Else if the GridView clicked has not been clicked before and is not an empty calendar spot, then the
                    // background color is changed to show that it has been unselected and the number of selected days goes
                    // down by one.
                    if(!currentDate.isClicked() && currentDate.getFontColor() != Color.TRANSPARENT){
                        cur.setBackgroundColor(Color.LTGRAY);
                        numDays = numDays - 1;
                    }
                    else if(!currentDate.isClicked() && currentDate.getFontColor() == Color.TRANSPARENT){
                        numDays = numDays + 1;
                    }

                if(numDays < 21){

                    // If the GridView clicked has been clicked before and is not an empty calendar spot, then the background color
                    // is changed to show that it has been selected and the number of selected days goes up by one.
                    if(currentDate.isClicked() && currentDate.getFontColor() != Color.TRANSPARENT){
                        cur.setBackgroundColor(Color.CYAN);
                        numDays = numDays + 1;
                    }
                }
                else{
                    if(currentDate.getFontColor() != Color.TRANSPARENT){
                        currentDate.clickTheDay();
                        if((currentDate.getDay() >= currentDay) && (currentDate.getMonth() == currentMonth)) {
                            Toast.makeText(getContext(), "For maximum efficiency, please do not choose more than 21 days",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(currentDate.getMonth() > currentMonth) {
                            Toast.makeText(getContext(), "For maximum efficiency, please do not choose more than 21 days",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }*/

            }
        });

        // Return the whole list item layout so that it can be shown in the ListView
        return gridItemView;
    }
}
