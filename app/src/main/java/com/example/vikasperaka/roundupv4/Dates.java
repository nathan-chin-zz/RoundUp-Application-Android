package com.example.vikasperaka.roundupv4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Nathan on 1/9/2017.
 */

/**
 * The Dates class was created to be input into the RoundUp app to keep track of days
 * @author Nathan Chin
 * @date 1/9/2017
 */
public class Dates extends AppCompatActivity{
    // Instance variables
    private int startHour;
    private int endHour;
    private boolean isAM;
    private ArrayList<String> hours;
    private boolean isSelected;
    private int day;
    private int fontColor;
    private int month;

    // Constants
    final int colorBlack = Color.BLACK;
    final int colorClear = Color.TRANSPARENT;
    final int colorDKGray = Color.DKGRAY;

    final int[] HOURS = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    final String HALF = ":30";
    final String AM = "AM";
    final String PM = "PM";
    final int TIME_SHIFT = 12;

    /**
     * The Dates constructor takes no parameters and assigns values to the instance variables. The View is made empty
     * automatically
     */
    public Dates(){
        isSelected = false;
        fontColor = colorDKGray;
        day = 0;
        makeEmptyView();
        this.month = month;
    }

    /**
     * The Dates constructor takes a month parameter so that a month can be assigned to a created Dates object. The View is made
     * empty automatically.
     * @param month the month to be assigned to the day (must be an integer with 1 = January,... 12 = December)
     */
    public Dates(int month){
        isSelected = false;
        fontColor = colorDKGray;
        day = 0;
        makeEmptyView();
        this.month = month;
    }

    /**
     * The Dates constructor takes a day and month parameter so a specific day can be created
     * @param day the number day of the month the date is given. If the day given is 0, the View is made empty
     * @param month the number month the date is given (must be an integer with 1 = January,... 12 = December)
     */
    public Dates(int day, int month){
        isSelected = false;
        this.day = day;
        fontColor = colorDKGray;
        if(this.day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    /**
     * The Dates constructor has four parameters so a really specific day can be created
     * @param month the month the day is to be placed in
     * @param startHour the starting hour of the date for the ArrayList of hours
     * @param endHour the ending hour of the date for the ArrayList of hours
     * @param hours the ArrayList of hours of the day
     */
    public Dates(int month, int startHour, int endHour, ArrayList<String> hours){
        this.startHour = startHour;
        this.endHour = endHour;
        this.hours = hours;
        fontColor = colorDKGray;
        if(this.day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    /**
     * The setDay method sets the day of the Dates object to a specific day
     * @param day
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * The getDay method retrieves the day of the Dates object
     * @return day the day of the Dates object
     */
    public int getDay(){
        return day;
    }

    /**
     * The getFontColor method retrieves the color of the font assigned to the Dates object
     * @return fontColor the color variable assigned
     */
    public int getFontColor(){
        return fontColor;
    }

    /**
     * The getMonth method gets the month of the Dates object
     * @return month the month of the object
     */
    public int getMonth(){
        return month;
    }

    /**
     * The getDayAndMonth method returns the day and month of a Dates object in a String based on its values
     * @return temp the day and month of the Dates object in a String
     */
    public String getDayAndMonth(){
        String temp = "";
        String nameMonth = "";
        switch(month)   {
            case 1:    nameMonth = "Jan";
                break;
            case 2:    nameMonth = "Feb";
                break;
            case 3:    nameMonth = "Mar";
                break;
            case 4:    nameMonth = "Apr";
                break;
            case 5:    nameMonth = "May";
                break;
            case 6:    nameMonth = "Jun";
                break;
            case 7:    nameMonth = "Jul";
                break;
            case 8:    nameMonth = "Aug";
                break;
            case 9:    nameMonth = "Sep";
                break;
            case 10:   nameMonth = "Oct";
                break;
            case 11:   nameMonth = "Nov";
                break;
            case 12:   nameMonth = "Dec";
                break;
        }
        temp = nameMonth + " " + day;
        return temp;
    }

    /**
     * The isClicked method tells whether or not the Dates object was clicked on
     * @return isSelected a boolean variable telling if the object was clicked
     */
    public boolean isClicked(){
        return isSelected;
    }

    /**
     * The clickTheDay method is called when the View is actually clicked. It takes the value of the isSelected variable and
     * flips it
     */
    public void clickTheDay(){
        isSelected = !isSelected;
    }

    /**
     * The translateHour method returns the hour given in a String format along with time of day (AM or PM)
     * @param hour the hour of the day to translate into a String
     * @return time the String holding the hour and time of day
     */
    public String translateHour(int hour){
        String time = "";
        int arraySpot = 0;
        switch(hour) {
            case 0:   arraySpot = TIME_SHIFT;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 1:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 2:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 3:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 4:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 5:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 6:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 7:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 8:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 9:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 10:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 11:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 12:   arraySpot = hour;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 13:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 14:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 15:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 16:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 17:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 18:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 19:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 20:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 21:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 22:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 23:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
        }
        return time;
    }

    /**
     * The makeEmptyView method gives the appearance that the View is empty by changing the font color to transparent
     */
    public void makeEmptyView() {
        fontColor = colorClear;
    }

}
