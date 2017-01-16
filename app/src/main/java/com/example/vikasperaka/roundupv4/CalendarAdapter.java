package com.example.vikasperaka.roundupv4;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat theDay = new SimpleDateFormat("d", Locale.US);
    String strDay = theDay.format(calendar.getTime());
    int currentDay = Integer.parseInt(strDay);

    SimpleDateFormat theMonth = new SimpleDateFormat("M", Locale.US);
    String strMonth = theMonth.format(calendar.getTime());
    int currentMonth = Integer.parseInt(strMonth);


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

        date.setText("" + currentDate.getDay());
        date.setTextColor(currentDate.getFontColor());

        if(currentDate.isClicked() == false){
            date.setBackgroundColor(Color.LTGRAY);
        }
        else{
            date.setBackgroundColor(Color.CYAN);
        }

        if((currentDate.getDay() == currentDay) && (currentDate.getMonth() == currentMonth)){
            SpannableString content = new SpannableString("" + currentDay);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            date.setText(content);
        }

        gridItemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView hi = (TextView) v.findViewById(R.id.number);
                if(currentDate.getFontColor() != Color.TRANSPARENT){
                    currentDate.clickTheDay();
                }
                /*currentDate.clickTheDay();
                if(currentDate.getFontColor() == Color.TRANSPARENT){
                    currentDate.clickTheDay();
                }
                else*/ if(currentDate.isClicked() == true && currentDate.getFontColor() != Color.TRANSPARENT){
                    hi.setBackgroundColor(Color.CYAN);
                    numDays = numDays + 1;
                }
                else if(currentDate.isClicked() == false && currentDate.getFontColor() != Color.TRANSPARENT){
                    hi.setBackgroundColor(Color.LTGRAY);
                    numDays = numDays - 1;
                }

            }
        });

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return gridItemView;
    }
}
